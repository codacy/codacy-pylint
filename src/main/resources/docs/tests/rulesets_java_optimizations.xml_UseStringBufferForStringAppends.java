//#Patterns: rulesets_java_optimizations.xml_UseStringBufferForStringAppends

public class Foo {
    
    public void bar() {
        String a;
        a = "foo";
        //#Warn: rulesets_java_optimizations.xml_UseStringBufferForStringAppends
        a+=" bar";

        a = new StringBuilder("foo");
        a.append(" bar");
    }

}

