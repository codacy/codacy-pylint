A method should have only one exit point, and that should be the last statement in the method.
This notion of "Single Entry, Single Exit" is a bit hold, and somehow deprecated, but it's usage still have some advantages.
If you want to add something before the function return (like a print or something), it's much easier if you have only one exit point.

Ex:

    public class OneReturnOnly1 {
      public void foo(int x) {
        if (x > 0) {
          return "hey";   // first exit
        }
        return "hi";	// second exit
      }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/controversial.html#OnlyOneReturn)
