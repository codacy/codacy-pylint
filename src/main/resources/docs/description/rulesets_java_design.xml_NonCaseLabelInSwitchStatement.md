A non-case label (e.g. a named break/continue label) was present in a switch statement. This legal, but confusing. It is easy to mix up the case labels and the non-case labels.

Ex:

    public class Foo {
      void bar(int a) {
       switch (a) {
         case 1:
           // do something
           break;
         mylabel: // this is legal, but confusing!
           break;
         default:
           break;
        }
      }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#NonCaseLabelInSwitchStatement)