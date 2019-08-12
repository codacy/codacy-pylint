##Patterns: E0603

from os import path
from collections import deque
from missing import Missing

__all__ = [
    'Dummy',
    ##Err: E0603
    '',
    Missing,
    SomeUndefined,
    ##Err: E0603
    'NonExistant',
    'path',
    ##Err: E0603
    'func',
    ##Err: E0603
    'inner',
    ##Err: E0603
    'InnerKlass',
    deque.__name__]


class Dummy(object):
    """A class defined in this module."""
    pass

DUMMY = Dummy()

def function():
    """Function docstring
    """
    pass

function()

class Klass(object):
    """A klass which contains a function"""
    def func(self):
        """A klass method"""
        inner = None
        return inner

    class InnerKlass(object):
        """A inner klass"""
        pass
