Don't create instances of already existing BigInteger (BigInteger.ZERO, BigInteger.ONE)
and for Java 1.5 onwards, BigInteger.TEN and BigDecimal (BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN)
From a code quality perspective, using BigDecimal.ZERO is preferable to new BigDecimal(0) as you avoid the extra instantiation and having a literal in your code.


Ex:

    BigInteger bi = new BigInteger(1);		// reference BigInteger.ONE instead
    BigInteger bi2 = new BigInteger("0");	// reference BigInteger.ZERO instead
    BigInteger bi3 = new BigInteger(0.0);	// reference BigInteger.ZERO instead
    BigInteger bi4;
    bi4 = new BigInteger(0);				// reference BigInteger.ZERO instead

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/basic.html#BigIntegerInstantiation)
