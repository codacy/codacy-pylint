##Patterns: R0202
class MyClass(object):
    def __init__(self):
        pass

    def cmethod(cls):
        pass

    ##Info: R0202
    cmethod = classmethod(cmethod)

    if True:
        ##Info: R0202
        cmethod = classmethod(cmethod)

    @classmethod
    def my_second_method(cls):
        pass

    def other_method(cls):
        pass
    ##Info: R0202
    cmethod2 = classmethod(other_method)

def helloworld():
    """says hello"""


MyClass.new_class_method = classmethod(helloworld)

class MyOtherClass(object):
    """Some other class"""
    _make = classmethod(tuple.__new__)
