By explicitly commenting empty constructors it is easier to distinguish between intentional (commented) and unintentional empty constructors.
It gives the reader a better understanding of the meaning of that empty constructor.

Ex:

    public class Bar {

        //Is this intentional? Why is this empty?
        public Bar() {
        }
    }

    public class Foo {

        //This is a better approach
        public Foo() {
            // This constructor is intentionally empty. (or some motive) Nothing special is needed here.
        }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#UncommentedEmptyConstructor)
