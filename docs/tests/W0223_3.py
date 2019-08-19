##Patterns: W0223

import abc


class AbstractClass(metaclass=abc.ABCMeta):

    @abc.abstractmethod
    def abstract_method(self):
        pass

##Warn: W0223
class ConcreteClass(AbstractClass):
    pass

