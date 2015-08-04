//#Patterns: rulesets_java_design.xml_EmptyMethodInAbstractClassShouldBeAbstract

public abstract class ShouldBeAbstract {
    
    public Object couldBeAbstract() {
        // Should be abstract method ?
        int i = 0;
        i++;
        return null;
    }

//#Info: rulesets_java_design.xml_EmptyMethodInAbstractClassShouldBeAbstract
    public void couldBeAbstract() {
    }
}
	

