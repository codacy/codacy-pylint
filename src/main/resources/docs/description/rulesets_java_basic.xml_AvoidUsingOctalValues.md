Integer literals starting with 0 are interpreted as an octal value.

A Integer literal starting with 0 may be misleading and introduce a bug,
if starts with 0, the rest of literal will be interpreted as an octal value.

Ex:

    int i = 012;	// set i with 10 not 12
    int j = 010;	// set j with 8 not 10
    k = i * j;		// set k with 80 not 120

[Source](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/basic.html#AvoidUsingOctalValues)
