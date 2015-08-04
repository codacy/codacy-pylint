//#Patterns: rulesets_java_empty.xml_EmptyStatementNotInLoop

public class Foo {
    public void bar() {
        //#Err: rulesets_java_empty.xml_EmptyStatementNotInLoop
        ;

        //#Err: rulesets_java_empty.xml_EmptyStatementNotInLoop
        System.out.println("Lorem Ipsum");;
        
        System.out.println("Lorem Ipsum");
    }
}
