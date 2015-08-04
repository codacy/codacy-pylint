The use of the ‘+=’ operator for appending strings causes the JVM to create and use an internal StringBuffer. If a non-trivial number of these concatenations are being used then the explicit use of a StringBuilder or threadsafe StringBuffer is recommended to avoid this.

Ex:

		public class Foo { 
			void bar() { 
				String a; 
				a = "foo"; 
				a += " bar"; // better would be: 
				
				// StringBuilder 
				a = new StringBuilder("foo"); 
				a.append(" bar); 
			} 
		}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/optimizations.html#UseStringBufferForStringAppends)
