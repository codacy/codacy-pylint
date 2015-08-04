If a final field is assigned to a compile-time constant, it could be made static, thus saving overhead in each object at runtime.
If its value is defined at compile time, then there is no reason to copy it for every object of the class where it was declared.

Ex:

    public class Foo {
      public final int BAR = 42; // this should be static
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#FinalFieldCouldBeStatic)