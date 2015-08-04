Fields, formal arguments, or local variable names that are too long can make the code difficult to follow.

Ex:

    public class Something {
        int reallyLongIntName = -3;  			// VIOLATION - Field
        public static void main( String argumentsList[] ) { // VIOLATION - Formal
            int otherReallyLongName = -5; 		// VIOLATION - Local
            for (int interestingIntIndex = 0;	// VIOLATION - For
                 interestingIntIndex < 10;
                 interestingIntIndex ++ ) {
        }
    }

[Source](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/naming.html#LongVariable)

