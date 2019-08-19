##Patterns: E1127

TESTLIST = [1, 2, 3]


def function1():
    ##Err: E1127
    return TESTLIST[id:id:]

def function2():
    ##Err: E1127
    return TESTLIST['0':'1':]

def function3():
    class NoIndexTest(object):
        pass

    ##Err: E1127
    return TESTLIST[NoIndexTest():1:]


def function4():
    return TESTLIST[0:0:0]

def function5():
    return TESTLIST[None:None:None]

def function6():
    class IndexTest(object):
        def __index__(self):
            return 0

    return TESTLIST[IndexTest():None:None]

def function7():
    class IndexType(object):
        def __index__(self):
            return 0

    class IndexSubType(IndexType):
        pass

    return TESTLIST[IndexSubType():None:None]

def function8():
    return TESTLIST[slice(1, 2, 3)]
