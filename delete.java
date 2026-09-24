import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class delete {
    public static void main(String[] args) throws Exception {

        Connection con = DBConn.getConn();
        Statement stmt = con.createStatement();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Employee ID to Delete: ");
        int empid = sc.nextInt();

        int i = stmt.executeUpdate(
            "DELETE FROM employee WHERE empid=" + empid
        );

        if(i > 0)
            System.out.println("Record Deleted Successfully");
        else
            System.out.println("Record Not Found");

        stmt.close();
        con.close();
        sc.close();
    }
}