A method argument that is never re-assigned within the method can be declared final.

It is considered to be good practice to be explicit on whether you wan't (or not) the variable to change it's value when the method is running.

Ex:


		public void foo1(String param) { 
		// do stuff with param never assigning it 
		} 
		
		public void foo2(final String param) { 
		// better, do stuff with param never assigning it 
		}
		
Other Example:

		public void  setTest(String test) {
			test = test
		}
If you forget the 'this' keyword on a setter the variable you wan't does not get set. However if you used the final keyword on the parameter then the bug would be caught at compile time.

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/optimizations.html#MethodArgumentCouldBeFinal)
