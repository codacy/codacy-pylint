//#Patterns: rulesets_java_optimizations.xml_LocalVariableCouldBeFinal

public class Bar {
    public void foo() {
        //#Err: rulesets_java_optimizations.xml_LocalVariableCouldBeFinal
        String txtA = "a";
        
        final String txtB = "b"; 
    }
}
