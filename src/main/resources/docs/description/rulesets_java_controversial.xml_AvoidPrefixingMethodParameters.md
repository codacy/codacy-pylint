Prefixing parameters by ‘in’ or ‘out’ pollutes the name of the parameters and reduces code readability. To indicate whether or not a parameter will be modify in a method, its better to document method behavior with Javadoc.

Ex:

**Not So Clear Code:**

  	public class Foo { 
  		public void bar(int inLeftOperand, Result outRightOperand) { 
  			outRightOperand.setValue(inLeftOperand * outRightOperand.getValue()); 
  		} 
  	}
  	
 **Far more readable code:** 	
 
  	public class Foo { 
  		/** 
  		* 
  		* @param leftOperand, (purpose), not modified by method. 
  		* @param rightOperand (purpose), will be modified by the method: contains the result. 
  		*/ 
  		
  		public void bar( int leftOperand, Result rightOperand) { 
  			rightOperand.setValue(leftOperand * rightOperand.getValue()); 
  		} 
  	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/controversial.html#AvoidPrefixingMethodParameters)
