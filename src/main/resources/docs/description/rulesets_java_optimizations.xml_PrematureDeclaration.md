Checks for variables that are defined before they might be used.
A reference is deemed to be premature if it is created right before a block of code that doesn’t use it that also has the ability to return or throw an exception.
In languages that support it, like Java, a good convention to follow is that variables should be declared and defined close to where they are first used.

Ex:

    public int getLength(String[] strings) {

      int length = 0;	// declared prematurely

      if (strings == null || strings.length == 0) return 0;

      //length should be declared here

      for (String str : strings) {
        length += str.length();
        }

      return length;
    }


[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/optimizations.html#PrematureDeclaration)
