##Patterns: E1111

def my_method():
    print "here"

def main():
    num = 1
    ##Err: E1111
    num = my_method()
