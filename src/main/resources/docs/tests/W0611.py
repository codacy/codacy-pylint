##Patterns: W0611
##Info: W0611
import xml.etree
##Info: W0611
import xml.sax
##Info: W0611
import os.path as test
##Info: W0611
from sys import argv as test2
##Info: W0611
from sys import flags
##Info: W0611
from collections import deque, Counter
DATA = Counter()

##Info: W0611
from fake import SomeName, SomeOtherName
class SomeClass(object):
    SomeName = SomeName
    SomeOtherName = 1
    SomeOtherName = SomeOtherName

from never import __all__
