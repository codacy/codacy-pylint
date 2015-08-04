Ensure that resources (like Connection, Statement, and ResultSet objects) are always closed after use.
Closing the objects releases the resources (like databases or sockets).

Ex:

    public class Bar {
      public void foo() {
        Connection c = pool.getConnection();
        try {
          // do stuff
        } catch (SQLException ex) {
         // handle exception
        } finally {
          // oops, should close the connection using 'close'!
          // c.close();
        }
      }
    }

[SOURCE](http://pmd.sourceforge.net/pmd-5.3.2/pmd-java/rules/java/design.html#CloseResource)
