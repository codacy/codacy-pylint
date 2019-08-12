import os
import sys
import json
import jsonpickle
from io import StringIO
from subprocess import Popen, PIPE
import ast
from itertools import takewhile, dropwhile
import glob
import time
import traceback

class Result:
    def __init__(self, filename, message, patternId, line):
        self.filename = filename
        self.message = message
        self.patternId = patternId
        self.line = line

def toJson(obj): return jsonpickle.encode(obj, unpicklable=False)
def readJsonFile(path):
    with open(path, 'r') as file:
        res = json.loads(file.read())
    return res

def runPylint(options):
    process = Popen(
        ["pylint"] + options,
        stdout=PIPE
    )
    stdout = process.communicate()[0]
    return stdout.decode("utf-8")

def isPython3(f):
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

def parseResult(res):
    lines = res.split(os.linesep)
    splits = [arr for arr in [[split.strip() for split in l.split(':')] for l in lines] if len(arr) == 5] 
    results = [Result(filename=res[0],message=res[4],patternId=res[3],line=int(res[1], 10)) for res in splits]
    return results

def walkDirectory(directory):
    for filename in glob.iglob(directory + '**/*.py', recursive=True):
        yield filename

def readConfiguration(configFile, srcDir):
    def allFiles(): return list(walkDirectory(srcDir))

    try:
        configuration = readJsonFile(configFile)
        files = [f for f in configuration['files'] if isPython3(f)] if 'files' in configuration else allFiles()
        pylint = [t for t in configuration['tools'] if t['name'] == '''PyLint (Python 3)'''][0] or []
        rules = ['--disable=all','--enable=' + ','.join([p['patternId'] for p in pylint['patterns']])] if 'patterns' in pylint else []
    except:
        rules = []
        files = allFiles()
    return rules, files

def chunks(lst,n):
    return [lst[i:i + n] for i in range(0, len(lst), n)]

def runPylintWith(rules, files):
    res = runPylint([
        'format=parseable',
        '--load-plugins=pylint_django',
        '--disable=django-installed-checker,django-model-checker',
        '--load-plugins=pylint_flask'] +
        rules +
        files)
    return parseResult(res)

if __name__ == '__main__':
    try:
        (rules, files) = readConfiguration('/.codacyrc', '/src')
        for chunk in chunks(files, 10):
            results = runPylintWith(rules, chunk)
            output = os.linesep.join([toJson(res) for res in results])
            print(output)
            print()
    except:
        traceback.print_stack()
        sys.exit(1)
