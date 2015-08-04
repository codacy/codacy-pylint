//#Patterns: rulesets_java_controversial.xml_CallSuperInConstructor

public class Foo extends Bar {
    
    //#Err: rulesets_java_controversial.xml_CallSuperInConstructor
    public Foo() {  

    }
}

public class Foo1 extends Bar {
    public Foo() {
        super();
    }
}
