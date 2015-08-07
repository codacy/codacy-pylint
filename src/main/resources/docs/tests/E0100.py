##Patterns: E0100

class Test():

    ##Err: E0100
    def __init__(self):
        for i in (1, 2, 3):
            yield i * i