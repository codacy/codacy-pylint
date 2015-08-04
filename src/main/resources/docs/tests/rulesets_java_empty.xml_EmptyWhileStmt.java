//#Patterns: rulesets_java_empty.xml_EmptyWhileStmt

public class Foo {
    public void bar(int a, int b) {
        //#Warn: rulesets_java_empty.xml_EmptyWhileStmt
        while(a == b) {
            //do nothing!
        }

        while(a < b) { //correct usage
            a++;
        }
    }
}
