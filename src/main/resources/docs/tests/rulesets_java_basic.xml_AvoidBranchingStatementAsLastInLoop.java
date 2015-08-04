//#Patterns: rulesets_java_basic.xml_AvoidBranchingStatementAsLastInLoop
public class Foo {

    public void bar() {


        for (int i = 0; i < 10; i++) {
            if (i * i <= 25) {
                continue;
            }
            //#Err: rulesets_java_basic.xml_AvoidBranchingStatementAsLastInLoop
            break;
        }

        // this makes more sense...
        for (int i = 0; i < 10; i++) {
            if (i * i > 25) {
                break;
            }
        }
    }

}