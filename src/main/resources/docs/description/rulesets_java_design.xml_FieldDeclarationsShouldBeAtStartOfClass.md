Fields should be declared at the top of the class, before any method declarations, constructors, initializers or inner classes.
It is a widely accepted good code style.

Ex:

    public class HelloWorldBean {

      // Field declared before methods / inner classes - OK
      private String _thing;

      public String getMessage() {
        return "Hello World!";
      }

      // Field declared after methods / inner classes - avoid this
      private String _fieldInWrongLocation;
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#FieldDeclarationsShouldBeAtStartOfClass)
