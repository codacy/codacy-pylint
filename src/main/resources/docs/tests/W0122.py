##Patterns: W0122

code = compile('a = 1 + 2', '<string>', 'exec')
##Warn: W0122
exec code
print a
