##Patterns: W0604, W0602, W0601
from __future__ import print_function

##Info: W0604
global CSTE
print(CSTE)

def other():
    ##Info: W0602
    global HOP
    print(HOP)


def define_constant():
    ##Info: W0601
    global SOMEVAR
    SOMEVAR = 2
