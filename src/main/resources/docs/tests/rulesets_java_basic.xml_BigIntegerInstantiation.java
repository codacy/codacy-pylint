//#Patterns: rulesets_java_basic.xml_BigIntegerInstantiation
public class Foo {
    public void bar() {

//#Warn: rulesets_java_basic.xml_BigIntegerInstantiation
	BigInteger bi = new BigInteger(1);		
//#Warn: rulesets_java_basic.xml_BigIntegerInstantiation
	BigInteger bi2 = new BigInteger("0");
	BigInteger bi3 = new BigInteger(0.0);	
	BigInteger bi4;
//#Warn: rulesets_java_basic.xml_BigIntegerInstantiation
	bi4 = new BigInteger(0);				
    

	BigInteger zbi = BigInteger.ONE;		

	zbi4 = BigInteger.ZERO;				

    }
}

