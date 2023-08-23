

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Registercontrol
 */
@WebServlet("/Registercontrol")
public class Registercontrol extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registercontrol() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		String uname=request.getParameter("uname");
		String pass=request.getParameter("pass");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Voting","root","root");
			PreparedStatement stmt=con.prepareStatement("insert into reg values(?,?,?,?)");
			stmt.setString(1, uname);
			stmt.setString(2, pass);
			stmt.setString(3, email);
			stmt.setString(4, phone);
			
			int row= stmt.executeUpdate();
			if(row>0)
			{
				HttpSession session=request.getSession();
				session.setAttribute("sucmsg", "Register Successfully");
				out.println("<h1>Registration Successfully</h1>");
				RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
			}
			else
			{
				out.println("Registration Unsuccessfully");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
