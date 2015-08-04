//#Patterns: rulesets_java_empty.xml_EmptyTryBlock

public class Foo {
    public void bar() {
        //#Warn: rulesets_java_empty.xml_EmptyTryBlock
        try {  } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
