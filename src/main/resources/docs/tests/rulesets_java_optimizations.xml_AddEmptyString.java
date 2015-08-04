//#Patterns: rulesets_java_optimizations.xml_AddEmptyString

public class Foo {

    public void bar() {
        
        //#Warn: rulesets_java_optimizations.xml_AddEmptyString
        String s = "" + 123;
        String t = Integer.toString(456);
    }

}
