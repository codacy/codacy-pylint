//#Patterns: rulesets_java_design.xml_MissingStaticMethodInNonInstantiatableClass

//#Warn: rulesets_java_design.xml_MissingStaticMethodInNonInstantiatableClass
public class Foo {
  private Foo() {}
  void foo() {}
}

public class Bar {
    public Bar() {}
    void foo() {}
}