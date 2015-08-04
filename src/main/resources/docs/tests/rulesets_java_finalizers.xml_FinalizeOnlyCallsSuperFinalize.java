//#Patterns: rulesets_java_finalizers.xml_FinalizeOnlyCallsSuperFinalize

public class Foo {
    protected void finalize() {
        //#Warn: rulesets_java_finalizers.xml_FinalizeOnlyCallsSuperFinalize
        super.finalize();
    }
}
