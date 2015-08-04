A catch clause catching an exception and then testing the instance type inside does not make any sense.
The multiple catch mechanism is already built to catch different types of exceptions.
Each caught exception type should be handled in its own catch clause.

Ex:

    // Avoid this
    try {

        //Some code

    }
    catch (Exception ee) {
        if (ee instanceof IOException) {
            cleanup();
        }
    }


    // Prefer this
    try {

        // do something

    }
    catch (IOException ee) {
        cleanup();
    }
    catch (OtherException ee) {
         closeDatabase();
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#AvoidInstanceofChecksInCatchClause)
