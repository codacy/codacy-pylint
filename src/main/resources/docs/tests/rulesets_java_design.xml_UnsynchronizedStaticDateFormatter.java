//#Patterns: rulesets_java_design.xml_UnsynchronizedStaticDateFormatter
public class Foo {
    private static final SimpleDateFormat sdf = new SimpleDateFormat();
    void bar() {
//#Err: rulesets_java_design.xml_UnsynchronizedStaticDateFormatter
        sdf.format(); // poor, no thread-safety
    }
    synchronized void foo() {
        sdf.format(); // preferred
    }
}

