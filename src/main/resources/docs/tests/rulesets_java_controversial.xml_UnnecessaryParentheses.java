//#Patterns: rulesets_java_controversial.xml_UnnecessaryParentheses

public class Foo {
    
    public boolean bar(int x) {

        if (x == 0) {
          //#Info: rulesets_java_controversial.xml_UnnecessaryParentheses
          return (true);
        }

        return false;
    }

}
