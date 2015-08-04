Empty initializers serve no purpose and therefore should be removed.

##Example

  	public class Foo { 
  		static {} //Delete this
  		{} //and this
  	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/empty.html#EmptyInitializer)
