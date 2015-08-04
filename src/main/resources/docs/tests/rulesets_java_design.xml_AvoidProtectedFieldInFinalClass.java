//#Patterns: rulesets_java_design.xml_AvoidProtectedFieldInFinalClass


public final class Bar {
  private int x;

  //#Err: rulesets_java_design.xml_AvoidProtectedFieldInFinalClass
  protected int y;  // bar cannot be subclassed, so is y really private or package visible?
  Bar() {}
}
