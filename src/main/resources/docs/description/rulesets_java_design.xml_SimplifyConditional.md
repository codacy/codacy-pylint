No need to check for null before an instanceof.
The instanceof keyword returns false when given a null argument.

Ex:

    class Foo {
      void bar(Object x) {
        if (x != null && x instanceof Bar) {
          // just drop the "x != null" check
        }
      }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#SimplifyConditional)
