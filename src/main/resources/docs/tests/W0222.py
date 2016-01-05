##Patterns: W0222

class Parent(object):

    def method(self, arg, arg=1):
        return arg


class Child(Parent):

    ##Err: W0222
    def method(self, arg, arg1):
        return arg, arg1, arg2
