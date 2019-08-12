from codacy_pylint import *
import unittest
import tempfile

class ResultTest(unittest.TestCase):
    def test_toJson(self):
        result = Result("/src/file.py", "message", "id", 80)
        res = toJson(result)
        expected = '{"filename": "/src/file.py", "line": 80, "message": "message", "patternId": "id"}'
        self.assertEqual(res, expected)

class PyLintTest(unittest.TestCase):
    def test_version(self):
        expected = "2.3.1"
        out = runPylint(["--version"])
        result = out.split(os.linesep)[0][len("Pylint "):]
        self.assertEqual(result, expected)

    def test_chunks(self):
        l = ["file1", "file2"]
        expected = [["file1", "file2"]]
        out = chunks(l, 10)
        self.assertEqual(out, expected)

        expected2 = [["file1"], ["file2"]]
        out2 = chunks(l, 1)
        self.assertEqual(expected2, out2)

    def test_readConfiguration(self):
        with tempfile.TemporaryDirectory() as directory:
            codacyrcPath = directory + "/.codacyrc"
            with open(codacyrcPath, "w") as codacyrc:
                print('{"tools":[{"name":"PyLint (Python 3)","patterns":[{"patternId":"C0111"}]}],"files":["C0111.py"]}', file=codacyrc)
            
            expectedRules = ['--disable=all', '--enable=C0111']
            expectedFiles = ['C0111.py']
            
            (rules, files) = readConfiguration(codacyrcPath, "docs/test")
            self.assertEqual(expectedRules, rules)
            self.assertEqual(expectedFiles, files)

if __name__ == '__main__':
    unittest.main()