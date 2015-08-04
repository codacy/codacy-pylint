If the finalize() is implemented, its last action should **always** be to call super.finalize.

##Example

  	protected void finalize() { 
  		something();
  		super.finalize(); //the right way
  	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/finalizers.html#FinalizeDoesNotCallSuperFinalize)
