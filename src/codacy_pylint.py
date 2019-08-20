import os
import sys
import json
import jsonpickle
from subprocess import Popen, PIPE
import ast
from itertools import takewhile, dropwhile
import glob
import re
import multiprocessing
import signal
from contextlib import contextmanager
from functools import partial

@contextmanager
def timeout(time):
    # Register a function to raise a TimeoutError on the signal.
    signal.signal(signal.SIGALRM, lambda: sys.exit(2))
    # Schedule the signal to be sent after ``time``.
    signal.alarm(time)
    yield

defaultTimeout = 16 * 60
def getTimeout(timeoutString):
    l = timeoutString.split()
    if len(l) != 2 or not l[0].isdigit():
        return defaultTimeout
    elif l[1] == "second" or l[1] == "seconds":
        return int(l[0])
    elif l[1] == "minute" or l[1] == "minutes":
        return int(l[0]) * 60
    elif l[1] == "hour" or l[1] == "hours":
        return int(l[0]) * 60 * 60
    else:
        return defaultTimeout
        

class Result:
    def __init__(self, filename, message, patternId, line):
        self.filename = filename
        self.message = message
        self.patternId = patternId
        self.line = line
    def __str__(self):
        return f'Result({self.filename},{self.message},{self.patternId},{self.line})'
    def __repr__(self):
        return self.__str__()
    def __eq__(self, o):
        return self.filename == o.filename and self.message == o.message and self.patternId == o.patternId and self.line == o.line

class Configuration:
    def __init__(self, rules, files, python_version):
        self.rules = rules
        self.files = files
        self.python_version = python_version if python_version in ['2', '3'] else None

def toJson(obj): return jsonpickle.encode(obj, unpicklable=False)

def readJsonFile(path):
    with open(path, 'r') as file:
        res = json.loads(file.read())
    return res

def runPylint(options, files, python_version):
    def partition(pred, iterable):
        trues = []
        falses = []
        for item in iterable:
            if pred(item):
                trues.append(item)
            else:
                falses.append(item)
        return trues, falses

    (python3Files, python2Files) = partition(partial(isPython3, python_version), files)
    python3Stdout = Popen(
        ["python3.6", "-m", "pylint"] + # Pylint 1.9.5 doesn't support Python 3.7
        options +
        python3Files, 
        stdout=PIPE,
        stderr=PIPE
    ).communicate()[0].decode("utf-8") if len(python3Files) > 0 else ''
    python2Stdout = Popen(
        ["python2", "-m", "pylint"] +
        options +
        python2Files,
        stdout=PIPE,
        stderr=PIPE
    ).communicate()[0].decode("utf-8") if len(python2Files) > 0 else ''
    if python3Stdout == '':
        res = python2Stdout
    elif python3Stdout == '':
        res = python3Stdout
    else:
        res = os.linesep.join([python3Stdout, python2Stdout])
    return res

def isPython3(python_version, f):
    if python_version == None:
        return parsesAsPython3(f)
    elif python_version == '2':
        return False
    else:
        return True
    
def parsesAsPython3(f):
    try:
        with open(f, 'r') as stream:
            try:
                ast.parse(stream.read())
            except (ValueError, TypeError, UnicodeError):
                # Assume it's the current interpreter.
                return True
            except SyntaxError:
                # the other version or an actual syntax error on current interpreter
                return False
            else:
                return True
    except Exception:
        # Shouldn't happen, but if it does, just assume there's
        # something inherently wrong with the file.
        return True

def parseMessage(message):
    return re.search('''\[(.+)\(.+\] (.+)''', message).groups()

def parseResult(res):
    lines = res.split(os.linesep)
    splits = [arr for arr in [[split.strip() for split in l.split(':')] for l in lines] if len(arr) == 3]
    def createResults():
        for res in splits:
            (patternId, message) = parseMessage(res[2])
            yield Result(filename=res[0],message=message,patternId=patternId,line=int(res[1], 10))
    return list(createResults())

def walkDirectory(directory):
    def generate():
        for filename in glob.iglob(directory + '**/*.py', recursive=True):
            res = filename[len(os.path.abspath(directory)) + 1:1]
            yield res
    return list(generate())

def readConfiguration(configFile, srcDir):
    def allFiles(): return walkDirectory(srcDir)
    try:
        configuration = readJsonFile(configFile)
        files = [f for f in configuration['files']] if 'files' in configuration else allFiles()
        tools = [t for t in configuration['tools'] if t['name'] == 'PyLint']
        python_version = configuration.get('python_version').strip()
        if len(tools) > 0:
            pylint = tools[0]
            rules = ['--disable=all','--enable=' + ','.join([p['patternId'] for p in pylint['patterns']])] if 'patterns' in pylint else []
        else:
            rules = []
    except:
        rules = []
        files = allFiles()
        python_version = None
    return Configuration(rules, files, python_version)

def chunks(lst,n):
    return [lst[i:i + n] for i in range(0, len(lst), n)]

def runPylintWith(rules, files):
    res = runPylint([
        '--output-format=parseable',
        '--load-plugins=pylint_django',
        '--disable=django-installed-checker,django-model-checker',
        '--load-plugins=pylint_flask',
        '-j',
        str(multiprocessing.cpu_count())] +
        rules,
        files)
    return parseResult(res)

def runTool(configFile, srcDir):
    configuration = readConfiguration(configFile, srcDir)
    res = []
    filesWithPath = [f'{srcDir}/{f}' for f in configuration.files]
    for chunk in chunks(filesWithPath, 10):
        res.extend(runPylintWith(configuration.rules, chunk))
    for result in res:
        if result.filename.startswith(srcDir + '/'):
            result.filename = result.filename[len(srcDir) + 1:]
    return res

def resultsToJson(results):
    return os.linesep.join([toJson(res) for res in results])

if __name__ == '__main__':
    with timeout(getTimeout(os.environ['TIMEOUT'])):
        try:
            results = runTool('/.codacyrc', '/src')
            print(resultsToJson(results))
        except:
            sys.exit(1)
