package com.nit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadParameters;

public class EmployeeRegistrationServlet extends HttpServlet {

	private static final String INSERT_EMPLOYEE_QUERY = "INSERT INTO UPLOAD_EMPLOYEE VALUES(empno_seq.NEXTVAL,?,?,?,?)";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
   
		//get printWriter
		PrintWriter pw =res.getWriter();
		// set response content type
		res.setContentType("text/html");
		
		if(req.getParameter("ename")=="" || req.getParameter("ename")==null)
		{
			pw.println("<h1>Plz Don't paste url directly</h1>");
			pw.close();
			return;
		}
		Connection con=null;
		PreparedStatement ps=null;
		try {
			//read special reauest object
			MultipartFormDataRequest nreq = new MultipartFormDataRequest(req);
			//read form data
			String name=nreq.getParameter("ename");
			String addrs=nreq.getParameter("eadd");
			//create UploadBean class obj
			UploadBean upb = new UploadBean();
			upb.setFolderstore("E:/Store");
			upb.setOverwrite(false);
			upb.setFilesizelimit(50*1024*1024);
			// complete file upload process
			upb.store(nreq);
			pw.println("<b>files are uploaded sucessfully!!!!!</b>");
			//get the names of the uploaded files
			Vector<UploadParameters> vector = upb.getHistory();
			ArrayList<String> fileList = new ArrayList();
			vector.forEach(up->{
				fileList.add("E:/Store/"+up.getFilename());
			});
			
			//write jdbc code to form data and files location to db table as recod
			//load jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection 
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","System","Rohit123");
			//create PreparedStatement object
			ps =con.prepareStatement(INSERT_EMPLOYEE_QUERY);
			// set vlues to query
			ps.setString(1, name);
			ps.setString(2, addrs);
			ps.setString(3, fileList.get(0));
			ps.setString(4, fileList.get(1));
			
			//execute the query
			int count = ps.executeUpdate();
			if(count==1)
			{
				pw.println("<h1 style='color:green; text-align:center'>Employee Registered!!!1</h1>");
			}
			else
			{
				pw.println("<h1 style='color:red; text-align:center'>Employee Not Registered!!!1</h1>");
			}
			
		}
		catch(Exception e)
		{
			pw.println("<h1> Problem in file Uploading </b>"+e.getMessage());
		}
		finally {
			//close jdbc objects
			try {
				if(ps!=null)
					ps.close();
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
		}//finally
		//add home link
		pw.println("<a href='employee_register.html'>Home </a>");
		pw.close();
	}//doPost	
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}//class
