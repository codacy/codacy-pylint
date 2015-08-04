//#Patterns: rulesets_java_basic.xml_ClassCastExceptionWithToArray
public class Foo {
    public void bar() {

		Collection c = new ArrayList();
		Integer obj = new Integer(1);
		c.add(obj);

		    
		//#Err: rulesets_java_basic.xml_ClassCastExceptionWithToArray
		Integer[] a = (Integer [])c.toArray();

		   // this is fine and will not trigger the rule
		Integer[] b = (Integer [])c.toArray(new Integer[c.size()]);


    }
}

