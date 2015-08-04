It is unclear which exceptions that can be thrown from the methods. 

It might be difficult to document and understand the vague interfaces so you should use either a class derived from RuntimeException or a checked exception.

##Example

	public void methodThrowingException() throws Exception {  }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/typeresolution.html#SignatureDeclareThrowsException)
