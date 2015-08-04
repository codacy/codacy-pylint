Calls to System.gc(), Runtime.getRuntime().gc(), and System.runFinalization() are **not advised**. 

Code should have the same behavior whether the garbage collection is disabled using the option '*-Xdisableexplicitgc*' or not. Moreover, “modern” jvms do a very good job handling garbage collections. If memory usage issues unrelated to memory leaks develop within an application, it should be dealt with JVM options rather than within the code itself.

Ex:

  	public class GCCall { 
  			public GCCall() { 
  				// Explicit gc call ! 
  				System.gc(); 
  			} 
  			
  			public void doSomething() { 
  				// Explicit gc call ! 
  				Runtime.getRuntime().gc(); 
  			} 
  			
  			public void doSomething() { 
  				// Explicit gc call ! 
  				Runtime.getRuntime().gc(); 
  			} 
  	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/controversial.html#DoNotCallGarbageCollectionExplicitly)
