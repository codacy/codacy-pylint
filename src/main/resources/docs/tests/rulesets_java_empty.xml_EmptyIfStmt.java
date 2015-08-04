//#Patterns: rulesets_java_empty.xml_EmptyIfStmt

public class Foo {
    public void bar(int x) {
        //#Warn: rulesets_java_empty.xml_EmptyIfStmt
        if(x == 0) {  }
    }
}
