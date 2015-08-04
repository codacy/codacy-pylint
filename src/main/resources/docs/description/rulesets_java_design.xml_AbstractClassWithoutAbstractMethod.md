An abstract class suggests an incomplete implementation, which is to be completed by subclasses implementing the abstract methods.
If the class is intended to be used as a base class only (not to be instantiated directly) a protected constructor can be provided prevent direct instantiation.

Ex:

    public abstract class Foo {
      void int method1() { ... }
      void int method2() { ... }
      // consider using abstract methods or removing
      // the abstract modifier and adding protected constructors
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#AbstractClassWithoutAbstractMethod)
