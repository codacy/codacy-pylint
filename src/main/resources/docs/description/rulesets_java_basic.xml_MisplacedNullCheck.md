The null check here is misplaced. If the variable is null a NullPointerException will be thrown.
Either the check is useless (the variable will never be null) or it is incorrect.

Ex:

    public class Foo {
        void bar() {
            if (a.equals(baz) && a != null) {}
            }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/basic.html#MisplacedNullCheck)
