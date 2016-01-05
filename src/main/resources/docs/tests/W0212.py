##Patterns: W0212

class SomeClass(object):

    def __init__(self):
        self._private = 42


instance = SomeClass()
##Err: W0212
var = instance._private
