Method-level synchronization can cause problems when new code is added to the method.
Block-level synchronization helps to ensure that only the code that needs synchronization gets it.

Ex:

    public class Foo {
      // Try to avoid this:
      synchronized void foo() {
      }
      // Prefer this:
      void bar() {
        synchronized(this) {
        }
      }

      // Try to avoid this for static methods:
      static synchronized void fooStatic() {
      }

      // Prefer this:
      static void barStatic() {
        synchronized(Foo.class) {
        }
      }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#AvoidSynchronizedAtMethodLevel)
