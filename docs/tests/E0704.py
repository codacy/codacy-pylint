##Patterns: E0704
try:
    ##Err: E0704
    raise
except Exception:
    pass

try:
    pass
except Exception:
    raise

try:
    pass
except Exception:
    if 1 == 2:
        raise

def test():
    try:
        pass
    except Exception:
        def chest():
            try:
                pass
            except Exception:
                raise
        raise

def test1():
    try:
        if 1 > 2:
            def best():
                ##Err: E0704
                raise
    except Exception:
        pass
    ##Err: E0704
    raise
##Err: E0704
raise

try:
    pass
finally:
    # This might work or might not to, depending if there's
    # an error raised inside the try block. But relying on this
    # behaviour can be error prone, so we decided to warn
    # against it.
    ##Err: E0704
    raise


class A(object):
    try:
        pass
    except Exception:
        raise
    ##Err: E0704
    raise

# This works in Python 2, but the intent is nevertheless
# unclear. It will also not work on Python 3, so it's best
# not to rely on it.
exc = None
try:
    1/0
except ZeroDivisionError as exc:
    pass
if exc:
    ##Err: E0704
    raise

# Don't emit if we're in ``__exit__``.
class ContextManager(object):
    def __enter__(self):
        return self
    def __exit__(self, *args):
        raise
