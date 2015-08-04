//#Patterns: rulesets_java_empty.xml_EmptyFinallyBlock

public class Foo {
    public void bar() {
        try {
            int x = 2;
        } 
        //#Warn: rulesets_java_empty.xml_EmptyFinallyBlock
        finally {
            //empty!
        }
    }
}
