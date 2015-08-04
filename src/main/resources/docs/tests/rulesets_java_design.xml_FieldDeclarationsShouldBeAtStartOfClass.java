//#Patterns: rulesets_java_design.xml_FieldDeclarationsShouldBeAtStartOfClass

public class HelloWorldBean {

  // Field declared before methods / inner classes - OK
  private String _thing;

  public String getMessage() {
    return "Hello World!";
  }

  // Field declared after methods / inner classes - avoid this
//#Info: rulesets_java_design.xml_FieldDeclarationsShouldBeAtStartOfClass
  private String _fieldInWrongLocation;
}

