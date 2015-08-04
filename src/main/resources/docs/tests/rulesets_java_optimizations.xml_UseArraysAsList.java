//#Patterns: rulesets_java_optimizations.xml_UseArraysAsList

public class Foo {

    public void bar(int[] ints) {
        List l = new ArrayList(10);
        for (int i = 0; i < 100; i++) {
            //#Warn: rulesets_java_optimizations.xml_UseArraysAsList
            l.add(ints[i]);
        }

        List o = Arrays.asList(ints);
    }

}

