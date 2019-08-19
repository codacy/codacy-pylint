'''Ensure that pylint finds the exported methods from flask.ext.'''

from flask.ext import wtf
from flask.ext.wtf import Form

MYFORM = wtf.Form
