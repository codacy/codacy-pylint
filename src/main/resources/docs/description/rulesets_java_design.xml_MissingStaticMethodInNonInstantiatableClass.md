A class that has private constructors and does not have any static methods or fields cannot be used.

Ex:

    public class Foo {
      private Foo() {}
      void foo() {}
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#MissingStaticMethodInNonInstantiatableClass)
