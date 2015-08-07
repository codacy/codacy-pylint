##Patterns: E0103

def test():
    while True:
        break
    ##Err: E0103
    break

    for letter in 'Python':
        if letter == 'h':
            continue
    ##Err: E0103
    continue