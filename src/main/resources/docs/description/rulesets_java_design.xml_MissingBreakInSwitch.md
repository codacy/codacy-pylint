Switch statements without break or return statements for each case option may indicate problematic behaviour.
A case without a break will continue to run to the next case.
Empty cases are ignored as these indicate an intentional fall-through.

Ex:

    public void bar(int status) {
         switch(status) {
           case CANCELLED:
             doCancelled();
             // break; hm, should this be commented out?
           case NEW:
             doNew();
             // is this really a fall-through?
           case REMOVED:
             doRemoved();
             // what happens if you add another case after this one?
           case OTHER: // empty case - this is interpreted as an intentional fall-through
           case ERROR:
             doErrorHandling();
             break;
         }
     }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#MissingBreakInSwitch)
