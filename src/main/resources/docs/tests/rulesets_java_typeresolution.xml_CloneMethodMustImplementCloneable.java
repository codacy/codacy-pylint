//#Patterns: rulesets_java_typeresolution.xml_CloneMethodMustImplementCloneable

public class Foo {

    //#Err: rulesets_java_typeresolution.xml_CloneMethodMustImplementCloneable
    public Object clone() {
        return foo;
    }

    //#Err: rulesets_java_typeresolution.xml_CloneMethodMustImplementCloneable
    public Object clone() throws CloneNotSupportedException {
        return bar;
    }

}
