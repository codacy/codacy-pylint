//#Patterns: rulesets_java_design.xml_ConfusingTernary
public class Foo {

	boolean bar(int x, int y) {
		//#Info: rulesets_java_design.xml_ConfusingTernary
		return (x != y) ? diff : same;
	}

	boolean bar2(int x, int y) {
		return (x == y) ? name : diff;
	}

}

