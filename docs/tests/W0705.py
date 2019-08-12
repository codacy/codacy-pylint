##Patterns: W0705
try:
    1 / 0
except ValueError:
    pass
##Warn: W0705
except ValueError:
    pass
##Warn: W0705
except (TypeError, ValueError):
    pass

