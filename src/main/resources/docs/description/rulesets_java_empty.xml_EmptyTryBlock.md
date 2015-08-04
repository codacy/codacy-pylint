Empty try blocks have no use whatsoever so avoid writing tries unless you have a specific use for them

##Example

  	public class Foo { 
  		public void bar() { 
  			try { } catch (Exception e) { 
  			e.printStackTrace(); 
  			} 
  		} 
  	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/empty.html#EmptyTryBlock)
