//#Patterns: rulesets_java_naming.xml_LongVariable

public class Something {
    int reallyLongIntName = -3;            // VIOLATION - Field

    public static void main(String argumentsList[]) { // VIOLATION - Formal
        //#Info: rulesets_java_naming.xml_LongVariable
        int otherReallyLongName = -5;        // VIOLATION - Local
        //#Info: rulesets_java_naming.xml_LongVariable
        for (int interestingIntIndex = 0;    // VIOLATION - For
             interestingIntIndex < 10;
             interestingIntIndex++) {
        }
    }
}