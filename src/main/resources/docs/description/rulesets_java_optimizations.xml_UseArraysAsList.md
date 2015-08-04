The java.util.Arrays class has a “asList” method that should be used when you want to create a new List from an array of objects. It is faster than executing a loop to copy all the elements of the array one by one.

Ex:


  	public class Test { 
  		public void foo(Integer[] ints) { 
  			// could just use Arrays.asList(ints) 
  			List l= new ArrayList(10); 
  			
  			for (int i=0; i< 100; i++) { 
  				l.add(ints[i]); 
  			} 
  			
  			for (int i=0; i< 100; i++) { 
  				l.add(a[i].toString()); // won't trigger the rule 
  			} 
  		} 
  	}


[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/optimizations.html#UseArraysAsList)
