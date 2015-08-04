//#Patterns: rulesets_java_braces.xml_WhileLoopsMustUseBraces
public class Foo {

    public void bar() {
        //#Info: rulesets_java_braces.xml_WhileLoopsMustUseBraces
        while (true)	// not recommended
            x++;

        while (true) {	// preferred approach
            x++;
        }
    }

}