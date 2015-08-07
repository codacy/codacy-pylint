##Patterns: W0150

def run(input):
    result = 0
    try:
        result = 3 / input
    except ZeroDivisionError:
        raise ValueError("input must not be 0")
    finally:
        print("input={0}".format(input))
        if 0 == input:
            ##Warn: W0150
            return result - 1
    return result * 2
