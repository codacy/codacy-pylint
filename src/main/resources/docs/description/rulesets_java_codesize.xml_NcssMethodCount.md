This rule uses the NCSS (Non-Commenting Source Statements) algorithm to determine the number of lines of code for a given method. **NCSS ignores comments, and counts actual statements**. 

Using this algorithm, lines of code that are split are counted as one.

##Example

	public class Foo extends Bar { 
		public int methd() { 
			super.methd(); 
			
			//this space is ignored
			
			
			//this method only has 1 NCSS lines 
			return 1; 
		} 
	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/codesize.html#NcssMethodCount)
