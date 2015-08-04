//#Patterns: rulesets_java_finalizers.xml_FinalizeDoesNotCallSuperFinalize

public class Foo {
    protected void finalize() {
      //#Err: rulesets_java_finalizers.xml_FinalizeDoesNotCallSuperFinalize
      doSomething();
    }
}

public class Bar {
    protected void finalize() {
        super.finalize();
    }
}
