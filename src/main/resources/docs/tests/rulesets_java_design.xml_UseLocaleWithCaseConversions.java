//#Patterns: rulesets_java_design.xml_UseLocaleWithCaseConversions


public class Foo {

    public void Bar() {

        x = "List";

    	//#Warn: rulesets_java_design.xml_UseLocaleWithCaseConversions
		 boolean result = x.toLowerCase().equals("list");
	 
		 // GOOD
		 String z = a.toLowerCase(Locale.EN);

    }
}

