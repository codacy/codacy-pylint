A non-field shouldn't has a name starting with m_.
This usually denotes a field and could be confusing.

Ex:

    public class Foo {
        private int m_foo; // OK
        public void bar(String m_baz) {  // Bad
          int m_boz = 42; // Bad
        }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/naming.html#MisleadingVariableName)
