##Patterns: E1126

TESTLIST = [1, 2, 3]
TESTTUPLE = (1, 2, 3)
TESTSTR = '123'

def function1():
    ##Err: E1126
    return TESTLIST[id]

def function2():
    ##Err: E1126
    return TESTLIST[None]

def function3():
    ##Err: E1126
    return TESTLIST[float(0)]

def function4():
    ##Err: E1126
    return TESTLIST['0']

def function5():

    class NonIndexType(object):
        pass
    ##Err: E1126
    return TESTLIST[NonIndexType()]

def function6():
    ##Err: E1126
    return TESTTUPLE[None]

def function7():
    ##Err: E1126
    return TESTSTR[None]

def function8():
    class TupleTest(tuple):
        pass
    ##Err: E1126
    return TupleTest()[None]

# getitem tests with good indices
def function9():
    return TESTLIST[0]

def function10():
    return TESTLIST[int(0.0)]

def function11():
    return TESTLIST[slice(1, 2, 3)]

def function12():
    class IndexType(object):
        def __index__(self):
            return 0

    return TESTLIST[IndexType()]

def function13():
    class IndexType(object):
        def __index__(self):
            return 0

    class IndexSubType(IndexType):
        pass

    return TESTLIST[IndexSubType()]

def function14():
    return TESTTUPLE[0]

def function15():
    return TESTSTR[0]

def function16():
    class TupleTest(tuple):
        pass
    return TupleTest()[0]

def function17():
    class TupleTest(tuple):
        def __getitem__(self, index):
            return 0
    return TupleTest()[None]

def function18():
    class TupleTest(tuple):
        def __getitem__(self, index):
            return 0

    class SubTupleTest(TupleTest):
        pass

    return SubTupleTest()[None]

def function19():
    ##Err: E1126
    TESTLIST[None] = 0
    TESTLIST[0] = 0

def function20():
    ##Err: E1126
    del TESTLIST[None]
    del TESTLIST[0]

def function21():
    class ListTest(list):
        pass
    test = ListTest()

    ##Err: E1126
    test[None] = 0
    ##Err: E1126
    del test[None]

    test[0] = 0
    del test[0]

def function22():
    class ListTest(list):
        def __setitem__(self, key, value):
            pass
    test = ListTest()

    ##Err: E1126
    test[None][0] = 0
    ##Err: E1126
    del test[None]

    test[0][0] = 0 # getitem with int and setitem with int, no error
    test[None] = 0 # setitem overridden, no error
    test[0] = 0 # setitem with int, no error
    del test[0] # delitem with int, no error

def function23():
    """Get, set, and delete on a subclass of list that overrides __delitem__"""
    class ListTest(list):
        """Override delitem but not get or set"""
        def __delitem__(self, key):
            pass
    test = ListTest()

    ##Err: E1126
    test[None][0] = 0
    ##Err: E1126
    test[None] = 0

    test[0][0] = 0 # getitem with int and setitem with int, no error
    test[0] = 0 # setitem with int, no error
    del test[None] # delitem overriden, no error
    del test[0] # delitem with int, no error

def function24():
    """Get, set, and delete on a subclass of list that overrides __getitem__"""
    class ListTest(list):
        """Override gelitem but not del or set"""
        def __getitem__(self, key):
            pass
    test = ListTest()

    ##Err: E1126
    test[None] = 0
    ##Err: E1126
    del test[None]

    test[None][0] = 0 # getitem overriden, no error
    test[0][0] = 0 # getitem with int and setitem with int, no error
    test[0] = 0 # setitem with int, no error
    del test[0] # delitem with int, no error

# Teest ExtSlice usage
def function25():
    ##Err: E1126
    return TESTLIST[..., 0]

def function26():
    """Extended slice used with an object that implements __getitem__"""
    class ExtSliceTest(object):
        """Permit extslice syntax by implementing __getitem__"""
        def __getitem__(self, index):
            return 0
    return ExtSliceTest()[..., 0] # no error
