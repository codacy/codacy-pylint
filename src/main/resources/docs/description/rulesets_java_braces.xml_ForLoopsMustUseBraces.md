Avoid using 'for' loops without using braces to surround the code block.
If the code formatting or indentation is lost then it becomes difficult to separate the code being controlled from the rest.

Ex:


    for (int i = 0; i < 42; i++) //not recommended
       foo();

    for (int i = 0; i < 42; i++) { //preferred approach
          foo();
    }

[Source](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/braces.html#ForLoopsMustUseBraces)