Avoid using if..else statements without using braces to surround the code block.
If the code formatting or indentation is lost then it becomes difficult to separate the code being controlled from the rest.

Ex:

       // this is OK
    if (foo) {
           x = x+1;
    }
    else {
           x = x-1;
    }


       // but this is not
    if (foo)
           x = x+1;
       else
           x = x-1;

[Source](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/braces.html#IfElseStmtsMustUseBraces)