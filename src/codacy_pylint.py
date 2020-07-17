import os
import sys
import json
import jsonpickle
from subprocess import Popen, PIPE
import ast
from itertools import groupby
import glob
import re
import signal
from contextlib import contextmanager
from functools import partial
import tempfile
import traceback

@contextmanager
def timeout(time):
    # Register a function to raise a TimeoutError on the signal.
    signal.signal(signal.SIGALRM, lambda: sys.exit(2))
    # Schedule the signal to be sent after ``time``.
    signal.alarm(time)
    yield

DEFAULT_TIMEOUT = 15 * 60
def getTimeout(timeoutString):
    if not timeoutString.isdigit():
        return DEFAULT_TIMEOUT
    return int(timeoutString)

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
    def __init__(self, rules, files, python_version = None, rcfile = None):
        self.rules = rules
        self.files = files
        self.python_version = int(python_version) if python_version in ['2', '3', 2, 3] else None
        self.rcfile = rcfile
    def __str__(self):
        return f'Configuration({self.rules},{self.files},{self.python_version},{self.rcfile})'
    def __repr__(self):
        return self.__str__()

def toJson(obj): return jsonpickle.encode(obj, unpicklable=False)

def readJsonFile(path):
    with open(path, 'r') as file:
        res = json.loads(file.read())
    return res

def runPylint(options, files, cwd=None, python_version=None):
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
        cwd=cwd
    ).communicate()[0].decode("utf-8") if python3Files else ''
    python2Stdout = Popen(
        ["python2.7", "-m", "pylint"] +
        options +
        python2Files,
        stdout=PIPE,
        cwd=cwd
    ).communicate()[0].decode("utf-8") if python2Files else ''
    if python3Stdout == '':
        res = python2Stdout
    elif python3Stdout == '':
        res = python3Stdout
    else:
        res = os.linesep.join([python3Stdout, python2Stdout])
    return res

def isPython3(python_version, f):
    if python_version is None:
        return parsesAsPython3(f)
    elif python_version == 2:
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
    res = re.search(r'\[(.+)\(.+\] (.+)', message)
    return res.groups() if res else None

blacklist = ["E0401"]

def parseResult(res):
    lines = res.split(os.linesep)
    splits = [arr for arr in [[split.strip() for split in l.split(':')] for l in lines] if len(arr) == 3 and parseMessage(arr[2])]
    def createResults():
        for res in splits:
            (patternId, message) = parseMessage(res[2])
            if patternId not in blacklist:
                yield Result(filename=res[0], message=message, patternId=patternId, line=int(res[1], 10))
    return list(createResults())

def walkDirectory(directory):
    def generate():
        for filename in glob.iglob(os.path.join(directory, '**/*.py'), recursive=True):
            res = os.path.relpath(filename, directory)
            yield res
    return list(generate())

def parametersFromJson(jsonObject):
    def generate():
        for pattern in (jsonObject.get('patterns') or []):
            for parameter in (pattern.get('parameters') or []):
                yield parameter
    return list(generate())

parametersSections = {
    "required-attributes": "BASIC",
    "bad-functions": "BASIC",
    "good-names": "BASIC",
    "bad-names": "BASIC",
    "name-group": "BASIC",
    "include-naming-hint": "BASIC",
    "function-rgx": "BASIC",
    "function-name-hint": "BASIC",
    "variable-rgx": "BASIC",
    "variable-name-hint": "BASIC",
    "const-rgx": "BASIC",
    "const-name-hint": "BASIC",
    "attr-rgx": "BASIC",
    "attr-name-hint": "BASIC",
    "argument-rgx": "BASIC",
    "argument-name-hint": "BASIC",
    "class-attribute-rgx": "BASIC",
    "class-attribute-name-hint": "BASIC",
    "inlinevar-rgx": "BASIC",
    "inlinevar-name-hint": "BASIC",
    "class-rgx": "BASIC",
    "class-name-hint": "BASIC",
    "module-rgx": "BASIC",
    "module-name-hint": "BASIC",
    "method-rgx": "BASIC",
    "method-name-hint": "BASIC",
    "no-docstring-rgx": "BASIC",
    "docstring-min-length": "BASIC",
    "spelling-dict": "SPELLING",
    "spelling-ignore-words": "SPELLING",
    "spelling-private-dict-file": "SPELLING",
    "spelling-store-unknown-words": "SPELLING",
    "min-similarity-lines": "SIMILARITIES",
    "ignore-comments": "SIMILARITIES",
    "ignore-docstrings": "SIMILARITIES",
    "ignore-imports": "SIMILARITIES",
    "logging-modules": "LOGGING",
    "max-line-length": "FORMAT",
    "ignore-long-lines": "FORMAT",
    "single-line-if-stmt": "FORMAT",
    "no-space-check": "FORMAT",
    "max-module-lines": "FORMAT",
    "indent-string": "FORMAT",
    "indent-after-paren": "FORMAT",
    "expected-line-ending-format": "FORMAT",
    "notes": "MISCELLANEOUS",
    "ignore-mixin-members": "TYPECHECK",
    "ignored-modules": "TYPECHECK",
    "ignored-classes": "TYPECHECK",
    "zope": "TYPECHECK",
    "generated-members": "TYPECHECK",
    "ignore-iface-methods": "CLASSES",
    "defining-attr-methods": "CLASSES",
    "valid-classmethod-first-arg": "CLASSES",
    "valid-metaclass-classmethod-first-arg": "CLASSES",
    "exclude-protected": "CLASSES",
    "max-args": "DESIGN",
    "ignored-argument-names": "DESIGN",
    "max-locals": "DESIGN",
    "max-returns": "DESIGN",
    "max-branches": "DESIGN",
    "max-statements": "DESIGN",
    "max-parents": "DESIGN",
    "max-attributes": "DESIGN",
    "min-public-methods": "DESIGN",
    "max-public-methods": "DESIGN",
    "deprecated-modules": "IMPORTS",
    "import-graph": "IMPORTS",
    "ext-import-graph": "IMPORTS",
    "int-import-graph": "IMPORTS",
    "overgeneral-exceptions": "EXCEPTIONS"
}

def pyconfigString(parameters):
    def generate():
        for k, g in groupby(parameters, lambda p: parametersSections[p['name']]):
            yield f'[{k}]'
            for p in g:
                yield f"{p['name']}={p['value']}"
    if parameters == []:
        return None
    else:
        return os.linesep.join(list(generate())) + os.linesep

def readConfiguration(configFile, srcDir):
    def allFiles(): return walkDirectory(srcDir)
    try:
        configuration = readJsonFile(configFile)
        files = configuration.get('files') or allFiles()
        tools = [t for t in configuration.get('tools') if t['name'] == 'pylint']
        options = configuration.get('options')
        python_version = options.get('python_version') if options else None
        if tools and 'patterns' in tools[0]:
            pylint = tools[0]
            rules = ['--disable=all', '--enable=' + ','.join([p['patternId'] for p in pylint.get('patterns') or []])]
            rcfile = pyconfigString(parametersFromJson(pylint))
        else:
            rules = []
            python_version = None
            rcfile = None
        res = Configuration(rules, files, python_version, rcfile)
    except:
        res = Configuration(rules=[],
                            files=allFiles())
    return res
    
def chunks(lst,n):
    return [lst[i:i + n] for i in range(0, len(lst), n)]

def runPylintWith(rules, files, cwd, python_version=None, rcfile_path=None):
    rcfile_option = [f'--rcfile={rcfile_path}'] if rcfile_path is not None else []
    res = runPylint([
        '--output-format=parseable',
        '--load-plugins=pylint_django',
        '--disable=django-installed-checker,django-model-checker',
        '--load-plugins=pylint_flask'] +
        rcfile_option +
        rules,
        files,
        cwd,
        python_version)
    return parseResult(res)

def runTool(configFile, srcDir):
    configFile = os.path.normpath(configFile) if configFile else None
    srcDir = os.path.normpath(srcDir) if srcDir else None
    configuration = readConfiguration(configFile, srcDir)
    with tempfile.TemporaryDirectory() as tempdir:
        if configuration.rcfile is not None:
            rcfile_path = os.path.join(tempdir, 'rcfile')
            with open(rcfile_path, 'w') as rcfile:
                print(configuration.rcfile, file=rcfile)
        else:
            rcfile_path = None
        res = []
        filesWithPath = [os.path.join(srcDir,f) for f in configuration.files]
        for chunk in chunks(filesWithPath, 10):
            res.extend(runPylintWith(configuration.rules, chunk, srcDir, configuration.python_version, rcfile_path))
        for result in res:
            if result.filename.startswith(srcDir):
                result.filename = os.path.relpath(result.filename, srcDir)
        return res

def resultsToJson(results):
    return os.linesep.join([toJson(res) for res in results])

if __name__ == '__main__':
    with timeout(getTimeout(os.environ.get('TIMEOUT_SECONDS') or '')):
        try:
            results = runTool('/.codacyrc', '/src')
            print(resultsToJson(results))
        except Exception:
            traceback.print_exc()
            sys.exit(1)
