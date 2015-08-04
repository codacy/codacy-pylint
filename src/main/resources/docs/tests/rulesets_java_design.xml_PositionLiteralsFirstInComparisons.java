//#Patterns: rulesets_java_design.xml_PositionLiteralsFirstInComparisons

class Foo {
  boolean bar(String x) {
	//#Err: rulesets_java_design.xml_PositionLiteralsFirstInComparisons
    return x.equals("2"); // should be "2".equals(x)
  }

  boolean bar2(String x) {
    return "2".equals(x);
  }
}

