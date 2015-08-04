Java will initialize fields with known default values so any explicit initialization of those same defaults is redundant and results in a larger class file (approximately three additional bytecode instructions per field).

Ex:

	public class Foo { 
		// examples of redundant initializers 
		boolean b = false;
		byte by = 0; 
		short s = 0; 
		char c = 0; 
		int i = 0; 
		long l = 0;
		
		// all possible float literals 
		float f = .0f;  
		double d = 0d; 
		
		// all possible double literals 
		Object o = null; 
		MyClass mca[] = null;  

	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/optimizations.html#RedundantFieldInitializer)
