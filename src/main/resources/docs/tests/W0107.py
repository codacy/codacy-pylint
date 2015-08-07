##Patterns: W0107

class MyEmptyClass:
    def __init__(self):
        pass


def fib(n):
    a, b = 0, 1
    while a < n:
        print a,
        a, b = b, a + b
        ##Warn: W0107
        pass
