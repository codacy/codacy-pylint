//#Patterns: rulesets_java_design.xml_ImmutableField


public class Foo {
  //#Err: rulesets_java_design.xml_ImmutableField
  private int x; // could be final
  public Foo() {
      x = 7;
  }
  public void foo() {
     int a = x + 2;
  }
}

