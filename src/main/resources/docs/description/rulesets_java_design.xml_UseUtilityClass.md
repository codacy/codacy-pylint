For classes that only have static methods, use them as utility classes.
Note that this doesn't apply to abstract classes, since their subclasses may well include non-static methods.
Also, if you want this class to be a utility class, remember to add a private constructor to prevent instantiation.

Ex:

    public class MaybeAUtility {
      public static void foo() {}
      public static void bar() {}

      //Add this line
      private MaybeAUtility() { }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#UseUtilityClass)
