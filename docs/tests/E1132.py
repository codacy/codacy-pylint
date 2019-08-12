##Patterns: E1132
def test(a, b):
    return a, b

test(1, 24)
test(1, b=24, **{})
##Err: E1132
test(1, b=24, **{'b': 24})
