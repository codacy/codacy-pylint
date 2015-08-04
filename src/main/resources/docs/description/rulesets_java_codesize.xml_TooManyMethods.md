A class with too many methods is probably a good suspect for refactoring, in order to reduce its complexity and find a way to have more fine grained objects. 

Making your Classes have less methods that do generic stuff will make your code more modular and therefore reusable in the long term.

##Example
	public class Foo {
		public void bar1() { //inside it does something }
		[.........]
		public void barn() { //it will more stuff}
	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/codesize.html#TooManyMethods)
