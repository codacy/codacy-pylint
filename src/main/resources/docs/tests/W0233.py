##Patterns: W0233

class Parent(object):
    pass


class Other(object):
    pass


class Child(Parent):

    def __init__(self):
        ##Err: W0233
        Other.__init__()
