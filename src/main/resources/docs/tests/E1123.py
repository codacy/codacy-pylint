##Patterns: E1123

def my_method(arg1, arg2):
    print arg1 + arg2

def main():
    ##Err: E1123
    my_method(arg1=1, arg3=3)
    my_method(arg1=1, arg2=3)
