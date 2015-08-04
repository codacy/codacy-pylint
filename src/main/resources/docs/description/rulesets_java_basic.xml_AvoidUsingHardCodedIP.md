Application with hard-coded IP addresses can become impossible to deploy in some cases.
A recompile is required if the address changes.
It forces the same address to be used in every environment (dev, sys, qa, prod).
It places the responsibility of setting the value to use in production on the shoulders of the developer.
Externalizing IP addresses is preferable.

Ex:

    public class Foo {
        private String ip = "127.0.0.1"; // not recommended
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/basic.html#AvoidUsingHardCodedIP)
