Constructors and methods receiving arrays should clone objects and store the copy. This prevents future changes from the user from affecting the original array.

Ex:

    public class Foo {
      private String [] x;
        public void foo (String [] param) { //wrong
          // Don't do this, make a copy of the array at least
          this.x = param;
        }

        public void foo (String [] param) { //right

          this.x = Arrays.copyOf(param, param.length);
        }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/sunsecure.html#ArrayIsStoredDirectly)
