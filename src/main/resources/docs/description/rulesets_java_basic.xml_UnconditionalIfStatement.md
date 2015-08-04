Do not use if statements whose conditionals are always true or always false.
An if(true) is like unused code, in the sense that the code block after will always run.

Ex:

    public class Foo {
        public void close() {
            if (true) {		// fixed conditional, not recommended
                // ...
            }
        }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/basic.html#UnconditionalIfStatement)
