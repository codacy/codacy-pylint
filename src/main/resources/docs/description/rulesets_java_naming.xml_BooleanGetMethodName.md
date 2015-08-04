Methods that return boolean results should be named as predicate statements to denote this. I.e, isReady(), hasValues(), canCommit(), willFail(), etc.
Don't use the get prefix for these methods.

Ex:

    public boolean getFoo(); // bad
    public boolean isFoo(); // ok
    public boolean getFoo(boolean bar); // ok, unless checkParameterizedMethods=true

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/naming.html#BooleanGetMethodName)
