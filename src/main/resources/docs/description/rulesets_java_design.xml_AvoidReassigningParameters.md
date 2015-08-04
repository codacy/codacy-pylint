Reassigning values to incoming parameters is not recommended. Use temporary local variables instead.
It can be confusing for other people that may want to use the original parameter value later in that code, and may not notice that it has already been change.

Ex:

    public class Foo {
      private void foo(String bar) {
        bar = "something else";
      }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#AvoidReassigningParameters)
