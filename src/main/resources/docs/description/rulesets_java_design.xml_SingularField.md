Fields whose scopes are limited to just single methods do not rely on the containing object to provide them to other methods.
They may be better implemented as local variables within those methods.

Ex:

    public class Foo {
        private int x;  // no reason to exist at the Foo instance level
        public void foo(int y) {
         x = y + 5;
         return x;
        }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#SingularField)
