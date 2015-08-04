Deeply nested if-then statements are harder to read and error-prone to maintain.
Consider a more Object Oriented approach (using objects to delegate responsibilities), or break part of the logic into functions.
The technique to break some logic into functions is called Extract Method, support for it can be found under Refactoring options on most IDEs.

Ex:

    public class Foo {
      public void bar(int x, int y, int z) {
        if (x>y) {
          if (y>z) {
            if (z==x) {
             // !! too deep
            }
          }
        }
      }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#AvoidDeeplyNestedIfStmts)
