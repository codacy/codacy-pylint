//#Patterns: rulesets_java_basic.xml_MisplacedNullCheck
   
public class Foo {
	void bar() {
//#Err: rulesets_java_basic.xml_MisplacedNullCheck
		if (a.equals(baz) && a != null) {}
		}
}


