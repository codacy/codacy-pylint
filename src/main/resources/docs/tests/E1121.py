##Patterns: E1121

def my_method(arg1, arg2):
    print arg1 + arg2

def main():
    ##Err: E1121
    my_method(1, 3, 4)
    my_method(1, 3)
