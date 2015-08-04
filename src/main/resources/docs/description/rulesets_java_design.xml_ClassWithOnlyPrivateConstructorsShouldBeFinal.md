A class with only private constructors should be final, unless the private constructor is invoked by a inner class.
A class with a private constructor cannot be extended, so it should be marked as final.

Adding final to all things that shouldn't change is a good practice, doing this explicity shows the intention of the code.

Ex:

    public class Foo {  //Should be final
        private Foo() { }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#ClassWithOnlyPrivateConstructorsShouldBeFinal)
