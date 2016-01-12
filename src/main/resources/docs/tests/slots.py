##Patterns: E0236,E0238

from collections import deque

def func():
    if True:
        return ("a", "b", "c")
    else:
        return [str(var) for var in range(3)]


class NotIterable(object):
    def __iter_(self):
        """ do nothing """

class Good(object):
    __slots__ = ()

class SecondGood(object):
    __slots__ = []

class ThirdGood(object):
    __slots__ = ['a']

class FourthGood(object):
    __slots__ = ('a%s' % i for i in range(10))

class FifthGood(object):
    __slots__ = "a"

class SixthGood(object):
    __slots__ = deque(["a", "b", "c"])

class SeventhGood(object):
    __slots__ = {"a": "b", "c": "d"}

class Bad(object):
    __slots__ = list

##Err: E0238
class SecondBad(object):
    __slots__ = 1

class ThirdBad(object):
    ##Err: E0236
    __slots__ = ('a', 2)

##Err: E0238
class FourthBad(object):
    __slots__ = NotIterable()

class FifthBad(object):
    ##Err: E0236
    __slots__ = ("a", "b", "")

class PotentiallyGood(object):
    __slots__ = func()

class PotentiallySecondGood(object):
    __slots__ = ('a', deque.__name__)

class PotentiallyThirdGood(object):
    __slots__ = deque.__name__
