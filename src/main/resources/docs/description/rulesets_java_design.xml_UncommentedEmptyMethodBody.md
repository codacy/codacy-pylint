By explicitly commenting empty method bodies it is easier to distinguish between intentional (commented) and unintentional empty methods.
It gives the reader a better understanding of the meaning of that empty method.

Ex:

    public class Foo {

        //Is this intentional? Why is this empty?
        public void bar() {
        }

        //This is a better approach
        public void good() {
            // This constructor is intentionally empty, because something something
        }
    }
[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#UncommentedEmptyMethodBody)
