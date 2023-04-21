package com.nit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class CustomerRegistrationServlet extends HttpServlet {
	private static final  String CUSTOMER_INSERT_QUERY = "INSERT INTO SERVLET_CUSTOMER VALUES( CUST_CNO.NEXTVAL,?,?,?,?)";
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	// get PrintWriter
	PrintWriter pw =res.getWriter();
	// set response content type
	res.setContentType("text/html");
	//read form data (req param value)
	String name =req.getParameter("cname");
	String addrs = req.getParameter("cadd");
	float billAmt = Float.parseFloat(req.getParameter("billAmt"));
	long mobileNo = Long.parseLong(req.getParameter("mobileNo"));
	// get pooled JDBC con object
	
	try(Connection con = getPooledConnection(); PreparedStatement ps =con.prepareStatement(CUSTOMER_INSERT_QUERY);)
	{
		// set values to query params
		ps.setString(1, name);
		ps.setString(2, addrs);
		ps.setFloat(3, billAmt);
		ps.setLong(4, mobileNo);
		
		//execute the query
		int result = ps.executeUpdate();
		//process the results
		if(result==1)
			pw.println("<h1 style='color:green'>Customer is Registered</h1>");
		else
			pw.println("<h1 style='color:red'>Customer is not Registered</h1>");
			
		//add home hyperlink
		pw.println("<br><br><a href='customer_register.html'>Home</a>");
	}
	catch(SQLException e)
	{
		e.printStackTrace();
		pw.println("<h1 style='color:red'>Internal Problem- Try Again</h1>"+e.getMessage());
		pw.println("<br><br><a href='customer_register.html'>Home</a>");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		pw.println("<h1 style='color:red'>Internal Problem- Try Again</h1>"+e.getMessage());
		pw.println("<br><br><a href='customer_register.html'>Home</a>");
	}
	
	
}//goGet

@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

 private Connection getPooledConnection() throws Exception{
	 
	 //create InitialContext object
	 InitialContext ic = new InitialContext();
	 //Get Datasource object ref through lookup operation
	 //DataSource ds =(DataSource)ic.lookup("DsJndi");
	 //
	 // get DsJndi name from servlet init param
	 String jndiName = getServletConfig().getInitParameter("jndi");
		 DataSource ds =(DataSource)ic.lookup(jndiName);
	 //get Pooled JDBC Connection
	 Connection con = ds.getConnection();
	 return con;
 }
}
