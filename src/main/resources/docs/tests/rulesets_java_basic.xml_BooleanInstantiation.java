//#Patterns: rulesets_java_basic.xml_BooleanInstantiation
public class Foo {
    public void bar() {

//#Warn: rulesets_java_basic.xml_BooleanInstantiation
Boolean bar = new Boolean("true");		// unnecessary creation, just reference Boolean.TRUE;
//#Warn: rulesets_java_basic.xml_BooleanInstantiation
Boolean buz = Boolean.valueOf(false);	// ...., just reference Boolean.FALSE;

Boolean zbar = Boolean.TRUE;		// unnecessary creation, just reference Boolean.TRUE;
Boolean zbuz = Boolean.FALSE;
    }
}

