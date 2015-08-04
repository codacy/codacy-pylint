(string).startsWith although it adds some sintactic sugar to our code and readability because of it passing in a literal of length 1, it loses performance wise to (string).charAt(0).

Ex:

  		public class Foo { 
  			boolean checkIt(String x) { 
  				return x.startsWith(\"a\"); // suboptimal 
  			} 
  			
  			boolean fasterCheckIt(String x) { 
  				return x.charAt(0) == 'a'; // faster approach 
  			}
  		}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/optimizations.html#SimplifyStartsWith)
