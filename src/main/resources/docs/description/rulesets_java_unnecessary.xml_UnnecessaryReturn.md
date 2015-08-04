Avoid the use of unnecessary return statements. Since it will not do anything to your code.

##Example

	public class Foo { 
		public void bar() { 
			int x = 42; 
			return; 
		} 
	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/unnecessary.html#UnnecessaryReturn)
