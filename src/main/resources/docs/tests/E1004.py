##Patterns: E1004

class OldStyleClass(object):
    def __init__(self): pass

class AnotherOldStyleClass(OldStyleClass):
    def __init__(self):
        ##Err: E1004
        super().__init__()
