By convention, the default label should be the last label in a switch statement.
It is the most common usage of the switch statement, and it makes more sense as control flow.
If does not match any case, then the default case is selected.

Ex:

    public class Foo {
      void bar(int a) {
       switch (a) {
        case 1:  // do something
           break;
        default:  // the default case should be last, by convention
           break;
        case 2:
           break;
       }
      }
    }


[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#DefaultLabelNotLastInSwitchStmt)
