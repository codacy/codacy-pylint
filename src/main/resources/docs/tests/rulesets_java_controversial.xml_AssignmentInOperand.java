//#Patterns: rulesets_java_controversial.xml_AssignmentInOperand

public class Foo {

    public void bar() {
        int x = 2;

        //#Info: rulesets_java_controversial.xml_AssignmentInOperand
        if ((x = getX()) == 3) {
            //insert code
        }

        x = getX();
        if(x == 3) {
            //insert code
        }
    }

}
