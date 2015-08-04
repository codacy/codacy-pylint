//#Patterns: rulesets_java_unusedcode.xml_UnusedPrivateField

public class UnusedPrivateField {
    //#Warn: rulesets_java_unusedcode.xml_UnusedPrivateField
    private int bla;

    private static int foo = 1;

    public static void test() {
        return foo;
    }

}
