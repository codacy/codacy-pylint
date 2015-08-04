//#Patterns: rulesets_java_optimizations.xml_AvoidInstantiatingObjectsInLoops

public class Foo {
    
    public void foo() {
        for (int i = 0; i < 10; i++) {
            //#Warn: rulesets_java_optimizations.xml_AvoidInstantiatingObjectsInLoops
            String f = new String("ola");
        }

        String bar;
        for (int i = 0; i < 10; i++) {
            bar = "Ola";
        }
    }

}
