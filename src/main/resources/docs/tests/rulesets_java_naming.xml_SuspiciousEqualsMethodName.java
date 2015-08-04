//#Patterns: rulesets_java_naming.xml_SuspiciousEqualsMethodName

public class Foo {
	//#Err: rulesets_java_naming.xml_SuspiciousEqualsMethodName
   public int equals(Object o) {
     // oops, this probably was supposed to be boolean equals
   }
   //#Err: rulesets_java_naming.xml_SuspiciousEqualsMethodName
   public boolean equals(String s) {
     // oops, this probably was supposed to be equals(Object)
   }
   //#Err: rulesets_java_naming.xml_SuspiciousEqualsMethodName
   public boolean equals(Object o1, Object o2) {
     // oops, this probably was supposed to be equals(Object)
   }

   public boolean equals(Object o) {
   	
   }
}