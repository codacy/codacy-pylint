//#Patterns: rulesets_java_clone.xml_ProperCloneImplementation

public class Foo {

    //#Err: rulesets_java_clone.xml_ProperCloneImplementation
    public Object clone() {
        return new Foo();
    }

    public Object clone() {
        return super.clone();
    }

}
