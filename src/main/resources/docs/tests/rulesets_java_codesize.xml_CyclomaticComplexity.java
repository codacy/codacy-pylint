//#Patterns: rulesets_java_codesize.xml_CyclomaticComplexity

//#Warn: rulesets_java_codesize.xml_CyclomaticComplexity
public class Foo {

    //#Warn: rulesets_java_codesize.xml_CyclomaticComplexity
    public void bar() {
        int i = 0;
        while (i < 1) {
            i++;
            //insert code
        }

        while (i < 2) {
            i++;
            //insert code
        }

        while (i < 3) {
            i++;
            //insert code
        }

        if (i == 3) {
            i++;
            //insert code
        }

        if (i == 4) {
            i++;
        }

        if (i < 100) {
            i++;
        }

        while (i < 10) {
            i++;
        }

        while (i < 11) {
            i++;
        }

        while (i < 12) {
            i++;
        }
    }
}
