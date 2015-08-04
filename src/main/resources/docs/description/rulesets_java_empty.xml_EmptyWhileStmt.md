Empty While Statement finds all instances where a while statement does nothing. 

If it is a timing loop, then you should use **Thread.sleep()** for it; if it is a while loop that does a lot in the exit expression, rewrite it to make it clearer.

##Example

  	void bar(int a, int b) { 
  		while (a == b) { 
  			// empty! 
  		} 
  	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/empty.html#EmptyWhileStmt)
