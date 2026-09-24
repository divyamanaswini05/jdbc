import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

class Employee{
	String empid;
	String empname;
	String dept;
	double sal;
	
	public Employee (String empid,String empname, String dept, double sal) {
		this.empid=empid;
		this.empname=empname;
		this.dept=dept;
		this.sal=sal;
	}
}

public class jdbc_insertion1 {
	public static void main(String[] args) throws Exception {
		Connection con = DBConn.getConn();
		String sql = "INSERT INTO employee(empid,empname,dept,sal) VALUES(?, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		ArrayList<Employee> employees = new ArrayList<>();
		employees.add(new Employee("326","dharani", "HR",50000));
		employees.add(new Employee("327","keerthi", "IT",60000));
		employees.add(new Employee("328","charvi", "Finance",70000));
		for(Employee emp : employees) {
			PreparedStatement checkStmt = con.prepareStatement("SELECT COUNT(*) FROM employee WHERE empid=?");
			checkStmt.setString(1, emp.empid);
			ResultSet rs = checkStmt.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			rs.close();
			checkStmt.close();
			
			if(count==0) {
				pstmt.setString(1,  emp.empid);
				pstmt.setString(2,  emp.empname);
				pstmt.setString(3,  emp.dept);
				pstmt.setDouble(4,  emp.sal);
				pstmt.addBatch();
			}
			else {
				System.out.println(" Employee "+ emp.empname + "already exists. Skipping insert. ");
			}
			
		}
		
		int[] results = pstmt.executeBatch();
		for(int i=0; i<results.length;i++) {
			if(results[i] >=0) {
				System.out.println(" Employee " + employees.get(i).empname + "inserted successfully.");
			}
			
			String delsql = "delete from employee WHERE empid  = ?";
			PreparedStatement psdel = con.prepareStatement(delsql);
			psdel.setInt(1, 27);
			if(psdel.executeUpdate()>0) {
				System.out.println("Record deleted successfully.");
			}
			else {
				System.out.println("Record deletion failed.");
			}
			String updatesql = "update  employee  set sal = ? WHERE empid  = ?";
			PreparedStatement psup = con.prepareStatement(updatesql);
			psup.setDouble(1, 75200.0);
			psup.setInt(2, 126);
			
			if(psup.executeUpdate()>0) {
				System.out.println("Record updated successfully.");
			}
			else {
				System.out.println("Record updation failed.");
			}
			
			pstmt.close();
			con.close();
		}
	}

}
