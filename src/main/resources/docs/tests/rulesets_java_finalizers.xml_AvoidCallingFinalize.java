//#Patterns: rulesets_java_finalizers.xml_AvoidCallingFinalize

public class Foo {
    void bar() {
        Bar b = new Bar();
        //#Warn: rulesets_java_finalizers.xml_AvoidCallingFinalize
        b.finalize();
    }
}
