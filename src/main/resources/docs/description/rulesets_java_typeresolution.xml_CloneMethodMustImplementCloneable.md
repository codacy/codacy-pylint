The method clone() should only be implemented if the class implements the **Cloneable interface** with the exception of a final method that only throws CloneNotSupportedException. 

This version uses PMD’s type resolution facilities, and can detect if the class implements or extends a Cloneable class.

##Example

	public class MyClass { 
		public Object clone() throws CloneNotSupportedException { 
			return foo; 
		} 
	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/typeresolution.html#CloneMethodMustImplementCloneable)
