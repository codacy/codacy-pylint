//#Patterns: rulesets_java_basic.xml_BrokenNullCheck

public class Foo {

    public String bar(String string) {
        // should be &&
        //#Err: rulesets_java_basic.xml_BrokenNullCheck
        if (string != null || !string.equals(""))
            return string;
        // should be ||
        //#Err: rulesets_java_basic.xml_BrokenNullCheck
        if (string == null && string.equals(""))
            return string;
    }
}