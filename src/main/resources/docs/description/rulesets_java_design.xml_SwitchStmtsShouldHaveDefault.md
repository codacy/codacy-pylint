The default rule should have a good implementation for the values not matched during the cases.
If the desired behaviour is to do nothing in the default case, it is better to add an empty default statement.
Preferably with a comment to make it clear to the reader that it's intentional.

Ex:

    public void bar() {
        int x = 2;
        switch (x) {
          case 1: int j = 6;
                  break;
          case 2: int j = 8;
                  breaK;
                        // missing default: here
        }

        //better usage
        switch (x) {
          case 1: int j = 6;
                  break;
          case 2: int j = 8;
                  break;
          default: //do nothing
                  break;
        }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#SwitchStmtsShouldHaveDefault)
