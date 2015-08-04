Be sure to specify a Locale when creating SimpleDateFormat instances to ensure that locale-appropriate formatting is used.

Ex:

    public class Foo {
      private SimpleDateFormat sdf = new SimpleDateFormat("pattern"); //bad usage

      private SimpleDateFormat sdf = new SimpleDateFormat("pattern", Locale.FRANCE); //correct usage
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#SimpleDateFormatNeedsLocale)
