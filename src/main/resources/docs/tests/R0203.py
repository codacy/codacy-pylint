##Patterns: R0203

class MyClass(object):
    def __init__(self):
        pass

    def smethod():
        pass
    ##Info: R0203
    smethod = staticmethod(smethod)

    if True:
        ##Info: R0203
        smethod = staticmethod(smethod)

    @staticmethod
    def my_second_method():
        pass

    def other_method():
        pass
    ##Info: R0203
    smethod2 = staticmethod(other_method)

def helloworld():
    pass


MyClass.new_static_method = staticmethod(helloworld)

class MyOtherClass(object):
    _make = staticmethod(tuple.__new__)
