##Patterns: E0240

class Str(str):
    pass


##Err: E0240
class Inconsistent(str, Str):
    pass
