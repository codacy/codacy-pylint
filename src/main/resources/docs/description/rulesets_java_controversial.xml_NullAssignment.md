Assigning a “null” to a variable (outside of its declaration) is usually bad form.
Sometimes, this type of assignment is an indication that the programmer doesn’t completely understand what is going on in the code.
There are some myths that were true back in the early days of java, that this sort of assignment may used in some cases to dereference objects and encourage garbage collection.
Nowadays the compiler and JVM have been improved, and there s no reason (if ever there was one) to explicitly put a reference equal to null after it's usage.

Ex:

    public void bar() {
      Object x = null; // this is OK
      x = new Object();
         // big, complex piece of code here
      x = null; // this is not required
         // big, complex piece of code here
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/controversial.html#NullAssignment)
