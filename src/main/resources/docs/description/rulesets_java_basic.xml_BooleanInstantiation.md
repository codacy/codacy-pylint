Avoid instantiating Boolean objects; you can reference Boolean.TRUE, Boolean.FALSE, or call Boolean.valueOf() instead.
From a code quality perspective, using Boolean.TRUE is preferable to new Boolean("true"), as you avoid the extra instantiation.

Ex:

    Boolean bar = new Boolean("true");		// unnecessary creation, just reference Boolean.TRUE;
    Boolean buz = Boolean.valueOf(false);	// ...., just reference Boolean.FALSE;

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/basic.html#BooleanInstantiation)
