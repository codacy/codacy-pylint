//#Patterns: rulesets_java_unnecessary.xml_UselessParentheses

public class Foo {
    private int _bar1;

    public void bar() {
        //#Warn: rulesets_java_unnecessary.xml_UselessParentheses
        _bar1 = Integer.valueOf((n));

        _bar1 = Integer.valueOf(n); //the correct way
    }
}
