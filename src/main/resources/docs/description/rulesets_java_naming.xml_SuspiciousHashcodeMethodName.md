The method name and return type are suspiciously close to hashCode(), which may denote an intention to override the hashCode() method.
This simple misspell can induce huge bugs (The object.hashCode will be used instead).
If the method is called this way intentionally, you should consider changing the name of the method.


Ex:

    public class Foo {
    	public int hashcode() {	// oops, this probably was supposed to be 'hashCode'

    	}
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/naming.html#SuspiciousHashcodeMethodName)
