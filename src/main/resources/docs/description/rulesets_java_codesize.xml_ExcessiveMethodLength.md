When methods are excessively long this usually indicates that **the method is doing more than its name/signature might suggest**. They also become challenging for others to digest since excessive scrolling causes readers to lose focus. Try to reduce the method length by creating helper methods and removing any copy/pasted code.

##Example

	public void doSomething() { 
		System.out.println("Hello world!"); 
		System.out.println("Hello world!"); 
		// 98 copies omitted for brevity.
		//Don't do this! 
	}
	
	//Do this instead
	
	public void doSomething() {
		helper1();
		helper2();
		//...
	}
	
	public void helper1() {
		//insert code
	}
	
	public void helper2() {
		//insert code
	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/codesize.html#ExcessiveMethodLength)
