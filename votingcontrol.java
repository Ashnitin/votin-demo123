

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class votingcontrol
 */
@WebServlet("/votingcontrol")
public class votingcontrol extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public votingcontrol() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		String vote=request.getParameter("opr");
		
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Voting","root","root");
			PreparedStatement stmt=con.prepareStatement("insert into vote values(?)");
			
			stmt.setString(1, vote);
		
			
			int row= stmt.executeUpdate();
			if(row>0)
			{
				out.println("<h1> Successfully inserted</h1>");
				RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
				rd.include(request, response);
			}
			else
			{
				out.println("Registration Unsuccessfully");
				RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
				rd.include(request, response);
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
