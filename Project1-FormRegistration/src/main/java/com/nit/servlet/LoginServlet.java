package com.nit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet  extends HttpServlet{
	// creating select query
	private final static String GET_DETAILS_QUERY ="SELECT SNAME,EMAIL,PNO FROM STUDENT_REGISTRATION WHERE EMAIL=? AND PWD=?";
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw =res.getWriter();
		res.setContentType("text/html");
		
		//READ REQUEST OBJECT DATA
		String email =req.getParameter("email");
		String pwd= req.getParameter("pwd");
		Connection con =null;
		PreparedStatement ps=null;
		ResultSet rs= null;
		
		try {
			//writing jdbc logic here,
			//loading class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","System","Rohit123");
			//create PreparedStatement object
			ps =con.prepareStatement(GET_DETAILS_QUERY);
			// SETTING VALUES TO STATEMENT OBJECT
			ps.setString(1, email);
			ps.setString(2, pwd);
			
			// let execute the query
			rs= ps.executeQuery();
			pw.println("<table border='1' align='center' ,bgcolor='cyan'");
			pw.println("<tr><th>Student Name</th> <th>Email</th> <th>Phone no</th></tr>");
			while(rs.next())
			{
				pw.println("<tr>");
				pw.println("<td>"+rs.getString(1)+"</td>");
				pw.println("<td>"+rs.getString(2)+"</td>");
				pw.println("<td>"+rs.getString(3)+"</td>");
				pw.println("</tr>");
			}
			pw.println("</table>");
		}
		catch(Exception e)
		{
			pw.println("<h2>Error Raised--->"+e.getMessage()+"</h2>");
		}
		finally
		{
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			try {
				if(ps!=null)
					ps.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	    }
	
}
