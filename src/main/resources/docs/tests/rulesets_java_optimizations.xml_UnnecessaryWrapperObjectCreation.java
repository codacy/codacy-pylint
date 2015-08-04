//#Patterns: rulesets_java_optimizations.xml_UnnecessaryWrapperObjectCreation

public class Foo {

    public void bar(String s) {
      int i, i2;

      //#Warn: rulesets_java_optimizations.xml_UnnecessaryWrapperObjectCreation
      i = Integer.valueOf(s).intValue();
      i = Integer.parseInt(s);

      //#Warn: rulesets_java_optimizations.xml_UnnecessaryWrapperObjectCreation
      i2 = Integer.valueOf(i).intValue();
      i2 = i;

      //#Warn: rulesets_java_optimizations.xml_UnnecessaryWrapperObjectCreation
      String s3 = Integer.valueOf(i2).toString();
      s3 = Integer.toString(i2);

    }

}
