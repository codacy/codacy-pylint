//#Patterns: rulesets_java_coupling.xml_LooseCoupling
public class Foo {


	//#Warn: rulesets_java_coupling.xml_LooseCoupling
	private ArrayList list = new ArrayList();

    //#Warn: rulesets_java_coupling.xml_LooseCoupling
	public HashSet getFoo() {

        return new HashSet();
	}

	// preferred approach
	private List list2 = new ArrayList();

	public Set getFoo2() {

        return new HashSet();
	}
}

