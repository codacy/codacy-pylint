Avoid returning from a finally block, this can discard exceptions.

If the catch block raises and exception, the finally block will catch and discard the new exception.
The return statement will return as everything is ok, and the exception is discarded.

    public class Bar {
        public String foo() {
            try {
                throw new Exception( "My Exception" );
            } catch (Exception e) {
                throw e;
            } finally {
                return "A. O. K."; // return not recommended here
            }
        }
    }

[Source](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/basic.html#ReturnFromFinallyBlock)