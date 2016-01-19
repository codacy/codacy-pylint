##Patterns: E0301

import six

class FirstGoodIterator(object):
    def __iter__(self):
        for index in range(10):
            yield index

class SecondGoodIterator(object):
    def __iter__(self):
        return self

    def __next__(self):
        """ Infinite iterator, but still an iterator """
        return 1

    def next(self):
        """Same as __next__, but for Python 2."""
        return 1

class ThirdGoodIterator(object):
    def __iter__(self):
        return SecondGoodIterator()

class FourthGoodIterator(object):
    def __iter__(self):
        return iter(range(10))


class IteratorMetaclass(type):
    def __next__(cls):
        return 1

    def next(cls):
        return 2


@six.add_metaclass(IteratorMetaclass)
class IteratorClass(object):
    """Iterable through the metaclass."""


class FifthGoodIterator(object):
    def __iter__(self):
        return IteratorClass

class FileBasedIterator(object):
    def __init__(self, path):
        self.path = path
        self.file = None

    def __iter__(self):
        if self.file is not None:
            self.file.close()
        self.file = open(self.path)
        # self file has two infered values: None and <instance of 'file'>
        # we don't want to emit error in this case
        return self.file


class FirstBadIterator(object):
    ##Err: E0301
    def __iter__(self):
        return []

class SecondBadIterator(object):

    ##Err: E0301
    def __iter__(self):
        return self

class ThirdBadIterator(object):
    ##Err: E0301
    def __iter__(self):
        return SecondBadIterator()

class FourthBadIterator(object):
    ##Err: E0301
    def __iter__(self):
        return ThirdBadIterator
