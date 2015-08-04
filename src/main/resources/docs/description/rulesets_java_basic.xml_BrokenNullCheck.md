The null check is broken since it will throw a NullPointerException itself.
It is likely that you used || instead of && or vice versa.

Ex:

    public String bar(String string) {
      // should be &&
        if (string!=null || !string.equals(""))
            return string;
      // should be ||
        if (string==null && string.equals(""))
            return string;
    }

[Source](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/basic.html#BrokenNullCheck)
