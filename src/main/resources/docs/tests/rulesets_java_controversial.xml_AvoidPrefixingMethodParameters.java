//#Patterns: rulesets_java_controversial.xml_AvoidPrefixingMethodParameters

public class Foo {
    
    //#Info: rulesets_java_controversial.xml_AvoidPrefixingMethodParameters
    public void notSoGoodCode(int inLeftOperand) {
        outRightOperand = 1;
    }

    public void betterCode(int function1, int function2) { 
      //insert more code here 
    }
}
