##Patterns: E0110

import abc
import six
from abc import ABCMeta
from lala import Bala


@six.add_metaclass(abc.ABCMeta)
class GoodClass(object):
    pass

@six.add_metaclass(abc.ABCMeta)
class SecondGoodClass(object):
    pass

    def test(self):
        """ do nothing. """

@six.add_metaclass(abc.ABCMeta)
class ThirdGoodClass(object):
    pass

    def test(self):
        raise NotImplementedError()

@six.add_metaclass(abc.ABCMeta)
class FourthGoodClass(object):
    pass

@six.add_metaclass(abc.ABCMeta)
class BadClass(object):
    pass

    @abc.abstractmethod
    def test(self):
        """ do nothing. """

@six.add_metaclass(abc.ABCMeta)
class SecondBadClass(object):
    pass

    @property
    @abc.abstractmethod
    def test(self):
        """ do nothing. """

@six.add_metaclass(abc.ABCMeta)
class ThirdBadClass(object):

    @abc.abstractmethod
    def test(self):
        pass


class FourthBadClass(ThirdBadClass):
    pass


@six.add_metaclass(abc.ABCMeta)
class SomeMetaclass(object):
    pass

    @abc.abstractmethod
    def prop(self):
        pass


class FifthGoodClass(SomeMetaclass):
    """Don't consider this abstract if some attributes are
    there, but can't be inferred.
    """
    prop = Bala # missing


def main():
    """ do nothing """
    GoodClass()
    SecondGoodClass()
    ThirdGoodClass()
    FourthGoodClass()
    ##Err: E0110
    BadClass()
    ##Err: E0110
    SecondBadClass()
    ##Err: E0110
    ThirdBadClass()
    ##Err: E0110
    FourthBadClass()
