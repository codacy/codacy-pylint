Avoid the use temporary objects when converting primitives to Strings. Use the static conversion methods on the wrapper classes instead. This will save you an object and performance wise enhance your code.

##Example

	public String convert(int x) { 
		String foo = new Integer(x).toString(); // this wastes an object 
		
		return Integer.toString(x); // preferred approach 
	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/unnecessary.html#UnnecessaryConversionTemporary)
