Vector is analog to the ArrayList hence they are very similar.

 ArrayLists bring to the table a much better performance than Vectors when no threads are involved.

Ex:


  	public class SimpleTest extends TestCase { 
  		public void testX() { 
  			Collection c1 = new Vector(); 
  			Collection c2 = new ArrayList(); // achieves the same with much better performance 
  		} 
  	}


[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/optimizations.html#UseArrayListInsteadOfVector)
