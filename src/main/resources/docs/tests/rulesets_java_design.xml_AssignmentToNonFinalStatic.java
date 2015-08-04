//#Patterns: rulesets_java_design.xml_AssignmentToNonFinalStatic

public class StaticField {
    //#Err: rulesets_java_design.xml_AssignmentToNonFinalStatic
   static int x;
   public FinalFields(int y) {
    x = y; // unsafe
   }
}
