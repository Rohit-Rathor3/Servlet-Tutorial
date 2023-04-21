package om.nt.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/empreg")
public class Register  extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name= req.getParameter("name");
		try {
			String stat = "INSERT INTO NEWTABLE VALUES(?)";
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Class loeded ------------");
			//establish connection 
			Connection con = DriverManager.getConnection("jdbc:mysql:///newdb","root","root");
			System.out.println("Data base connected");
			PreparedStatement st =con.prepareStatement(stat);
			st.setString(1, name);
			int a= st.executeUpdate();
			if(a==0)
				System.out.println("Fail");
			else
				System.out.println("Pass");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
