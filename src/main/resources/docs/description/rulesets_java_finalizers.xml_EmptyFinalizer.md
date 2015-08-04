Empty finalize methods serve **no purpose** and therefore it should be removed.

##Example

  	public class Foo { 
  	
  		//It serves no purpose
  		protected void finalize() { } 
  	
  	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/finalizers.html#EmptyFinalizer)
