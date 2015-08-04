Sometimes two consecutive 'if' statements can be consolidated by separating their conditions with a boolean short-circuit operator.
Doing this makes the code more readable and understandable.

Ex:

    void bar() {
        if (x) {			// original implementation
            if (y) {
                // do stuff
            }
        }
    }

    void bar() {
        if (x && y) {		// optimized implementation
            // do stuff
        }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/basic.html#CollapsibleIfStatements)
