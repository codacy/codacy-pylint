Avoid equality comparisons with Double.NaN. Due to the implicit lack of representation precision when comparing floating point numbers these are likely to cause logic errors.

Ex:

    boolean x = (y == Double.NaN);

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#BadComparison)
