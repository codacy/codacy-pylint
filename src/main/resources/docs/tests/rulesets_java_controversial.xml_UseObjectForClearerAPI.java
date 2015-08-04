//#Patterns: rulesets_java_controversial.xml_UseObjectForClearerAPI

public class MyClass {
    //#Info: rulesets_java_controversial.xml_UseObjectForClearerAPI
    public void connect(String username,
        String pssd,
        String databaseName,
        String databaseAdress)
        // Instead of those parameters object
        // would ensure a cleaner API and permit
        // to add extra data transparently (no code change):
        // void connect(UserData data);
         {
        
         }
}
