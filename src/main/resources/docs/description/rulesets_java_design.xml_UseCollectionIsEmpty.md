The isEmpty() method on java.util.Collection is provided to determine if a collection has any elements.
Comparing the value of size() to 0 does not convey intent as well as the isEmpty() method.

Ex:

    public class Foo {
        void good() {
            List foo = getList();
            if (foo.isEmpty()) {
                // blah
            }
        }

        void bad() {
            List foo = getList();
                if (foo.size() == 0) {
                    // blah
                }
            }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#UseCollectionIsEmpty)
