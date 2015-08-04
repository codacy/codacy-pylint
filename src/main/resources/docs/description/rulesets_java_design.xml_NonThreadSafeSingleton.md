Non-thread safe singletons can result in bad state changes. Eliminate static singletons if possible by instantiating the object directly.
Static singletons are usually not needed as only a single instance exists anyway.
Possible fixes are to synchronize the entire method or to use an initialize-on-demand holder class (do not use the double-check idiom).

There are better approaches, you can use an enum or instantiate the object in the declaration.
The best approaches are shown in the examples.

Ex:

    //Not recommended
    public class Foo {

        private static Foo fooInstance = null;

        //multiple simultaneous callers may see partially initialized objects
        public static Foo getInstance() {
            if (fooInstance==null)
                fooInstance = new Foo();
            return fooInstance;
        }
    }

    //Preferred approach
    public enum Foo {
        INSTANCE;

        public void methodA() {
            int i = 0;
        }
    }
    //To call methodA() do Foo.INSTANCE.methodA();



    //Good approach
    public class Bar {

        private static Bar barInstance = new Bar();

        public static Bar getInstance() {
            return barInstance;
        }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#NonThreadSafeSingleton)
