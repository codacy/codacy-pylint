The conversion of literals to strings by concatenating them with empty strings is inefficient. It is much better to use one of the type-specific toString() methods instead.

Ex:

	  String s = "" + 123; // inefficient 
	  String t = Integer.toString(456); // preferred approach

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/optimizations.html#AddEmptyString)
