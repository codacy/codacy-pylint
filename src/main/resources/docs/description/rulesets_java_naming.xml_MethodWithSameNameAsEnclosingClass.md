Non-constructor methods should not have the same name as the enclosing class.
Constructors and methods with the same name are confusing, and should be avoided.

Ex:

    public class MyClass {

        public MyClass() {}			// this is OK because it is a constructor

        public void MyClass() {}	// this is bad because it is a method
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/naming.html#MethodWithSameNameAsEnclosingClass)
