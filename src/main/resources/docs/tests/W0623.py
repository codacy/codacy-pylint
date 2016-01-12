##Patterns: W0623

class MyError(Exception):
    pass


def some_function():
    """A function."""
    exc = None

    try:
        {}["a"]
    ##Warn: W0623
    except KeyError as RuntimeError:
        pass
    ##Warn: W0623    
    except KeyError as OSError:
        pass
    ##Warn: W0623
    except KeyError as MyError:
        pass
    except KeyError as exc:
        return exc
    except KeyError as exc1:
        return exc1
    except KeyError as FOO:
        return FOO
