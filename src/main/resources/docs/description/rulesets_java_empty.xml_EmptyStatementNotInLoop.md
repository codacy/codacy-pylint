An empty statement (or a semicolon by itself) that is not used as the sole body of a ‘*for*’ or ‘*while*’ loop is probably a bug. 

It could also be a double semicolon, which has no purpose and therefore should be removed.

##Example

  	public void doit() { 
  		// this is probably not what you meant to do 
  		; 
  		// the extra semicolon here this is not necessary 
  		System.out.println(\"look at the extra semicolon\");; 
  	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/empty.html#EmptyStatementNotInLoop)
