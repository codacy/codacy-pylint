The dataflow analysis tracks local definitions, undefinitions and references to variables on different paths on the data flow. From those informations there can be found various problems.

* **UR - Anomaly**: There is a reference to a variable that was not defined before. This is a bug and leads to an error.
* **DU - Anomaly**: A recently defined variable is undefined. These anomalies may appear in normal source text.
* **DD - Anomaly**: A recently defined variable is redefined. This is ominous but don’t have to be a bug.

Ex:

	public void foo() { 
		int buz = 5; 
		buz = 6; // redefinition of buz -> dd-anomaly 
		foo(buz); 
		buz = 2; 
	} // buz is undefined when leaving scope -> du-anomaly

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/controversial.html#DataflowAnomalyAnalysis)
