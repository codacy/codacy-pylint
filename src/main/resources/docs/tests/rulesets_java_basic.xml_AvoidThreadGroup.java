//#Patterns: rulesets_java_basic.xml_AvoidThreadGroup
public class Foo {
    public void bar() {

//#Err: rulesets_java_basic.xml_AvoidThreadGroup
		ThreadGroup tg = new ThreadGroup("My threadgroup") ;
//#Err: rulesets_java_basic.xml_AvoidThreadGroup
        tg = new ThreadGroup(tg, "my thread group");
//#Err: rulesets_java_basic.xml_AvoidThreadGroup
        tg = Thread.currentThread().getThreadGroup();
//#Err: rulesets_java_basic.xml_AvoidThreadGroup
        tg = System.getSecurityManager().getThreadGroup();

    }
}

