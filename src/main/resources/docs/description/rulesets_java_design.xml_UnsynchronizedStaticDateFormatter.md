SimpleDateFormat instances are not synchronized. Sun recommends using separate format instances for each thread.
If multiple threads must access a static formatter, the formatter must be synchronized either on method or block level.

Ex:

    public class Foo {
        private static final SimpleDateFormat sdf = new SimpleDateFormat();
        void bar() {
            sdf.format(); // poor, no thread-safety
        }
        synchronized void foo() {
            sdf.format(); // preferred
        }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#UnsynchronizedStaticDateFormatter)
