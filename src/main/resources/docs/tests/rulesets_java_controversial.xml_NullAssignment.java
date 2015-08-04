//#Patterns: rulesets_java_controversial.xml_NullAssignment

public class Foo {
    
    public void bar() {
        Object x = null;

        x = new Object();

        //#Warn: rulesets_java_controversial.xml_NullAssignment
        x = null;
    }
}
