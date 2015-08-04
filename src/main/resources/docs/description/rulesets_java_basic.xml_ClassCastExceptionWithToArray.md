When deriving an array of a specific class from your Collection, one should provide an array of the same class as the parameter of the toArray() method.
Doing otherwise you will will result in a ClassCastException.

Ex:

    Collection c = new ArrayList();
    Integer obj = new Integer(1);
    c.add(obj);

    // this would trigger the rule (and throw a ClassCastException if executed)
    Integer[] a = (Integer [])c.toArray();

    // this is fine and will not trigger the rule
    Integer[] b = (Integer [])c.toArray(new Integer[c.size()]);


[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/basic.html#ClassCastExceptionWithToArray)
