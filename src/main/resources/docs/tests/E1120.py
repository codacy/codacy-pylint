##Patterns: E1120

def my_method(arg1, arg2):
    print arg1 + arg2

def main():
    ##Err: E1120
    my_method(1)
    my_method(1,3)
