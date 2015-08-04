Switch statements are indended to be used to support complex branching behaviour.
Using a switch for only a few cases is ill-advised, since switches are not as easy to understand as if-then statements.
In these cases use the if-then statement to increase code readability.

Ex:

    public class Foo {
        public void bar() {
            switch (condition) {
                case ONE:
                    instruction;
                    break;
                default:
                    break; // not enough for a 'switch' stmt, a simple 'if' stmt would have been more appropriate
            }
        }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#TooFewBranchesForASwitchStatement)
