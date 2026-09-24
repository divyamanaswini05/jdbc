import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class select {
    public static void main(String[] args) throws Exception {

        Connection con = DBConn.getConn();
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM employee");

        System.out.println("ID\tName\t\tDepartment\tSalary");
        System.out.println("-----------------------------------------------------");

        while (rs.next()) {
            String empid = rs.getString("empid");
            String empname = rs.getString("empname");
            String dept = rs.getString("dept");
            double sal = rs.getDouble("sal");

            System.out.println(empid + "\t" + empname + "\t\t" + dept + "\t" + sal);
        }

        rs.close();
        stmt.close();
        con.close();
    }
}