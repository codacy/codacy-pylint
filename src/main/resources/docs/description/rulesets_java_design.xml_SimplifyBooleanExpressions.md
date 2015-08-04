Avoid unnecessary comparisons in boolean expressions, they serve no purpose and impacts readability.

Ex:

    public class Bar {
      // can be simplified to
      // bar = isFoo();
      private boolean bar = (isFoo() == true);

      public isFoo() { return false;}
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#SimplifyBooleanExpressions)
