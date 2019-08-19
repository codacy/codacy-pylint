##Patterns: E1310

''.strip('yo')
''.strip()

##Err: E1310
''.strip('http://')
##Err: E1310
''.lstrip('http://')
##Err: E1310
''.rstrip('http://')
