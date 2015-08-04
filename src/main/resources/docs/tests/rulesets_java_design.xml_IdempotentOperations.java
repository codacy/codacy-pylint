//#Patterns: rulesets_java_design.xml_IdempotentOperations
      
public class Foo {
 public void bar() {
  int x = 2;
  //#Info: rulesets_java_design.xml_IdempotentOperations
  x = x;
 }
}

