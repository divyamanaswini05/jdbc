

import java.sql.*;
public class Callable2 {
	public static void main(String[] args) {
		int count = 0;
		try {
			Connection con = DBConn.getConn();
			CallableStatement cs = con.prepareCall("{call EmployeeReport()}");
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				System.out.println("empid : "+rs.getString("empid"));
				System.out.println("empname : "+rs.getString("empname"));
				System.out.println("sal : "+rs.getString("sal"));
				System.out.println("Category : "+rs.getString("salary_category"));
			}
			System.out.println("Total Employees : "+count);

			rs.close();
			cs.close();
			con.close();
				

				
		}
			catch(Exception e) {
				System.out.println("Database error.");
				e.printStackTrace();

		}
	}
}
