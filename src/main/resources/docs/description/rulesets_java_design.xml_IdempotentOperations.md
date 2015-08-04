Avoid idempotent operations - they have no effect. Idempotent operations are operations that can be applied multiple times without changing the result beyond the initial application.
Sometimes this is a bug, like the programmer mistyping similar names only differing by a letter or capitalization.
Code with no effects is like unused code, and should be fixed.

Ex:

    public class Foo {
     public void bar() {
      int x = 2;
      int y = 3;

      x = x; //does nothing
      y = y; //does nothing
     }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#IdempotentOperations)
