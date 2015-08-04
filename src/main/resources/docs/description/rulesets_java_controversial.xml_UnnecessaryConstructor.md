This rule detects when a constructor is not necessary; i.e., when there is only one constructor, its public, has an empty body, and takes no arguments.

Ex:

  	public class Foo { 
  		public Foo() { } 
  	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/controversial.html#UnnecessaryConstructor)
