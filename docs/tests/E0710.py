##Patterns: E0710

class MyException(object):
    def __init__(self):
        return

class MyOtherException(BaseException):
    def __index__(self):
        return

def my_method():
    ##Err: E0710
    raise MyException
    raise MyOtherException
