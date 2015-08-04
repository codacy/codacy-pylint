Avoid unused import statements. 

This rule will find unused on demand imports, i.e. import com.foo.*.

##Example

	import java.io.*; // not referenced or required 
	
	public class Foo {}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/typeresolution.html#UnusedImports)
