##Patterns: W0120

x = [1, 2]

while x:
    print("doing...")
    x.pop()
##Warn: W0120
else:
    print("done")

x = [2]

while x:
    print("doing...")
    x.pop()

x = [3]

while x:
    print("doing...")
    x.pop()
    break
else:
    print("done")

data = [1, 2]

for x in data:
    print("doing")
    if x == 1:
        print("found")
##Warn: W0120
else:
    print("done")

for x in data:
    print("doing")
    if x == 1:
        break
else:
    print("done")
