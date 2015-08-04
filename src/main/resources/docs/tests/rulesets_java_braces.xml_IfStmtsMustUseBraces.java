//#Patterns: rulesets_java_braces.xml_IfStmtsMustUseBraces
public class Foo {

    public void bar() {

        //#Info: rulesets_java_braces.xml_IfStmtsMustUseBraces
        if (foo)	// not recommended
            x++;

        if (foo) {	// preferred approach
            x++;
        }
    }

}