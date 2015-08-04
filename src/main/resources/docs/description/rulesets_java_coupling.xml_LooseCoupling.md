"Program to an interface rather than to an implementation"

The use of implementation types as object references limits your ability to use alternate implementations in the future as requirements change.
Whenever available, referencing objects by their interface types provides much more flexibility.

Ex:

    // sub-optimal approach
    private ArrayList list = new ArrayList();

    public HashSet getFoo() {
        return new HashSet();
    }

    // preferred approach
    private List list = new ArrayList();

    public Set getFoo() {
        return new HashSet();
    }


[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/coupling.html#LooseCoupling)
