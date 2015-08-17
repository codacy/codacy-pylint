##Patterns: R0903
##Info: R0903
class MyClass(object):
    i = 123
    x = 456
    z = 555
    def bar(self, baz):
        print i

class MyOtherClass(object):
    i = 123
    x = 456
    z = 555
    def bar(self, baz):
        return i
    def doStuff(self, baz):
        return i+x
    def doMoreStuff(self, baz):
        return x+z