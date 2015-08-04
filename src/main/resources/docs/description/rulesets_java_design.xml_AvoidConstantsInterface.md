An interface should be used only to characterize the external behaviour of an implementing class.
Using an interface as a container of constants is a poor usage pattern and not recommended.
You should use a final class instead, and if you want to prohibit instantiation, define a private constructor on that class.

Ex:

    public interface ConstantsInterface {
       public static final int CONSTANT1=0;
       public static final String CONSTANT2="1";
    }

    public final class MyConstants {
       public static final int CONSTANT1=0;
       public static final String CONSTANT2="1";

       private MyConstatns() {
          //Private constructor prohibits instantiation
       }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#AvoidConstantsInterface)
