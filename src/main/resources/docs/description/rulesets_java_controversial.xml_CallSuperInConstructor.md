It is a good practice to call super() in a constructor.
Calling super() explicitly shows that the code is not evoking another super constructed (one with arguments).
Using this convention explicitly shows the code intention.

Ex:

    public class Foo extends Bar{
      public Foo() {
       // call the constructor of Bar
       super();
      }
     public Foo(int code) {
      // do something with code
       this();
       // no problem with this
      }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/controversial.html#CallSuperInConstructor)
