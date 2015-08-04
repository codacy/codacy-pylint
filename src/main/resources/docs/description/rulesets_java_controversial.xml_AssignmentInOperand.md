Avoid assignments in operands. This can make code more complicated and harder to read.
The programmers are used to test for things inside an if condition, you have to pay extra attention to see that the if condition is changing the value of some variable.
The compilers are the experts on optimizations and little tricks, produce the code to be read and understandable for others.

Ex:

    public void bar() {
        int x = 2;
        if ((x = getX()) == 3) {
          System.out.println("3!");
        }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/controversial.html#AssignmentInOperand)