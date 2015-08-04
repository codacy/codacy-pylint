//#Patterns: rulesets_java_basic.xml_DoubleCheckedLocking

public class Foo {
    Object baz;
    //#Err: rulesets_java_basic.xml_DoubleCheckedLocking
    Object bar() {
        if (baz == null) { // baz may be non-null yet not fully created
            synchronized(this) {
                if (baz == null) {
                    baz = new Object();
                }
            }
        }
        return baz;
    }
}