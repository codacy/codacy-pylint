Tests for null should not use the equals() method. The ‘==’ operator should be used instead.
If you try to invoke .equals(null) on a non-initialized object you will get a NullPointerException.

Ex:

    String x = "foo";

    if (x.equals(null)) { // bad form
        doSomething();
        }

    if (x == null) { 	// preferred
        doSomething();
        }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#EqualsNull)
