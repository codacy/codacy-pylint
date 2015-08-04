Use opposite operator instead of negating the whole expression with a logic complement operator.
It makes code easier to read and understand.

Ex:

    public boolean bar(int a, int b) {

        if (!(a == b)) // use !=
             return false;

        if (!(a < b)) // use >=
             return false;

        return true;

    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#LogicInversion)