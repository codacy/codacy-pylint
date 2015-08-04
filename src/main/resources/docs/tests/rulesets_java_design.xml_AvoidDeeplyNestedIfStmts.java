//#Patterns: rulesets_java_design.xml_AvoidDeeplyNestedIfStmts


public class Foo {
  public void bar(int x, int y, int z) {
    if (x>y) {
      if (y>z) {
      //#Err: rulesets_java_design.xml_AvoidDeeplyNestedIfStmts
        if (z==x) {
         // !! too deep
        }
      }
    }
  }
}
