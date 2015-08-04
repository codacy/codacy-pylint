Classes that have too many fields can become unwieldy and could be redesigned to have fewer fields, possibly through **grouping related fields in new objects**. 

For example, a class with individual city/state/zip fields could park them within a single Address field.

##Example

	public class Person { 
		// too many separate fields int birthYear; 
		int birthMonth; 
		int birthDate; 
		float height; 
		float weight; 
	} 
	
	public class Person { 
		// this is more manageable 
		Date birthDate; 
		BodyMeasurements measurements; 
	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/codesize.html#TooManyFields)
