Empty Catch Block finds instances where an exception is caught, but nothing is done. In most circumstances, this swallows an exception which should either be **acted on or reported**.

##Example

  	public void doSomething() { 
  		try { 
  			FileInputStream fis = new FileInputStream("/tmp/bugger"); 
  		} catch (IOException ioe) { 
  			// not good 
  		} 
  	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/empty.html#EmptyCatchBlock)
