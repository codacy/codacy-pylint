//#Patterns: rulesets_java_design.xml_SimplifyConditional
public class Foo {
  void bar(Object x) {
  	//#Warn: rulesets_java_design.xml_SimplifyConditional
    if (x != null && x instanceof Bar) {
      // just drop the "x != null" check
    }
  }

}
public class Foo2 {
    public void Bar() {


    }
}

