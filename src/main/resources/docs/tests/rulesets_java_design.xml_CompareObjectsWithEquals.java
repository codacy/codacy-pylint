//#Patterns: rulesets_java_design.xml_CompareObjectsWithEquals
public class Foo {

    boolean soStuff(String a, String b) {
        //#Err: rulesets_java_design.xml_CompareObjectsWithEquals
        return a == b;
    }
}

