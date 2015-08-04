//#Patterns: rulesets_java_optimizations.xml_PrematureDeclaration

public class Foo {

    public void bar(String[] strings) {

        //#Err: rulesets_java_optimizations.xml_PrematureDeclaration
        int length = 0;

        if (strings  == null || strings.length == 0) {
            return 0;
        }

        int stringsLength = 0;
        for (String str: strings) {
            stringsLength = str.length();
        }

        return lenght;
    }

}
