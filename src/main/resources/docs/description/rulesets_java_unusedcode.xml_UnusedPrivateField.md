Private fields are fields only accessible inside the class.

Any private field not used inside the class is useless, so it should be removed.

Ex:

    public class Something {

         private static int FOO = 2; // Unused
         private int i = 5; // Unused
         private int j = 6;

         public int addOne() {
           return j++;
        }
    }


[Source](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/unusedcode.html#UnusedPrivateField)