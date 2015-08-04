Use explicit scoping instead of the default package private level. What this means it that you should do is make all the fields within your classes private to enforce better encapsulation.

##Example
		public class Foo {
			/* private missing */ Object bar;
		}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/controversial.html#DefaultPackage)
