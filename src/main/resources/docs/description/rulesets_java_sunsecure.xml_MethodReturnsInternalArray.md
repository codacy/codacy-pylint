Exposing internal arrays to the caller violates object encapsulation since elements can be removed or replaced outside of the object that owns it. It is safer to return a copy of the array.

Ex:

    public class SecureSystem {
      UserData [] ud;
      public UserData [] getUserData() { //wrong
          // Don't return directly the internal array, return a copy
          return ud;
      }

      public UserData [] getUserData() { //right

          return Arrays.copyOf(ud, ud.length);
      }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/sunsecure.html#MethodReturnsInternalArray)
