//#Patterns: rulesets_java_unnecessary.xml_UselessOverridingMethod

public class Foo {
    
    //#Warn: rulesets_java_unnecessary.xml_UselessOverridingMethod
    public String foo() {
        return super.foo();
    }
}
