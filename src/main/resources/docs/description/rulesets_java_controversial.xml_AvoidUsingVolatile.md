The use of the keyword ‘volatile’ is generally used to fine tune a Java application, and therefore, requires a good expertise of the Java Memory Model. Moreover, its range of action is somewhat misknown. 

Therefore, the volatile keyword should not be used for maintenance purpose and portability.

Ex:

  	public class ThrDeux { 
  		private volatile String var1; // not suggested 
  		private String var2; // preferred 
  	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/controversial.html#AvoidUsingVolatile)
