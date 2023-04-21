package com.nit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeSearchServlet extends HttpServlet {
	private static final String GET_EMP_BY_ENO="SELECT EMPNO,ENAME,SAL,JOB,DEPTNO FROM EMP WHERE EMPNO =?";
	int no;
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		RequestDispatcher rd =req.getRequestDispatcher("/errurl");
		try
		{
			//get printwriter 
			
			pw =res.getWriter();
			//set response content type
			res.setContentType("text/html");
			//get access to servletconfig object
			ServletConfig cg =getServletConfig();
			//read servlet init param values
			String driver =cg.getInitParameter("driverClass");
			String url =cg.getInitParameter("url");
			String user=cg.getInitParameter("dbuser");
			String pwd=cg.getInitParameter("dbpwd");
			
			//read form data
			try {
				no =Integer.parseInt(req.getParameter("eno"));
			}
			catch(NumberFormatException e)
			{
				rd.forward(req, res);
				
			}
			try {
				Class.forName(driver);
			}//try
			catch(ClassNotFoundException e)
			{   
				rd.forward(req, res);
				e.printStackTrace();
			}//catch
			
			//write jdbc code
			try(Connection con = DriverManager.getConnection(url,user,pwd);PreparedStatement ps =con.prepareStatement(GET_EMP_BY_ENO))
			{
				if(ps!=null) {
					ps.setInt(1, no);
				}
		
				try(ResultSet rs =ps.executeQuery())
				{  
					if(rs!=null) {
						if(rs.next())
						{ 
							pw.println("<h1 style='color:blue;text-align:center'>------------ Employee Details --------</h1>");
							
							pw.println("<p style='text-align:center'><b>Employee Number ::"+rs.getInt(1)+"</b><br>");
							pw.println("<b>Employee Name ::"+rs.getString(2)+"</b><br>");
							pw.println("<b>Employee Salary ::"+rs.getFloat(3)+"</b><br>");
							pw.println("<b>Employee Desg::"+rs.getString(4)+"</b><br>");
							pw.println("<b>Employee Dept No ::"+rs.getInt(5)+"</b><br>");
						}//if
						else
						{
							pw.println("<h1>Hello no, result found!</h1>");
						}//else
					}//if
					
					pw.println("<br><br><p style='text-align:center'><a href='index.html'>Home</a>");
					//close stream
					pw.close();
					
				}//try2
			}//try1
		}//try3
		catch(Exception se)
		{   rd.forward(req, res);
			se.printStackTrace();
		}
			
}//doGet
}//class
