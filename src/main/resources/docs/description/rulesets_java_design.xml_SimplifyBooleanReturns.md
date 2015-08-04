Avoid unnecessary if-then-else statements when returning a boolean.
The result of the conditional test can be returned instead.

Ex:

    public boolean isBarEqualTo(int x) {

        if (bar == x) {		 // this bit of code...
            return true;
        } else {
            return false;
        }
    }

    public boolean isBarEqualTo(int x) {

        return bar == x;	// can be replaced with this
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#SimplifyBooleanReturns)
