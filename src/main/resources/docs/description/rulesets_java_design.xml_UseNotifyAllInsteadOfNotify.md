Thread.notify() awakens a thread monitoring the object.
If more than one thread is monitoring, then only one is chosen.
The thread chosen is arbitrary, its usually safer to call notifyAll() instead.

Ex:

    void bar() {
        // If many threads are monitoring x, only one (and you won't know which) will be notified.
        x.notify();

        // use instead:
        x.notifyAll();
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#UseNotifyAllInsteadOfNotify)