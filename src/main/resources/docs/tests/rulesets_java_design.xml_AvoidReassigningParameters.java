//#Patterns: rulesets_java_design.xml_AvoidReassigningParameters

public class Foo {
//#Err: rulesets_java_design.xml_AvoidReassigningParameters
  private void foo(String bar) {
    bar = "something else";
  }

    private void foo2(String bar) {
        String barLocal = "something else";
    }
}