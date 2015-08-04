//#Patterns: rulesets_java_naming.xml_MisleadingVariableName
public class Foo {
    private int m_foo; // OK

    //#Info: rulesets_java_naming.xml_MisleadingVariableName
    public void bar(String m_baz) {  // Bad
      //#Info: rulesets_java_naming.xml_MisleadingVariableName
      int m_boz = 42; // Bad
    }
}