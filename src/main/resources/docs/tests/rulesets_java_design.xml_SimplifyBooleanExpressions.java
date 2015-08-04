//#Patterns: rulesets_java_design.xml_SimplifyBooleanExpressions

public class Bar {
    // can be simplified to
    // bar = isFoo();
    //#Err: rulesets_java_design.xml_SimplifyBooleanExpressions
    private boolean bar = (isFoo() == true);

    public isFoo() { return false;}
}