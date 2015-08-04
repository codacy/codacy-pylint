//#Patterns: rulesets_java_controversial.xml_OnlyOneReturn

public class Foo {

    public void bar(int x) {
        if (x > 0) {
            //#Err: rulesets_java_controversial.xml_OnlyOneReturn
            return "hey";
        }

        return "hi";
    }

}
