The method name and parameter number are suspiciously close to equals(Object), which can denote an intention to override the equals(Object) method.
This simple misspell can induce huge bugs (The object.equals will be used instead).
If the method is called this way intentionally, you should consider changing the name of the method.

Ex:

    public class Foo {
       public int equals(Object o) {
         // oops, this probably was supposed to be boolean equals
       }
       public boolean equals(String s) {
         // oops, this probably was supposed to be equals(Object)
       }
       public boolean equals(Object o1, Object o2) {
         // oops, this probably was supposed to be equals(Object)
       }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/naming.html#SuspiciousEqualsMethodName)
