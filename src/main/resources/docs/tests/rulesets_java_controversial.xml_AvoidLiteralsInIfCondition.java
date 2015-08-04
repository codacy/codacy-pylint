//#Patterns: rulesets_java_controversial.xml_AvoidLiteralsInIfCondition

public class Foo {
    private static final int MAX_NUMBER = 10;

    public void bar() {
        int x = 0;

        //#Info: rulesets_java_controversial.xml_AvoidLiteralsInIfCondition
        if (x > 10) {  }

        if (x > MAX_NUMBER) {  } //preferred approach
    }
}
