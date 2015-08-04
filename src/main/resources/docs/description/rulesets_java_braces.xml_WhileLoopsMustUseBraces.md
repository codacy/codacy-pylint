Avoid using 'while' loops without using braces to surround the code block.
If the code formatting or indentation is lost then it becomes difficult to separate the code being controlled from the rest.

Ex:

    while (true)	// not recommended
        x++;

    while (true) {	// preferred approach
        x++;
    }

[Source](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/braces.html#WhileLoopsMustUseBraces)