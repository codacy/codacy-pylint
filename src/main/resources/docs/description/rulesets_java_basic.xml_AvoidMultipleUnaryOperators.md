The use of multiple unary operators may be problematic, and/or confusing.
Ensure that the intended usage is not a bug, or consider simplifying the expression.

Ex:

    // These are typo bugs, or at best needlessly complex and confusing:
    int i = - -1;
    int j = + - +1;
    int z = ~~2;
    boolean b = !!true;
    boolean c = !!!true;

    // These are better:
    int i = 1;
    int j = -1;
    int z = 2;
    boolean b = true;
    boolean c = false;

    // And these just make your brain hurt:
    int i = ~-2;
    int j = -~7;

[Source](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/basic.html#AvoidMultipleUnaryOperators)
