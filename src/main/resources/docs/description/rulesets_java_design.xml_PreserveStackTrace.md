Throwing a new exception from a catch block without passing the original exception into the new exception will cause the original stack trace to be lost.
A incorrect stack trace will make it very difficult to debug effectively.

Ex:

    public class Foo {
        
        void bad() {
            try{
                Integer.parseInt("a");
            } catch (Exception e) {
                //the new exception should receive e instead of e.gotMessage()
                throw new Exception(e.getMessage());
            }
        }

        void good() {
            try{
                Integer.parseInt("a");
            } catch (Exception e) {
                throw new Exception(e); // first possibility to create exception chain
            }
            try {
                Integer.parseInt("a");
            } catch (Exception e) {
                throw (IllegalStateException)new IllegalStateException().initCause(e); // second possibility to create exception chain.
            }
        }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#PreserveStackTrace)
