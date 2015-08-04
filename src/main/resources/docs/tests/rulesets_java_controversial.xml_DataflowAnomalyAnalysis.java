//#Patterns: rulesets_java_controversial.xml_DataflowAnomalyAnalysis

public class Foo {

    public void bar() {
        //#Info: rulesets_java_controversial.xml_DataflowAnomalyAnalysis
        int buz = 5;
        buz = 6;

        foo(buz);
        //#Info: rulesets_java_controversial.xml_DataflowAnomalyAnalysis
        buz = 2;
    }

}
