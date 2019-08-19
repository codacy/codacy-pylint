##Patterns: E0108

class Test():
    ##Err: E0108
    def a(self, arg, arg):
        return 1

##Err: E0108
def test(a, a):
    return 1