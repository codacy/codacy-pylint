Avoid instantiating an object just to call getClass() on it; use the .class public member instead.
If you only need the Class, there is no point in creating a new object just to be discarded.

Ex:

      // replace this
    Class c = new String().getClass();

      // with this:
    Class c = String.class;

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#InstantiationToGetClass)
