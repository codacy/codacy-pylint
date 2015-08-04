The method clone() should throw a CloneNotSupportedException since it is possible that the this error comes up when implementing the method.

##Example

  	public class MyClass implements Cloneable { 
  		public Object clone() { 
  			// will cause an error 
  			MyClass clone = (MyClass)super.clone(); 
  			return clone; 
  		} 
  		
  		
  		public Object clone() throws CloneNotSupportedException {
  			//this will not cause an error
  			MyClass clone = (MyClass)super.clone();
  			return clone;
  		}
  	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/clone.html#CloneThrowsCloneNotSupportedException)
