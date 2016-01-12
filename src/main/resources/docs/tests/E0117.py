##Patterns:E0117
def test():
    def parent():
        a = 42
        def stuff():
            nonlocal a

    c = 24
    def parent2():
        a = 42
        def stuff():
            def other_stuff():
                nonlocal a
                nonlocal c

b = 42
def func():
    def other_func():
        ##Err: E0117
        nonlocal b

class SomeClass(object):
    ##Err: E0117
    nonlocal x

    def func(self):
        ##Err: E0117
        nonlocal some_attr

   