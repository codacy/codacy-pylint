##Patterns: E0712

class MyException(object):
    def __init__(self):
        return

class MyOtherException(BaseException):
    def __index__(self):
        return

def my_method():
    try:
        raise MyException
        raise MyOtherException
    ##Err: E0712
    except MyException:
        return
    except MyOtherException:
        return

my_method()
