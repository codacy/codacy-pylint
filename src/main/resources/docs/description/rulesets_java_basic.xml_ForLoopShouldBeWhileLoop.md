Some for loops can be simplified to while loops, this makes them more concise.

Ex:

    public class Foo {
        void bar() {
            for (;true;) true; // No Init or Update part, may as well be: while (true)
        }
    }

[Source](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/basic.html#ForLoopShouldBeWhileLoop)