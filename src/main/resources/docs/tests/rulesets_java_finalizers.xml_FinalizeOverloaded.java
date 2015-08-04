//#Patterns: rulesets_java_finalizers.xml_FinalizeOverloaded

public class Foo {
    //#Err: rulesets_java_finalizers.xml_FinalizeOverloaded
    protected void finalize(int a) {  }

    protected void finalize() {  }
}
