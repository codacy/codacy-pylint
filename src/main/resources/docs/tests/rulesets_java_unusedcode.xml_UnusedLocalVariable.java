//#Patterns: rulesets_java_unusedcode.xml_UnusedLocalVariable

public class Foo {
    public void doSomething() {
        //#Warn: rulesets_java_unusedcode.xml_UnusedLocalVariable
        int i = 5; // Unused

        int foo = 5;

        System.out.println(foo);
    }
}

