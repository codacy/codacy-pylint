//#Patterns: rulesets_java_controversial.xml_DoNotCallGarbageCollectionExplicitly

public class Foo {

    public CGCall() {
        //#Warn: rulesets_java_controversial.xml_DoNotCallGarbageCollectionExplicitly
        System.gc();
    }

    public void doSomething() {
        //#Warn: rulesets_java_controversial.xml_DoNotCallGarbageCollectionExplicitly
        Runtime.getRuntime().gc();
    }

    public void doSomething() {
        //#Warn: rulesets_java_controversial.xml_DoNotCallGarbageCollectionExplicitly
        Runtime.getRuntime().gc();
    }

}
