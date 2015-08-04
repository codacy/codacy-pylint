//#Patterns: rulesets_java_design.xml_SwitchStmtsShouldHaveDefault

public class Foo {
    public void bar() {
        int x = 2;
        int j = 0;
        //#Err: rulesets_java_design.xml_SwitchStmtsShouldHaveDefault
        switch (x) {
            case 1:
                j = 6;
            case 2:
                j = 8;
                // missing default: here
        }
    }

}