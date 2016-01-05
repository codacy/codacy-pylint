##Patterns: W0201

class SomeClass(object):

    def func(self):
        """Attribute is defined outside __init__"""
        ##Warn: W0201
        self._func = 42

