//#Patterns: rulesets_java_basic.xml_AvoidMultipleUnaryOperators

public class Foo {

    public void bar()

    {
        // These are typo bugs, or at best needlessly complex and confusing:
        //#Err: rulesets_java_basic.xml_AvoidMultipleUnaryOperators
        int i = - -1;
        //#Err: rulesets_java_basic.xml_AvoidMultipleUnaryOperators
        int z = ~~2;
        //#Err: rulesets_java_basic.xml_AvoidMultipleUnaryOperators
        boolean b = !!true;

        // These are better:
        int ii = 1;
        int jj = -1;
        int zz = 2;
        boolean bb = true;
        boolean cc = false;


    }
}


