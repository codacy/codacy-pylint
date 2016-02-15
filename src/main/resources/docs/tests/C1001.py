##Patterns: C1001

##Info: C1001
class OldStyleClass():
    def __init__(self):
        return

class NewStyleClass(object):
    def __init__(self):
        # Marking the file as Python 2.
        raise Exception, "lala"
