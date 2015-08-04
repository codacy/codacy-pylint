Java allows the use of several variables declaration of the same type on one line. However, it can lead to quite messy code and turning your code hard to read.

Ex:

	// separate declarations
  	String name; 
  	String lastname; 
  	
  	// combined declaration, a violation
  	String name, lastname;  
  	
  	// combined declaration on multiple lines, no violation.
  	String name, 
  			 lastname;

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/controversial.html#OneDeclarationPerLine)
