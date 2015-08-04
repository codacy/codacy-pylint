After checking an object reference for null, you should invoke equals() on that object rather than passing it to another object’s equals() method. If you invoke *equals()* on a null reference you will get a **NullPointerException**.

##Example

  	public class Test { 
  		public String method1() { 
  			return "ok";
  		} 
  		
  		public String method2() { 
  			return null;
  		} 
  		
  		public void method(String a) { 
  			// I don't know it method1() can be "null" 
  			// but I know \"a\" is not null.. 
  			// I'd better write a.equals(method1()) 
  			
  			if (a!=null && method1().equals(a)) { // will trigger the rule 
  				//whatever 
  			} 
  			
  			if (method1().equals(a) && a != null) { 
  				// won't trigger the rule //whatever 
  			} 
  			
  			if (a!=null && "SOME_LITERAL".equals(a)) { 
  				// won't trigger the rule //whatever 
  			} 
  		} 
  	} 

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/unnecessary.html#UnusedNullCheckInEquals)
