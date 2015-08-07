##Patterns: E1124

def my_method(arg1, arg2):
    print arg1 + arg2

def main():
    ##Err: E1124
    my_method(1, arg1=2)
    my_method(1, 2)