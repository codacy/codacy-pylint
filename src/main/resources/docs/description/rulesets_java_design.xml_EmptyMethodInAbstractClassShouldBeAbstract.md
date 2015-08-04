Empty methods in an abstract class should be tagged as abstract.
This helps to remove their inappropriate usage by developers who should be implementing their own versions in the concrete subclasses.

Ex:

    public abstract class ShouldBeAbstract {

        public Object couldBeAbstract() {
            // Should be abstract method ?
            return null;
        }

        public void couldBeAbstract() {
        }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#EmptyMethodInAbstractClassShouldBeAbstract)
