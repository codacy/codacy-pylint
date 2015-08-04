A non-static initializer block will be called any time a constructor is invoked (just prior to invoking the constructor).
While this is a valid language construct, it is rarely used and is confusing to the reader.

Ex:

    public class MyClass {
     // this block gets run before any call to a constructor
      {
       System.out.println("I am about to construct myself");
      }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#NonStaticInitializer)
