The NPath complexity of a method is the number of **acyclic execution paths through that method**. A threshold of 200 is generally considered the point where measures should be taken to reduce complexity and increase readability.

##Example

	void bar() { 
		// this is something more complex than it needs to be, 
		if (y) { // it should be broken down into smaller methods or functions 
		for (j = 0; j < m; j++) { 
			if (j > r) { 
				doSomething(); 
				while (f < 5 ) { 
					anotherThing(); 
					f -= 27; 
				} 
			} else { 
				tryThis(); 
			} 
		} 
		
		if ( r - n > 45) { 
			while (doMagic()) { 
			findRabbits(); 
			} 
		}
		
		try { 
			doSomethingDangerous(); 
		} catch (Exception ex) { 
			makeAmends(); 
		} finally { 
			dontDoItAgain(); 
		}
	} 
	 
	

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/codesize.html#NPathComplexity)
