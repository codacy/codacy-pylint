Fields, local variables, or parameter names are very important for code reading and understanding.

Very short names are usually not helpful to the reader.

Ex:

    public class Something {
        private int q = 15;                             // field - too short
        public static void main( String as[] ) {        // formal arg - too short
            int r = 20 + q;                             // local var - too short
            for (int i = 0; i < 10; i++) {              // not a violation (inside 'for' loop)
                r += q;
            }
            for (Integer i : numbers) {                 // not a violation (inside 'for-each' loop)
                r += q;
            }
        }
    }

[Source](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/naming.html#ShortVariable)