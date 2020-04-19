//-- Prajakta Bhosale TECOB201--

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class myServlet
 */
@WebServlet("/myServlet")
public class myServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String url="jdbc:mysql://localhost:3306/Prajakta";
	private static final String userName="root";
	private static final String password="9881487034";

    /**
     * Default constructor. 
     */
    public myServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection conn=DriverManager.getConnection(url,userName,password);
			out.println("<h1>Connection Established!</h1>");
			java.sql.Statement stmt=conn.createStatement();
									
			String act = request.getParameter("action");
			
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String pass = request.getParameter("password");
		
			if (act.equals("Insert")) {
				
		
				int i=stmt.executeUpdate("insert into Student values('"+name+"','"+email+"','"+pass+"')");
				if (i<0)
					out.println("Error");
				else
					out.println("Inserted successfully");
			}
			
			if (act.equals("Select")) 
			{
				ResultSet rs=stmt.executeQuery("select name, email,password from Student where name='"+name+"';");
				while (rs.next())
				{
				
		            out.println("Name: " + name + "<br>");
		            out.println("Email: " + email + "<br>");
		            out.println("Password: " + pass + "<br>");	   
				        }
			}
			 
			 if (act.equals("Delete")) 
			 {
				 int i=stmt.executeUpdate("delete from Student where name = '"+name+"'");
				 if (i<0)
						out.println("Error");
					else
						out.println("Deleted successfully");
				 
			 }
			
			if(act.equals("Update"))
			{
				
				java.sql.PreparedStatement st = conn.prepareStatement("update Student set name=?,email=?,password=? where name= '"+name+"'"); 
				st.setString(1, name);
		        st.setString(2,email);
		        st.setString(3,pass);
		        st.executeUpdate();
		    	out.println("Updated successfully");
			}
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			out.println(e);
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
