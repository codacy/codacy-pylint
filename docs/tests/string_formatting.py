##Patterns: W1301,W1303,W1304,W1305,W1306,W1307
import os
from missing import Missing

__revision__ = 1

class Custom(object):
    """ Has a __getattr__ """
    def __getattr__(self, _):
        return self

class Test(object):
    """ test format attribute access """
    custom = Custom()
    ids = [1, 2, 3, [4, 5, 6]]

class Getitem(object):
    """ test custom getitem for lookup access """
    def __getitem__(self, index):
        return 42

class ReturnYes(object):
    """ can't be properly infered """
    missing = Missing()

def log(message, message_type="error"):
    """ Test """
    return message

def print_good():
    """ Good format strings """
    "{0} {1}".format(1, 2)
    "{0!r:20}".format("Hello")
    "{!r:20}".format("Hello")
    "{a!r:20}".format(a="Hello")
    "{pid}".format(pid=os.getpid())
    str("{}").format(2)
    "{0.missing.length}".format(ReturnYes())
    "{1.missing.length}".format(ReturnYes())
    "{a.ids[3][1]}".format(a=Test())
    "{a[0][0]}".format(a=[[1]])
    "{[0][0]}".format({0: {0: 1}})
    "{a.test}".format(a=Custom())
    "{a.__len__}".format(a=[])
    "{a.ids.__len__}".format(a=Test())
    "{a[0]}".format(a=Getitem())
    "{a[0][0]}".format(a=[Getitem()])
    "{[0][0]}".format(["test"])
    # these are skipped
    "{0} {1}".format(*[1, 2])
    "{a} {b}".format(**{'a': 1, 'b': 2})
    "{a}".format(a=Missing())

def pprint_bad():
    """Test string format """
    "{{}}".format(1)
    "{} {".format()
    "{} }".format()
    ##Warn: W1305
    "{0} {}".format(1, 2)
    ##Warn: W1304
    "{a}".format(a=1, c=2)
    ##Warn: W1303
    "{} {a}".format(1, 2)
    "{} {}".format(1)
    "{} {}".format(1, 2, 3)
    ##Warn: W1303
    "{a}".format()
    "{} {}".format(a=1, b=2)
    ##Warn: W1303
    "{a}".format(1)
    ##Warn: W1303
    "{0} {1} {a}".format(1, 2, 3)
    ##Warn: W1306
    "{a.ids.__len__.length}".format(a=Test())
    ##Warn: W1307
    "{a.ids[3][400]}".format(a=Test())
    ##Warn: W1307
    "{a.ids[3]['string']}".format(a=Test())
    ##Warn: W1307
    "{[0][1]}".format(["a"])
    ##Warn: W1307
    "{[0][0]}".format(((1, )))
    ##Warn: W1303
    "{b[0]}".format()
    ##Warn: W1307
    "{a[0]}".format(a=object)
    log("{}".format(2, "info"))
    ##Warn: W1306
    "{0.missing}".format(2)
    "{0} {1} {2}".format(1, 2)
    "{0} {1}".format(1, 2, 3)
    "{0} {a}".format(a=4)
    "{[0]} {}".format([4])
    "{[0]} {}".format([4], 5, 6)
    ##Warn: W1301
    "%(PARG_1)d %(PARG_2)d" % {'PARG_1': 1, 'PARG_2':2, 'PARG_3':3}

def test_kwargs_not_false_positive(*args, **kwargs):
    """ Test that using kwargs does not emit a false
    positive.
    """
    'Hello John Doe {0[0]}'.format(args)
    'Hello {0[name]}'.format(kwargs)

def test_parent_of_format_is_undeducible():
    """ Test that the string format checker skips
    format nodes which don't have a string as a parent
    (but a subscript, name etc).
    """
    name = 'qwerty'
    ret = {'comment': ''}
    ret['comment'] = 'MySQL grant {0} is set to be revoked'
    ret['comment'] = ret['comment'].format(name)
    return ret, name

def test_nested_format_fields():
    """ Test nested format fields. """
    '{0:>{1}}'.format(42, 24)
    '{0:{a[1]}} {a}'.format(1, a=[1, 2])
    '{:>{}}'.format(42, 24)
    '{0:>{1}}'.format(42)
    '{0:>{1}}'.format(42, 24, 54)
    ##Warn: W1303
    '{0:{a[1]}}'.format(1)
    ##Warn: W1306
    '{0:{a.x}}'.format(1, a=2)

def test_duplicate_manual_position_arguments():
    """ Test a regression using duplicate manual position arguments. """
    '{0} {1} {0}'.format(1, 2)
    '{0} {1} {0}'.format(1)

def test_mixed_manual_positional_arguments_with_attribute_access_arguments():
    """ Test a regression using mixed manual position arguments
    and attribute access arguments.
    """
    '{0}{1[FOO]}'.format(123, {'FOO': 456})
    '{0}{1[FOO]}'.format(123, {'FOO': 456}, 321)
    '{0}{1[FOO]}'.format(123)

def issue_namedtuple_subclass_works_as_expected():
    """
    Check that using a namedtuple subclass doesn't crash when
    trying to infer EmptyNodes (resulted after mocking the
    members of namedtuples).
    """
    from collections import namedtuple

    class Crash(namedtuple("C", "foo bar")):
        """ Looking for attributes in __str__ will crash,
        because EmptyNodes can't be infered.
        """
        def __str__(self):
            return "{0.foo}: {0.bar}".format(self)
    return Crash

def issue_format_can_be_assigned():
    """
    Check that the format method can be assigned to a variable, ie:
    """
    fmt = 'test {} {}'.format
    fmt('arg1')
    fmt('arg1', 'arg2')
    fmt('arg1', 'arg2', 'arg3')

def test_ignore_objects_coming_from_arguments():
    """
    Ignore any object coming from an argument.
    """
    class SomeClass(object):
        """ empty docstring. """
        def __init__(self, opts=None):
            self.opts = opts

        def dunc(self, arg):
            """Don't try to analyze this."""
            return "A{0}{1}".format(arg, self.opts)

        def func(self):
            """Don't try to analyze the following string."""
            return 'AAA{0[iface]}BBB{0[port]}'.format(self.opts)

    return SomeClass

def test_mix_positional_with_attribute_access():
    """
    Mix positional arguments, `{0}`, with positional
    arguments with attribute access, `{0.__x__}`.
    """
    data = "{0.__class__.__name__}: {0}".format(42)
    data2 = "{0[0]}: {0}".format([1])
    return (data, data2)
