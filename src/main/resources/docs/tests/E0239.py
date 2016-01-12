##Patterns: E0239


from missing import Missing

if 1:
    Ambiguous = None
else:
    Ambiguous = int

class Empty(object):
    pass

def return_class():
    return Good3

##Err: E0239
class Bad(1):
    pass

##Err: E0239
class Bad1(lambda abc: 42):
    pass

##Err: E0239
class Bad2(object()):
    pass

##Err: E0239
class Bad3(return_class):
    pass

##Err: E0239
class Bad4(Empty()):
    pass

class Good(object):
    pass

class Good1(int):
    pass

class Good2(type):
    pass

class Good3(type(int)):
    pass

class Good4(return_class()):
    pass

class Good5(Good4, int, object):
    pass

class Good6(Ambiguous):
    pass

class Unknown(Missing):
    pass

class Unknown1(Good5 if True else Bad1):
    pass

##Err: E0239
class NotInheritableBool(bool):
    pass


##Err: E0239
class NotInheritableRange(range):
    pass


##Err: E0239            
class NotInheritableSlice(slice):
    pass

##Err: E0239
class NotInheritableMemoryView(memoryview):
    pass
