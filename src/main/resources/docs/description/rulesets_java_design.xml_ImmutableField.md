Identifies private fields whose values never change once they are initialized either in the declaration of the field or by a constructor.
This helps in converting existing classes to becoming immutable ones.
When possible you should use immutable objects instead of mutable ones.

Working with immutable objects it's less error prone and has a lot of advantages.
You can be sure the value won't change, so you can pass it to functions without worrying.
Making an immutable object thread safe is easier.
Programmers won't introduce bugs by changing values that weren't suppose to change.

Ex:

    public class Foo {
      private int x; // should be final
      public Foo() {
          x = 7;
      }
      public void foo() {
         int a = x + 2;
      }
    }


[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#ImmutableField)
