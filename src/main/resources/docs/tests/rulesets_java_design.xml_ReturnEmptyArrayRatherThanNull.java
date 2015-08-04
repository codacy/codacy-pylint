//#Patterns: rulesets_java_design.xml_ReturnEmptyArrayRatherThanNull

public class Example {
    // Not a good idea...
    //#Err: rulesets_java_design.xml_ReturnEmptyArrayRatherThanNull
    public int[] badBehavior() {
                   // ...
        return null;
    }

    // Good behavior
    public String[] bonnePratique() {
      //...
     return new String[0];
    }
}