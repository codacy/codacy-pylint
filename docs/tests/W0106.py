##Patterns: W0106

foo = list()


def sum(a, b):
    c = a + b
    return c


bar_list = [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]

##Warn: W0106
[foo.append(sum(i, x)) for i, x in enumerate(bar_list)]

foo = [sum(i, x) for i, x in enumerate(bar_list)]
