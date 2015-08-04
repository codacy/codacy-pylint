Parameters are passed to methods so they can be used during the method execution.

Any private method not used inside the class, should be removed.

Ex:

    public class Foo {
        private void bar(String howdy) {
            // howdy is not used
        }
    }

[Source](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/unusedcode.html#UnusedFormalParameter)
