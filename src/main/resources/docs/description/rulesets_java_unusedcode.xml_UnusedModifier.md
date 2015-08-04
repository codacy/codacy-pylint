Fields in interfaces are automatically public static final, and methods are public abstract.
Classes or interfaces nested in an interface are automatically public and static (all nested interfaces are automatically static).

For historical reasons, modifiers which are implied by the context are accepted by the compiler, but are superfluous.

Ex:

    public interface Foo {
        public abstract void bar(); 		// both abstract and public are ignored by the compiler
        public static final int X = 0; 	    // public, static, and final all ignored
        public static class Bar {} 		    // public, static ignored
        public static interface Baz {} 	    // ditto
    }
    public class Bar {
        public static interface Baz {} // static ignored
    }

[Source](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/unusedcode.html#UnusedModifier)