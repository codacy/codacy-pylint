##Patterns: C0123
def simple_positives():
    ##Info: C0123
    type(42) is int
    ##Info: C0123
    type(42) is not int
    ##Info: C0123
    type(42) == int
    ##Info: C0123
    type(42) != int
    ##Info: C0123
    type(42) in [int]
    ##Info: C0123
    type(42) not in [int]

def simple_inference_positives():
    alias = type
    ##Info: C0123
    alias(42) is int
    ##Info: C0123
    alias(42) is not int
    ##Info: C0123
    alias(42) == int
    ##Info: C0123
    alias(42) != int
    ##Info: C0123
    alias(42) in [int]
    ##Info: C0123
    alias(42) not in [int]

def type_creation_negatives():
    type('Q', (object,), dict(a=1)) is int
    type('Q', (object,), dict(a=1)) is not int
    type('Q', (object,), dict(a=1)) == int
    type('Q', (object,), dict(a=1)) != int
    type('Q', (object,), dict(a=1)) in [int]
    type('Q', (object,), dict(a=1)) not in [int]

def invalid_type_call_negatives(**kwargs):
    type(bad=7) is int
    type(bad=7) is not int
    type(bad=7) == int
    type(bad=7) != int
    type(bad=7) in [int]
    type(bad=7) not in [int]
    type('bad', 7) is int
    type('bad', 7) is not int
    type('bad', 7) == int
    type('bad', 7) != int
    type('bad', 7) in [int]
    type('bad', 7) not in [int]
    type(**kwargs) is int
    type(**kwargs) is not int
    type(**kwargs) == int
    type(**kwargs) != int
    type(**kwargs) in [int]
    type(**kwargs) not in [int]

def local_var_shadowing_inference_negatives():
    type = lambda dummy: 7
    type(42) is int
    type(42) is not int
    type(42) == int
    type(42) != int
    type(42) in [int]
    type(42) not in [int]

def parameter_shadowing_inference_negatives(type):
    type(42) is int
    type(42) is not int
    type(42) == int
    type(42) != int
    type(42) in [int]
    type(42) not in [int]

def deliberate_subclass_check_negatives(b):
    type(42) is type(b)
    type(42) is not type(b)
