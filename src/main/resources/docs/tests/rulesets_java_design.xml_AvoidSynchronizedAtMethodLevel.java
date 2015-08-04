//#Patterns: rulesets_java_design.xml_AvoidSynchronizedAtMethodLevel


public class Foo {
  // Try to avoid this:
	//#Err: rulesets_java_design.xml_AvoidSynchronizedAtMethodLevel
  synchronized void foo() {
  }
  // Prefer this:
  void bar() {
    synchronized(this) {
    }
  }

  // Try to avoid this for static methods:
  //#Err: rulesets_java_design.xml_AvoidSynchronizedAtMethodLevel
  static synchronized void fooStatic() {
  }
  
  // Prefer this:
  static void barStatic() {
    synchronized(Foo.class) {
    }
  }
}

