//#Patterns: rulesets_java_basic.xml_ForLoopShouldBeWhileLoop
public class Foo {
    void bar() {
        //#Err: rulesets_java_basic.xml_ForLoopShouldBeWhileLoop
        for (;true;) true; // No Init or Update part, may as well be: while (true)
    }
}