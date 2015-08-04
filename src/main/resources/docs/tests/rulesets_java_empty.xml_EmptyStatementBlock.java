//#Patterns: rulesets_java_empty.xml_EmptyStatementBlock

public class Foo {
    private int _bar;

    public void lorem(int var) {
        //#Warn: rulesets_java_empty.xml_EmptyStatementBlock
        {}

        {_bar = var;} //this is okay
    }
}
