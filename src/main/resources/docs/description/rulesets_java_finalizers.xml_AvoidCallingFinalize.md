The method Object.finalize() is called by the **garbage collector** on an object when garbage collection determines that there are no more references to the object. It should not be invoked by application logic.

Ex:

	void foo() { 
		Bar b = new Bar(); 
		b.finalize(); 
	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/finalizers.html#AvoidCallingFinalize)
