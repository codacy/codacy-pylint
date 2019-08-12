##Patterns: W1501

open('foo.bar', 'w', 2)
##Warn: W1501
open('foo.bar', 'rw')
##Warn: W1501
open(name='foo.bar', buffering=10, mode='rw')
##Warn: W1501
open(mode='rw', name='foo.bar')
open('foo.bar', 'U+')
open('foo.bar', 'rb+')
##Warn: W1501
open('foo.bar', 'Uw')
##Warn: W1501
open('foo.bar', 2)
open('foo.bar', buffering=2)
WRITE_MODE = 'w'
##Warn: W1501
open('foo.bar', 'U' + WRITE_MODE + 'z')
##Warn: W1501
open('foo.bar', 'wU')
open('foo.bar', 'r+b')
open('foo.bar', 'r+')
open('foo.bar', 'w+')
##Warn: W1501
open('foo.bar', 'rx')
open('foo.bar', 'Ur')
open('foo.bar', 'rU')
open('foo.bar', 'rUb')
open('foo.bar', 'rUb+')
open('foo.bar', 'rU+b')
open('foo.bar', 'r+Ub')
open('foo.bar', 'ab+')
open('foo.bar', 'a+b')
open('foo.bar', 'U+b')
open('foo.bar', '+Ub')
open('foo.bar', 'b+U')
open('foo.bar', 'Urb+')
open('foo.bar', 'Ur+b')
