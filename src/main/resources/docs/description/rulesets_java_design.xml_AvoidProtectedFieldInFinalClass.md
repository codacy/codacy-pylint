Do not use protected fields in final classes since they cannot be subclassed. Clarify your intent by using private or package access modifiers instead.
In this case, the protected instead of private will only expose it to the package level.
At this point, (final class), you should choose between public, private, or no modifier (package access).
If the intention is it to have package access level, then you should put no modifier keyword, this way you will get package access.
Doing this you clearly state to other readers what is the purpose and accessibility of the field.

Ex:

    public final class Bar {
      private int x;
      protected int y;  // bar cannot be subclassed, so is y really private or package visible?
      Bar() {}
    }


[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#AvoidProtectedFieldInFinalClass)
