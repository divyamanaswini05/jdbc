import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
    static Connection con;

    public static Connection getConn() {
        try {
            if (con == null) {
                // Load MySQL Driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Create Connection
                con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/company",
                    "root",
                    "root"
                );
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
}