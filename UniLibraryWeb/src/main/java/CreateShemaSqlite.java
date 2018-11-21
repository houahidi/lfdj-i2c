
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
 
/**
 *
 * @author sqlitetutorial.net
 */
public class CreateShemaSqlite {
 
    /**
     * Create a new table in the test database
     *
     */
    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:library.db";
        
        // SQL statement for creating a new table
        /*String sql = "CREATE TABLE t_category (\n" + 
        		"  k_puid integer PRIMARY KEY AUTOINCREMENT ,\n" + 
        		"  fk_puid_parent integer default NULL,\n" + 
        		"  name varchar(255) NOT NULL\n" + 
        		")";*/
        	
        String sql = "INSERT INTO t_category VALUES (2, NULL, 'Informatique')";
        
        //String sql = "drop table t_category";
        
        try {
        			Class.forName("org.sqlite.JDBC");
        			Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement();
            // create a new table
            stmt.execute(sql);
            conn.commit();
            System.out.println("success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        createNewTable();
    }
 
}