It can be confusing to have a field name with the same name as a method.
While this is permitted, having information (field) and actions (method) is not clear naming.

Ex:

    public class Foo {
        // bar is data or an action or both?
        Object bar;
        void bar() {
        }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/naming.html#AvoidFieldNameMatchingMethodName)
