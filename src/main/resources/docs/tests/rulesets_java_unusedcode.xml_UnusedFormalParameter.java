//#Patterns: rulesets_java_unusedcode.xml_UnusedFormalParameter
public class Foo {

    //#Warn: rulesets_java_unusedcode.xml_UnusedFormalParameter
    private void bar(String howdy) {
        // howdy is not used
    }

    private void bar(String howdy) {
        System.out.println(howdy);
    }
}