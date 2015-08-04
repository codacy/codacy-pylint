Sometimes expressions are wrapped in unnecessary parentheses, making the code look fuzzy and sometimes it can even be confused with function calls.

Ex:
  		
  	public class Foo { 
  		boolean bar() { 
  			return (true); //unnecessary use of parenthesis
  			
  			return true; //better way
  		} 
  	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/controversial.html#UnnecessaryParentheses)
