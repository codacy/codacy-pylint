Avoid using java.lang.ThreadGroup; although it is intended to be used in a threaded environment it contains methods that are not thread-safe.

Ex:

    public class Bar {
        void buz() {
            ThreadGroup tg = new ThreadGroup("My threadgroup") ;
            tg = new ThreadGroup(tg, "my thread group");
            tg = Thread.currentThread().getThreadGroup();
            tg = System.getSecurityManager().getThreadGroup();
        }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/basic.html#AvoidThreadGroup)
