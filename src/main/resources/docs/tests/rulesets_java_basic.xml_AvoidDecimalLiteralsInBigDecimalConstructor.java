//#Patterns: rulesets_java_basic.xml_AvoidDecimalLiteralsInBigDecimalConstructor

public class Foo {

    //#Err: rulesets_java_basic.xml_AvoidDecimalLiteralsInBigDecimalConstructor
    BigDecimal bd1 = new BigDecimal(1.123);        // loss of precision, this would trigger the rule

    BigDecimal bd2 = new BigDecimal("1.123");    // preferred approach

    BigDecimal bd3 = new BigDecimal(12);        // preferred approach, ok for integer values
}