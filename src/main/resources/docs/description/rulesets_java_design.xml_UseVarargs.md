Java 5 introduced the varargs parameter declaration for methods and constructors.
This syntactic sugar provides flexibility for users of these methods and constructors, allowing them to avoid having to deal with the creation of an array.

Ex:

    public class Foo {
       public void foo(String s, Object[] args) {
          // Do something here...
       }

       public void bar(String s, Object... args) {
          // Ahh, varargs tastes much better...
       }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#UseVarargs)
