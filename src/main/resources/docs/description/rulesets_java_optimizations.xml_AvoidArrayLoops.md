Instead of manually copying data between two arrays, use the efficient System.arraycopy method instead.
To use this method, you must use an already initialized array. You have to specify where to start and the number of elements to copy.
If the source and destination arrays differ in size put the minimum length between them.
The code will be more readable and more efficient.

Ex:

     public class Test {
       public void bar() {
         int[] a = new int[10];
         int[] b = new int[10];
         for (int i=0;i<10;i++) {
           b[i]=a[i];
         }
       }
     }

      // this will trigger the rule
      for (int i=0;i<10;i++) {
        b[i]=a[c[i]];
      }

      //correct usage
      public void foo(int[] source, int[] destination, int numberOfElementsToCopy) {
        System.arraycoppy(source, 0, destination, 0, numberOfElementsToCopy)
      }

      //another correct usage
      public int[] myArrayCopy(int[] source) {

        int[] newArray = int[source.lenght];

        int minimumLength = min(source.length, destination.length) //they are the same in this example, but bear with us

        System.arraycoppy(source, 0, newArray, 0, minimumLength)

        return newArray;
      }

      private int min(int x, int y) {
        return x < y ? x : y;
      }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/optimizations.html#AvoidArrayLoops)
