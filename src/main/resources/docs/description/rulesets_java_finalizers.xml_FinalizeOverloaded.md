Methods named finalize() should not have parameters. It is confusing and most likely an attempt to overload Object.finalize(). 

##Example

  	public class Foo { 
  		// this is confusing and probably a bug 
  		protected void finalize(int a) { } 
  		
  		//this is better
  		protected void finalize() { }
  	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/finalizers.html#FinalizeOverloaded)
