When comparing objects you must use the equals() method.
The == operator in Java checks for reference equality: it returns true if the pointers are the same.
It does not check for contents equality.

Ex:

    class Foo {
      boolean bar(String a, String b) { //wrong
        return a == b;
      }

      boolean bar(String a, String b) { //right
        return a.equals(b);
      }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#CompareObjectsWithEquals)
