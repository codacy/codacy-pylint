##Patterns: W0105

"""Test for statements without effects."""

##Info: W0105
"""inline doc string should use a separated message"""

##Info: W0105
"""inline doc string should use a separated message"""


class ClassLevelAttributeTest(object):
    """ test attribute docstrings. """

    good_attribute_docstring = 24
    """ class level attribute docstring is fine either. """
    second_good_attribute_docstring = 42
    # Comments are good.

    # empty lines are good, too.
    """ Still a valid class level attribute docstring. """

    def test(self):
        """ invalid attribute docstrings here. """
        self.val = 42
        ##Info: W0105
        """ this is an invalid attribute docstring. """
