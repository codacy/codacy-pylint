Methods with numerous parameters are a challenge to maintain, especially if most of them share the same datatype. These situations usually denote the need for new objects to wrap the numerous parameters.

##Example
	// too many arguments liable to be mixed up
	public void addPerson(int birthYear, int birthMonth, int birthDate, int height, int weight, int ssn) { 
		//insert code here
 	} 
 	
 	// preferred approach
 	public void addPerson(Date birthdate, BodyMeasurements measurements, int ssn) { 
 		//insert code here
 	}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/codesize.html#ExcessiveParameterList)
