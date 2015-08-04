Unnecessary reliance on Java Native Interface (JNI) calls directly reduces application portability and increases the maintenance burden.

##Example

  	public class SomeJNIClass { 
  		public SomeJNIClass() { 
  			System.loadLibrary("nativelib"); 
  		} 
  		
  		static { 
  			System.loadLibrary("nativelib"); 
  		} 
  		
  		public void invalidCallsInMethod() throws SecurityException, NoSuchMethodException { 
  			System.loadLibrary("nativelib"); 
  		} 
  	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/controversial.html#AvoidUsingNativeCode)
