//#Patterns: rulesets_java_unnecessary.xml_UselessOperationOnImmutable

import java.math.*;

public class Foo {
    public void bar() {
        BigDecimal bd = new BigDecimal(10);
        //#Err: rulesets_java_unnecessary.xml_UselessOperationOnImmutable
        bd.add(new BigDecimal(5));

        bd = bd.add(new BigDecimal(5));
    }
}
