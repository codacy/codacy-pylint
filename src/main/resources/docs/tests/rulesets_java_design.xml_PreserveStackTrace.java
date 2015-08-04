//#Patterns: rulesets_java_design.xml_PreserveStackTrace

public class Foo {
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
    void bad() {
        try{
            Integer.parseInt("a");
        } catch (Exception e) {
			//#Info: rulesets_java_design.xml_PreserveStackTrace
            throw new Exception(e.getMessage());
        }
    }
}
