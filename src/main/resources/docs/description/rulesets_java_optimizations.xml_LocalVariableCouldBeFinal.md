If a local variable is assigned only once it can be declares as final.

Making a variable final specifies the *intent* of the programmer at the time of writing. This is very important as it might  give subsequent programmers who edit the code pause for thought before they start changing how that variable is used. 

Ex:

	public class Bar { 
			public void foo () { 
				String txtA = "a";  // if txtA will not be assigned again it is better to do this: 
				final String txtB = "b"; 
			} 
	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/optimizations.html#LocalVariableCouldBeFinal)
