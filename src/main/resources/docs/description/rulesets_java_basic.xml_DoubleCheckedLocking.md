Partially created objects can be returned by the Double Checked Locking pattern when used in Java.
An optimizing JRE may assign a reference to the baz variable before it creates the object the reference is intended to point to.

For more details refer to: http://www.javaworld.com/javaworld/jw-02-2001/jw-0209-double.html or http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html

Ex:

    public class Foo {
        Object baz;
        Object bar() {
            if (baz == null) { // baz may be non-null yet not fully created
                synchronized(this) {
                    if (baz == null) {
                        baz = new Object();
                    }
                }
            }
            return baz;
        }
    }

[Source](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/basic.html#DoubleCheckedLocking)