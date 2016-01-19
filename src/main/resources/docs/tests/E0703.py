##Patterns: E0703

import socket
import unknown

class ExceptionSubclass(Exception):
    pass

def test():
    ##Err: E0703
    raise IndexError from 1
    raise IndexError from None
    raise IndexError from ZeroDivisionError
    ##Err: E0703
    raise IndexError from object()
    raise IndexError from ExceptionSubclass
    raise IndexError from socket.error
    raise IndexError() from None
    raise IndexError() from ZeroDivisionError
    raise IndexError() from ZeroDivisionError()
    ##Err: E0703
    raise IndexError() from object()
    raise IndexError() from unknown
