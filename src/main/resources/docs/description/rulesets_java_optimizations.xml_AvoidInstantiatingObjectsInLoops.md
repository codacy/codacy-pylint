Objects created within loops should be checked to see if they can be created outside and reused.
Memory wise it can be very expensive to do this, so it is considered a good practice to just reuse an already instantiated variable.

Ex:

		public class Something { 
			public static void main(String as[]) { 
				for (int i = 0; i < 10; i++) { 
					Foo f = new Foo(); // Avoid this whenever you can it's really expensive 				} 
			}
		}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/optimizations.html#AvoidInstantiatingObjectsInLoops)
