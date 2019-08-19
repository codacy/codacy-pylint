##Patterns: E1003

class OldStyleClass(object):
    def __init__(self): pass

class AnotherOldStyleClass(OldStyleClass):
    def __init__(self):
        ##Err: E1003
        super(OldStyleClass, self).__init__()
