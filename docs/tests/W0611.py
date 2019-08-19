##Patterns: W0611
##Warn: W0611
import xml.etree
##Warn: W0611
import xml.sax
##Warn: W0611
import os.path as test
##Warn: W0611
from sys import argv as test2
##Warn: W0611
from sys import flags
##Warn: W0611
from collections import deque, Counter
DATA = Counter()

##Warn: W0611
from fake import SomeName, SomeOtherName
class SomeClass(object):
    SomeName = SomeName
    SomeOtherName = 1
    SomeOtherName = SomeOtherName

from never import __all__
