//#Patterns: rulesets_java_basic.xml_AvoidUsingOctalValues

public class Foo {

        //#Err: rulesets_java_basic.xml_AvoidUsingOctalValues
        int i = 012;    // set i with 10 not 12
        //#Err: rulesets_java_basic.xml_AvoidUsingOctalValues
        int j = 010;    // set j with 8 not 10

        int z = 30;
}