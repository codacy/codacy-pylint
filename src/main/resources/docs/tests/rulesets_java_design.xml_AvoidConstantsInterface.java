//#Patterns: rulesets_java_design.xml_AvoidConstantsInterface

//#Info: rulesets_java_design.xml_AvoidConstantsInterface
public interface ConstantsInterface {
   public static final int CONSTANT1=0;
   public static final String CONSTANT2="1";
}

public interface NormalInterface {
   void doStuff();
}

public class Constants {
   public static final int CONSTANT1=0;
   public static final String CONSTANT2="1";
}