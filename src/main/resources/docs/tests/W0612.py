##Patterns: W0612

def function(matches):
    ##Warn: W0612
    aaaa = 1
    index = -1
    for match in matches:
        index += 1
        yield match


def test_global():
    """ Test various assignments of global
    variables through imports.
    """
    global PATH, OS, collections, deque
    from os import path as PATH
    import os as OS
    import collections
    from collections import deque
    # make sure that these triggers unused-variable
    ##Warn: W0612
    from sys import platform
    ##Warn: W0612
    from sys import version as VERSION
    ##Warn: W0612
    import this
    ##Warn: W0612
    import re as RE
