Don’t use floating point for loop indices.
If you must use floating point, use double unless you’re certain that float provides enough precision and you have a compelling performance need (space or time).

Ex:


    public class Count {
      public static void main(String[] args) {
        final int START = 2000000000;
        int count = 0;
        for (float f = START; f < START + 50; f++)
          count++;
          //Prints 0 because (float) START == (float) (START + 50).
          System.out.println(count);
          //The termination test misbehaves due to floating point granularity.
        }
    }

[Source](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/basic.html#DontUseFloatTypeForLoopIndices)
