//#Patterns: rulesets_java_controversial.xml_SuspiciousOctalEscape

public class Foo {

    public void bar() {
        //#Err: rulesets_java_controversial.xml_SuspiciousOctalEscape
        System.out.println("suspicious: \128");
    }

}
