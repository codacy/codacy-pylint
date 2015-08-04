//#Patterns: rulesets_java_empty.xml_EmptyCatchBlock

public class Foo {
    public void bar() {
        try {
            FileInputStream fis = new FileInputStream("/some/directory");
        } 
        //#Warn: rulesets_java_empty.xml_EmptyCatchBlock
        catch (IOException ioe) {  }
    }
}
