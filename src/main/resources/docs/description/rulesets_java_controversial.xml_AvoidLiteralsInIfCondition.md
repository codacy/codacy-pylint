Avoid using hard-coded literals in conditional statements. By declaring them as static variables or private members with descriptive names **maintainability and readability** are enhanced. **WARNING** This does not apply to '*magic numbers*' like -1 and 0.

##Examples

  	private static final int MAX_NUMBER_OF_REQUESTS = 10; 
  	public void checkRequests() { 
  		if (i == 10) { 
  			// magic number, buried in a method 
  			doSomething(); 
  		} 
  		
  		if (i == MAX_NUMBER_OF_REQUESTS) { 
  			// preferred approach 
  			doSomething(); 
  		} 
  	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/controversial.html#AvoidLiteralsInIfCondition)
