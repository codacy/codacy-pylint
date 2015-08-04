//#Patterns: rulesets_java_empty.xml_EmptySynchronizedBlock

public class Foo {
    public void bar() {
        //#Warn: rulesets_java_empty.xml_EmptySynchronizedBlock
        synchronized(this) {
        }
    }

    public void helper(int value) {
        int acc = 0;
        synchronized(this) {
            acc += value;
        }
    }
}
