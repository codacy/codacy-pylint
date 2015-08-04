If an abstract class does not provides any methods, it may be acting as a simple data container that is not meant to be instantiated.
In this case, it is probably better to use a private or protected constructor in order to prevent instantiation than make the class misleadingly abstract.

Ex:

    //Is this a data container?
    public class abstract BadExample {
        String field;
        int otherField;
    }

    //This is a better approach
    public class  GoodExample {
        String field;
        int otherField;

        private GoodExample() {
            //With private constructor prohibits instantiation
        }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#AbstractClassWithoutAnyMethod)
