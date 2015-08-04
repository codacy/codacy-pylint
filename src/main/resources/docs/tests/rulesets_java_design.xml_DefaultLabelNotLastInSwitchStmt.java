//#Patterns: rulesets_java_design.xml_DefaultLabelNotLastInSwitchStmt
   
public class Foo {
  void bar(int a) {
      //#Err: rulesets_java_design.xml_DefaultLabelNotLastInSwitchStmt
   switch (a) {
    case 1:  // do something
       break;
    default:  // the default case should be last, by convention
       break;
    case 2:
       break;
   }
  }
}   
       

