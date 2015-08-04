//#Patterns: rulesets_java_design.xml_SimpleDateFormatNeedsLocale
public class Foo {

	private SimpleDateFormat sdf = new SimpleDateFormat("pattern", Locale.ITALY);

    //#Warn: rulesets_java_design.xml_SimpleDateFormatNeedsLocale
	private SimpleDateFormat sdf2 = new SimpleDateFormat("pattern");
	
}

