Calls to a collection’s toArray() method should specify target arrays sized to match the size of the collection. Initial arrays that are too small are discarded in favour of new ones that have to be created that are the proper size.

Ex:

    List foos = getFoos();

        // inefficient, the array will be discarded
    Foo[] fooArray = foos.toArray(new Foo[0]);

        // much better; this one sizes the destination array,
        // avoiding of a new one via reflection
    Foo[] fooArray = foos.toArray(new Foo[foos.size()]);
	  
	     

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#OptimizableToArrayCall)
