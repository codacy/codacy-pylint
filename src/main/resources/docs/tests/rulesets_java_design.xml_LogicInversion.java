//#Patterns: rulesets_java_design.xml_LogicInversion
public class Foo {

public boolean bar(int a, int b) {

//#Err: rulesets_java_design.xml_LogicInversion
	if (!(a == b)) // use !=
         return false;

//#Err: rulesets_java_design.xml_LogicInversion
	if (!(a < b)) // use >=
         return false;

	if (a != b)
         return false;

	if (a >= b)
         return false;


	return true;

    }
}

