##Patterns: E0302

class Test():
    def __init__(self):
        return

    ##Err: E0302
    def __exit__(self):
        return self
