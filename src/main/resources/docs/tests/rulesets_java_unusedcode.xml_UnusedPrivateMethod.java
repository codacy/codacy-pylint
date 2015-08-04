//#Patterns: rulesets_java_unusedcode.xml_UnusedPrivateMethod

public class Something {
    //#Warn: rulesets_java_unusedcode.xml_UnusedPrivateMethod
    private void foo() {
        System.out.print("This private method is NOT called!");
    } // unused

    private void bar() {
        System.out.print("This private method is called!");
    }

    public void doSomething() {
        bar();
    }
}