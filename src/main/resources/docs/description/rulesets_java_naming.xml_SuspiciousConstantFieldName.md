Field names using all uppercase characters - Sun's Java naming conventions indicating constants - should be declared as final.
Names using only uppercase are considered constants for most programmers, using them as variables can induce errors.

Ex:

    public class Foo {
     // this is bad, since someone could accidentally change the value of PI
	double PI = 3.16;
     
    final double PI = 3.16; //this is ok
      
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/naming.html#SuspiciousConstantFieldName)
