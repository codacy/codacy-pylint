##Patterns: E0115

##Err: E0115
def bad():
    nonlocal missing
    global missing

def good():
    nonlocal missing
    def test():
        global missing
    return test
