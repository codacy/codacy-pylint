//#Patterns: rulesets_java_unnecessary.xml_UnusedNullCheckInEquals

public class Foo {
    public String bar() {
        return "ok";
    }

    public String method1() {
        return null;
    }

    public void method2(String a) {
        
        //#Err: rulesets_java_unnecessary.xml_UnusedNullCheckInEquals
        if (a != null && bar().equals(a)) {  }

        if (bar().equals(a) && a != null) {  }
    }
}
