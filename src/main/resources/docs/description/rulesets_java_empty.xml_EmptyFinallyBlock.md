Empty finally blocks serve no purpose and should be removed.

##Example

  	public class Foo { 
  		public void bar() { 	
  			try { 
  				int x=2; 
  			} finally { 
  				// empty! 
  			} 
  		} 
  	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/empty.html#EmptyFinallyBlock)
