//#Patterns: rulesets_java_braces.xml_ForLoopsMustUseBraces
public class Foo {

    public void bar() {
        //#Info: rulesets_java_braces.xml_ForLoopsMustUseBraces
        for (int i = 0; i < 42; i++)
            foo();


        for (int i = 0; i < 42; i++) {
            foo();
        }
    }

}