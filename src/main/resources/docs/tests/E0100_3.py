##Patterns: E0100

class Generator(object):

    ##Err: E0100
    def __init__(self):
        yield from range(10)
