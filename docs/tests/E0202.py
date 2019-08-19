##Patterns: E0202

class Test1():
    def __init__(self):
        self.a = 1

class Test2(Test1):
    ##Err: E0202
    def a(self):
        return 2