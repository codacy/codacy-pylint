//#Patterns: rulesets_java_basic.xml_CollapsibleIfStatements
public class Foo {

	void bar() {
		if (x) {			// original implementation
            //#Info: rulesets_java_basic.xml_CollapsibleIfStatements
			if (y) {
				// do stuff
			}
		}
	}

	void zbar() {
		if (x && y) {		// optimized implementation
			// do stuff
		}
	}

}