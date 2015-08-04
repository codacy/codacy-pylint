Do not use protected methods in final classes since they cannot be subclassed.
This should only be allowed in final classes that extend other classes with protected methods (whose visibility cannot be reduced).
Clarify your intent by using private or package access modifiers instead (package access modifier is attained by using no modifier keyword).

Ex:

    public class Foo {

        private int bar() {}
        protected int baz() {} // Foo cannot be subclassed, and doesn't extend anything, so is baz() really private or package visible?

        //private int baz() {} //class accessible
        //int baz() {} //package accessible
    }


[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#AvoidProtectedMethodInFinalClassNotExtending)
