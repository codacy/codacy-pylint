Private methods are only accessible inside the class where they were defined.

Any private method not used inside the class is useless, should be removed.

Ex:

     
    public class Something
    {
        private void foo() // unused
        {
            System.out.println(\"This method is never invoked!\");
        }
    }


[Source](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/unusedcode.html#UnusedPrivateMethod)
