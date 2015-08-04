//#Patterns: rulesets_java_controversial.xml_AvoidAccessibilityAlteration

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.security.PrivilegedAction;

public class Violation {
    public void invalidCallsInMethod() throws SecurityException, NoSuchMethodException {

            // Possible call to forbidden getDeclaredConstructors
            Class[] arrayOfClass = new Class[1];
            //#Err: rulesets_java_controversial.xml_AvoidAccessibilityAlteration
            this.getClass().getDeclaredConstructors();
            //#Err: rulesets_java_controversial.xml_AvoidAccessibilityAlteration
            this.getClass().getDeclaredConstructor(arrayOfClass);
            Class clazz = this.getClass();
            //#Err: rulesets_java_controversial.xml_AvoidAccessibilityAlteration
            clazz.getDeclaredConstructor(arrayOfClass);
            //#Err: rulesets_java_controversial.xml_AvoidAccessibilityAlteration
            clazz.getDeclaredConstructors();

            //#Err: rulesets_java_controversial.xml_AvoidAccessibilityAlteration
            clazz.getMethod("", arrayOfClass).setAccessible(false);
            //#Err: rulesets_java_controversial.xml_AvoidAccessibilityAlteration
            AccessibleObject.setAccessible(null, false);
            Method.setAccessible(null, false);
            Method[] methodsArray = clazz.getMethods();
            int nbMethod;

            for ( nbMethod = 0; nbMethod < methodsArray.length; nbMethod++  ) {
                //#Err: rulesets_java_controversial.xml_AvoidAccessibilityAlteration
                methodsArray[nbMethod].setAccessible(false);
            }
            
            // Possible call to forbidden PrivilegedAction
            PrivilegedAction priv = (PrivilegedAction) new Object(); priv.run();
    }
}
