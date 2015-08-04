This rule uses the NCSS (Non-Commenting Source Statements) algorithm to determine the number of lines of code for a given constructor. 

**NCSS ignores comments**, and counts actual statements. Using this algorithm, **lines of code that are split are counted as one**.

##Example

	public class Foo extends Bar { 
		public Foo() { 
			super(); 
			
			
			//this constructor only has 1 NCSS lines 
			super.foo(); 
		} 	
	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/codesize.html#NcssConstructorCount)
