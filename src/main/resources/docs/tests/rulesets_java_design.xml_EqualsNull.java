//#Patterns: rulesets_java_design.xml_EqualsNull
public class Foo {

    public void Bar() {

      
	String x = "foo";

	//#Err: rulesets_java_design.xml_EqualsNull
	if (x.equals(null)) { // bad form
	   	doSomething();
		}
		
	if (x == null) { 	// preferred
	   	doSomething();
		}
    
        
    }
}

