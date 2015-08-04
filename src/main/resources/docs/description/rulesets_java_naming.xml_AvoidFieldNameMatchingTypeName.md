It is somewhat confusing to have a field name matching the declaring class name.
This probably means that type and/or field names should be chosen more carefully.

Ex:

    public class Foo extddends Bar {
        int foo;    // There is probably a better name that can be used
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/naming.html#AvoidFieldNameMatchingTypeName)
