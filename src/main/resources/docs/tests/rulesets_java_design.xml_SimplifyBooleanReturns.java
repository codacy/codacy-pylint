//#Patterns: rulesets_java_design.xml_SimplifyBooleanReturns

public class Foo {

    public boolean isBarEqualTo(int x) {

        //#Err: rulesets_java_design.xml_SimplifyBooleanReturns
        if (bar == x) {         // this bit of code...
            return true;
        } else {
            return false;
        }
    }

    public boolean isBarEqualTo2(int x) {

        return bar == x;    // can be replaced with this
    }
}