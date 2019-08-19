##Patterns: W0223

class AbstractClass(object):

    def abstract_method(self):
        raise NotImplementedError


##Warn: W0223
class ConcreteClass(AbstractClass):
    pass

