//#Patterns: rulesets_java_design.xml_UseCollectionIsEmpty

public class Foo {
	void good() {
		List foo = getList();
		if (foo.isEmpty()) {
			// blah
		}
	}

	void bad() {
		List foo = getList();
//#Info: rulesets_java_design.xml_UseCollectionIsEmpty
		if (foo.size() == 0) {
				// blah
		}
	}
}