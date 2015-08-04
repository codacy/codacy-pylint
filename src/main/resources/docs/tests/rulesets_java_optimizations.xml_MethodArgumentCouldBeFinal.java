//#Patterns:  rulesets_java_optimizations.xml_MethodArgumentCouldBeFinal
public class Foo {

    //#Err: rulesets_java_optimizations.xml_MethodArgumentCouldBeFinal
    public void bar(int i) {
        //do something here
    }

    public void correct(final int i) {
        //the correct way
    }

}
