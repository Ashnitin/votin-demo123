package votingapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDeo {
	public static boolean validate(String aname,String pass)
	{
		boolean status=false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Voting","root","root");
			PreparedStatement ps=con.prepareStatement("select * from admin where adminid=? and pass=?");
			
			ps.setString(1, aname);
			ps.setString(2, pass);
			
			ResultSet rs=ps.executeQuery();
			status=rs.next();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}
