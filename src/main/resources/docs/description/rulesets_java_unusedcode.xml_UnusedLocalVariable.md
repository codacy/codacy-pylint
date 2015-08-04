Local variables are fields only accessible inside the block where they were created.

Local variables not used are useless and should be removed.

Ex:

     public class Foo {
        public void doSomething() {
            int i = 5; // Unused
        }
     }


[Source](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/unusedcode.html#UnusedLocalVariable)
