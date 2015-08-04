//#Patterns: rulesets_java_optimizations.xml_UseArrayListInsteadOfVector

public class Foo {
    public void bar() {
        //#Warn: rulesets_java_optimizations.xml_UseArrayListInsteadOfVector
        Collection c1 = new Vector();

        Collection c2 = new ArrayList(); //much better performance
    }
}
