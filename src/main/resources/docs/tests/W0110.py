##Patterns: W0110


my_list = [-3, -2, -1, 1, 2]
value = 0

r1 = [i for i in my_list if i > value]
r2 = [i for i.value in some_list]

##Warn: W0110
r3 = filter(lambda x: x > value, my_list)
##Warn: W0110
r4 = map(lambda x: x.value, some_list)

# Marking this file as Python 2
raise Exception, "lala"