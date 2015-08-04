Avoid importing anything from the ‘sun.*’ packages. These packages are not portable and are likely to change.

Ex:

	import sun.misc.foo; 
	public class Foo { }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/controversial.html#DontImportSun)
