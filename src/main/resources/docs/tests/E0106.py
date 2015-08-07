##Patterns: E0106

def test():
    for letter in 'Python':
        yield letter
        ##Err: E0106
        return 1
