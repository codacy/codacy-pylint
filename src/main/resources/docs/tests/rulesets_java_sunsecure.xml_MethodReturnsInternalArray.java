//#Patterns: rulesets_java_sunsecure.xml_MethodReturnsInternalArray

    public class SecureSystem {
        UserData [] ud;
        public UserData [] getUserData() {
            //#Err: rulesets_java_sunsecure.xml_MethodReturnsInternalArray
            return ud;
        }

        public UserData [] getUserData2() {

            return Arrays.copyOf(ud, ud.length);;
        }
    }
