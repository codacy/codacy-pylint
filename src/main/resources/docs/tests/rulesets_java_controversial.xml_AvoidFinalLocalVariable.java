//#Patterns: rulesets_java_controversial.xml_AvoidFinalLocalVariable

public class Foo {

    public void bar() {
        
        //#Err: rulesets_java_controversial.xml_AvoidFinalLocalVariable
        final String simple;
        String hello;
    }

}
