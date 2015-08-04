//#Patterns: rulesets_java_finalizers.xml_FinalizeShouldBeProtected

public class Foo {
    //#Err: rulesets_java_finalizers.xml_FinalizeShouldBeProtected
    private void finalize() {  }
    //#Err: rulesets_java_finalizers.xml_FinalizeShouldBeProtected
    public void finalize() {  }

    protected void finalize() {  }
}
