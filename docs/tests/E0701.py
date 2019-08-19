##Patterns: E0701

import sys

##Err: E0701
try:
    open("file")
except:
    sys.exit("could not open file")
except IOError:
    sys.exit("could not open file")
