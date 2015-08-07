##Patterns: E1303

##Err: E1303
print "%(arg1)s" % "wrong"
print "%(arg1)s" % {"arg1":"ok"}
