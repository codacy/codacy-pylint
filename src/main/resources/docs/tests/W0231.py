##Patterns: W0231

class Parent(object):
    def __init__(self):
        self._var = 24


class Child(Parent):

    ##Err: W0231
    def __init__(self):
        self._var = 42
