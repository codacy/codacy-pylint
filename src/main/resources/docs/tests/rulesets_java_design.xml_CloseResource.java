//#Patterns: rulesets_java_design.xml_CloseResource

public class Bar {
    public void foo() {
        //#Err: rulesets_java_design.xml_CloseResource
        Connection c = pool.getConnection();
        try {
            // do stuff
        } catch (SQLException ex) {
            // handle exception
        } finally {
            // oops, should close the connection using 'close'!

            // c.close();
        }

        Connection c2 = pool.getConnection();
        try {
            // do stuff
        } catch (SQLException ex) {
            // handle exception
        } finally {
            // oops, should close the connection using 'close'!

            c2.close();
        }
    }
}

