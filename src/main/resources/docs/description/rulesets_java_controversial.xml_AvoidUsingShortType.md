Java uses the ‘short’ type to reduce memory usage, not to optimize calculation. 

In fact, the JVM does not have any arithmetic capabilities for the short type it first has to convert the short into an int, do the proper calculation and only then convert the int back to a short. Thus any storage gains found through use of the ‘short’ type may be offset by adverse impacts on performance.

Ex:

  	public class UsingShort { 
  		private short doNotUseShort = 1; 
  		
  		public UsingShort() { 
  			short aux = 0; 
  			aux += doNotUseShort; 
  		} 
  	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/controversial.html#AvoidUsingShortType)
