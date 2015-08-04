//#Patterns: rulesets_java_unnecessary.xml_UnnecessaryConversionTemporary

public class Foo {
    public void bar(int x) {
        //#Warn: rulesets_java_unnecessary.xml_UnnecessaryConversionTemporary
        String aux = new Integer(x).toString();

        return Integer.toString(x);
    }
}
