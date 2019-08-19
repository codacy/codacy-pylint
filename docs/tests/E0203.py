##Patterns: E0203

class Test():
    def __init__(self):
        ##Err: E0203
        self.a = self.b
        ##Err: E0203
        self.a += self.b
        self.b = 1