Useless parentheses should be removed since they serve no purpose to make your code better and more readable.

##Example

  	public class Foo { 
  		private int _bar1; 
  		
  		public void setBar(int n) { 
  			_bar1 = Integer.valueOf((n)); // here 
  			
  			_bar1 = Integer.valueOf(n); // the correct way 
  		} 
  	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/unnecessary.html#UselessParentheses)
