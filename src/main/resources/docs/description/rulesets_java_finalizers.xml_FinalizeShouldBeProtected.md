When overriding the finalize(), the new method **should be set as protected**. If made public, other classes may invoke it at inappropriate times which can severely harm your program.

##Example


  	public void finalize() { //this isn't good
  		 // do something 
  	}
  	
  	protected void finalize() { //much better
  		//do something
  	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/finalizers.html#FinalizeShouldBeProtected)
