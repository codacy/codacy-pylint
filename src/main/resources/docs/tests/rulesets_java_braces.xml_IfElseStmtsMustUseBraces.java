//#Patterns: rulesets_java_braces.xml_IfElseStmtsMustUseBraces
public class Foo {

    public void bar() {


        // this is OK
        if (foo) {
            x = x + 1;
        }
        else {
            x = x - 1;
        }

        if (foo)
            //#Info: rulesets_java_braces.xml_IfElseStmtsMustUseBraces
            x = x+1;
        else
            //#Info: rulesets_java_braces.xml_IfElseStmtsMustUseBraces
            x = x-1;
    }

}