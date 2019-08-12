##Patterns: E0241

##Err: E0241
class Duplicates(str, str):
    pass


class Alpha(str):
    pass


class NotDuplicates(Alpha, str):
    """The error should not be emitted for this case, since the
    other same base comes from the ancestors."""
