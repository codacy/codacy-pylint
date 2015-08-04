//#Patterns: rulesets_java_sunsecure.xml_ArrayIsStoredDirectly
    public class Foo {
        private String [] x;

        //#Err: rulesets_java_sunsecure.xml_ArrayIsStoredDirectly
        public void foo (String [] param) { //wrong

            this.x = param;
        }

        public void foo2 (String [] param) { //right

            this.x = Arrays.copyOf(param, param.length);
        }
    }