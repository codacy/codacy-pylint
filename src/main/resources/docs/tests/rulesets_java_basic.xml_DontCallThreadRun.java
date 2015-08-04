//#Patterns: rulesets_java_basic.xml_DontCallThreadRun
public class Foo {

    public void bar() {
        Thread t = new Thread();
        //#Warn: rulesets_java_basic.xml_DontCallThreadRun
        t.run();            // use t.start() instead
        //#Warn: rulesets_java_basic.xml_DontCallThreadRun
        new Thread().run(); // same violation
    }
}