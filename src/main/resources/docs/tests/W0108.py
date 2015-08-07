##Patterns: W0108

def foo():
    return 42

##Warn: W0108
f = lambda: foo()  # should be f = foo()

h = lambda (bar): bar + 1
