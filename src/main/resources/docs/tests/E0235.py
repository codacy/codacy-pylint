##Patterns: E0235

class Test():
    def __init__(self):
        return

    ##Err: E0235
    def __exit__(self):
        return self

    def __exit__(self, type, value, traceback):
        return 1