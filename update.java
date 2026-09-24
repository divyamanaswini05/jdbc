import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class update {
    public static void main(String[] args) throws Exception {

        Connection con = DBConn.getConn();
        Statement stmt = con.createStatement();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Employee ID: ");
        int empid = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter New Employee Name: ");
        String empname = sc.nextLine();

        System.out.print("Enter New Department: ");
        String dept = sc.nextLine();

        System.out.print("Enter New Salary: ");
        int sal = sc.nextInt();

        int i = stmt.executeUpdate(
            "UPDATE employee SET empname='" + empname +
            "', dept='" + dept +
            "', sal=" + sal +
            " WHERE empid=" + empid
        );

        if(i > 0)
            System.out.println("Record Updated Successfully");
        else
            System.out.println("Record Not Found");

        stmt.close();
        con.close();
        sc.close();
    }
}