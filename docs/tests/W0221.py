##Patterns: W0221

class Parent(object):

    def method(self, arg):
        return arg


class Child(Parent):

    ##Warn: W0221
    def method(self, arg, arg1):
        return arg, arg1

