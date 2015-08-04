//#Patterns: rulesets_java_design.xml_InstantiationToGetClass
public class Foo {

    public void Bar() {

 
  //#Info: rulesets_java_design.xml_InstantiationToGetClass
Class c = new String().getClass();

  // with this:
Class c2 = String.class;


    }
}

