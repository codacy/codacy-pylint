//#Patterns: rulesets_java_controversial.xml_AvoidUsingNativeCode

public class SomeJNIClass {

    public SomeJNIClass() {
        //#Warn: rulesets_java_controversial.xml_AvoidUsingNativeCode
        System.loadLibrary("nativelib");
    }

    static {
        //#Warn: rulesets_java_controversial.xml_AvoidUsingNativeCode
        System.loadLibrary("nativelib");
    }

    public void invalidCallsInMethod() throws SecurityException, NoSuchMethodException {
        //#Warn: rulesets_java_controversial.xml_AvoidUsingNativeCode
        System.loadLibrary("nativelib");
    }

}
