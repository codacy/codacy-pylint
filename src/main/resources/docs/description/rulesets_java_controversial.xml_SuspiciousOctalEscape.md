A suspicious octal escape sequence was found inside a String literal. An octal escape sequence inside a literal String shall consist of a backslash followed by **OctalDigit | OctalDigit OctalDigit | ZeroToThree OctalDigit OctalDigit**

Ex:

		public void foo() { 
			// interpreted as octal 12, followed by character '8' 
			System.out.println("suspicious: \128"); 
		}

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/controversial.html#SuspiciousOctalEscape)
