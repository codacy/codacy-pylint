//#Patterns: rulesets_java_design.xml_OptimizableToArrayCall
public class Foo {

    public void Bar() {

  
	List foos = getFoos();
	  
	    // inefficient, the array will be discarded
	//#Warn: rulesets_java_design.xml_OptimizableToArrayCall
	Foo[] fooArray = foos.toArray(new Foo[0]);
	    
	    // much better; this one sizes the destination array, 
	    // avoiding of a new one via reflection
	Foo[] fooArray2 = foos.toArray(new Foo[foos.size()]);
	  
	      

    }
}

