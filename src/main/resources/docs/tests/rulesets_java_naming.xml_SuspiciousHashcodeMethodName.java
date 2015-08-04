//#Patterns: rulesets_java_naming.xml_SuspiciousHashcodeMethodName
public class Foo {
	//#Err: rulesets_java_naming.xml_SuspiciousHashcodeMethodName
    public int hashcode() {	// oops, this probably was supposed to be 'hashCode'
	
	}

	public int hashCode() {
	
	}
}