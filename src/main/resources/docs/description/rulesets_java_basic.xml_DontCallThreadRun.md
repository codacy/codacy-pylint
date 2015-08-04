Explicitly calling Thread.run() method will execute in the caller’s thread of control.
Instead, call Thread.start() for the intended behavior.

Ex:


    Thread t = new Thread();
    t.run();            // use t.start() instead
    new Thread().run(); // same violation

[Source](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/basic.html#DontCallThreadRun)
