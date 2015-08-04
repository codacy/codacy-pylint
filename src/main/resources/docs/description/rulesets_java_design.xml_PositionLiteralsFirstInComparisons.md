Position literals first in comparisons, if the second argument is null then NullPointerExceptions can be avoided, they will just return false.

Ex:

    class Foo {
      boolean bar(String x) { //it will throw a nullPointer if x is null
        return x.equals("2");
      }

      boolean bar2(String x) { //this way it will return false even if x is null
        return "2".equals(x);
      }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#PositionLiteralsFirstInComparisons)
