Avoid negation within an “if” expression with an “else” clause.

Most “if (x != y)” cases without an “else” are often return cases, so consistent use of this rule makes the code easier to read.
Also, this resolves trivial ordering problems, such as “does the error case go first?” or “does the common case go first?”.

Ex:

     int bar(int x, int y) {
            if(x != y) { //this is not ok
                return 1;
            }
            else {
                return 0;
            }

            if(x == y) { //this is ok
               return 0;
            }
            else {
                return 1
            }
     }

     int bar(int x, int y) {
         if(x != y) //this is ok
             return -1;

         //Rest of the method
     }

     boolean bar(int x, int y) {
       return (x != y) ? diff : same; //not ok

       return (x == y) ? same : diff //ok
      }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#ConfusingTernary)
