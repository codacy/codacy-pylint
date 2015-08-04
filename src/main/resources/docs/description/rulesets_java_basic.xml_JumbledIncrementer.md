Avoid jumbled loop incrementers - its usually a mistake, and is confusing even if intentional.

Ex:

    public class JumbledIncrementerRule1 {
        public void foo() {
            for (int i = 0; i < 10; i++) {			// only references 'i'
                for (int k = 0; k < 20; i++) {		// references both 'i' and 'k'
                    System.out.println("Hello");
                }
            }
        }
    }

 [Source](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/basic.html#JumbledIncrementer)