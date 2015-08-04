//#Patterns: rulesets_java_design.xml_NonCaseLabelInSwitchStatement

public class Foo {
  void bar(int a) {
   switch (a) {
     case 1:
       // do something
       break;
       //#Err: rulesets_java_design.xml_NonCaseLabelInSwitchStatement
     mylabel: // this is legal, but confusing!
       break;
     default:
       break;
    }
  }
}

