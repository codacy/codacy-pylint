Identifies a possible unsafe usage of a static field.
A static field can be accessed using the class instead of an instance of it (this is actually the correct usage of static fields).
This means a static field can be accessed before there is an instantiation of the class.
And as you can see in the example, the static variable is only initialized in the constructor.
So it is very possible that you access the variable before it is initialized.

Ex:

    public class StaticField {
       static int x;
       public FinalFields(int y) {
        x = y; // unsafe
       }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#AssignmentToNonFinalStatic)
