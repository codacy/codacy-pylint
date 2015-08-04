//#Patterns: rulesets_java_optimizations.xml_RedundantFieldInitializer

public class Foo {
    
    //#Warn: rulesets_java_optimizations.xml_RedundantFieldInitializer
    boolean a = false; 
    boolean b;
    
    //#Warn: rulesets_java_optimizations.xml_RedundantFieldInitializer
    byte c = 0;
    byte d;

    //#Warn: rulesets_java_optimizations.xml_RedundantFieldInitializer
    short e = 0;
    short f;

    //#Warn: rulesets_java_optimizations.xml_RedundantFieldInitializer
    char g = 0;
    char h;

    //#Warn: rulesets_java_optimizations.xml_RedundantFieldInitializer
    int i = 0;
    int j;

    //#Warn: rulesets_java_optimizations.xml_RedundantFieldInitializer
    long k = 0;
    long m;

    //#Warn: rulesets_java_optimizations.xml_RedundantFieldInitializer
    float n = .0f;    
    float o;

    //#Warn: rulesets_java_optimizations.xml_RedundantFieldInitializer
    double p = 0d;   
    double q;
    
    //#Warn: rulesets_java_optimizations.xml_RedundantFieldInitializer
    Object r = null;
    Object s;

    //#Warn: rulesets_java_optimizations.xml_RedundantFieldInitializer
    MyClass mca[] = null;
    MyClass mcb[];
}
