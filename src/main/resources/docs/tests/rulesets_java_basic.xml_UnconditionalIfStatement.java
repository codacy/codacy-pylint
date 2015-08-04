//#Patterns: rulesets_java_basic.xml_UnconditionalIfStatement


  
public class Foo {
	public void close() {
		//#Warn: rulesets_java_basic.xml_UnconditionalIfStatement
		if (true) {		// fixed conditional, not recommended
			// ...
		}
	}
}