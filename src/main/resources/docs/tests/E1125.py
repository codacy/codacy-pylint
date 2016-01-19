##Patterns: E1125

def function(*, foo):
    print(foo)

function(foo=1)

foo = 1
##Err: E1125
function(foo)
##Err: E1125
function(1)
