package com.nit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadParameters;

public class RegistrationServlet extends HttpServlet {
	// insert query to database
	private final static String SAVE_REGISTRATION_STUDENT = "INSERT INTO STUDENT_REGISTRATION VALUES(?,?,?,?,?)";

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// creating 
		PrintWriter pw =res.getWriter();
		res.setContentType("text/html");
        pw.println("<h1>Starting Of Program");
		Connection  con=null;
		PreparedStatement ps =null;

		try {
			//let read data from request object
			MultipartFormDataRequest mpfdr =new MultipartFormDataRequest(req);
			String fname = mpfdr.getParameter("fname");
			String email = mpfdr.getParameter("email");
			String pwd = mpfdr.getParameter("pwd");
			String pno =mpfdr.getParameter("pno");
 
			//uploadbean class object
			UploadBean upb =new UploadBean();
			upb.setOverwrite(false);
			upb.setFilesizelimit(1024*1024*250);
			//C:/Users/rohit/eclipse-workspace-Servlet/Project1-FormRegistration/src/main/webapp/resume
			///Project1-FormRegistration/src/main/webapp/resume
			upb.setFolderstore("C:/Users/rohit/eclipse-workspace-Servlet/Project1-FormRegistration/src/main/webapp/resume");
			upb.store(mpfdr);

			//Here is the logic of getting the file name
			Vector<UploadParameters> names =upb.getHistory();
			ArrayList fileList =new ArrayList();

			names.forEach(upob->{
				///Project1-FormRegistration/src/main/webapp/WEB-INF/resume
				fileList.add("C:/Users/rohit/eclipse-workspace-Servlet/Project1-FormRegistration/src/main/webapp/resume/"+upob.getFilename());
			});
			String resume_path = fileList.get(0).toString();
			// wriring jdbc logic

			//write jdbc code to form data and files location to db table as recod
			//load jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection 
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","System","Rohit123");
			//create PreparedStatement object
			ps =con.prepareStatement(SAVE_REGISTRATION_STUDENT);

			//set values to query
			ps.setString(1, fname);
			ps.setString(2,email);
			ps.setString(3, pwd);
			ps.setString(4, resume_path);
			ps.setString(5, pno);

			//execute the query
			int count =ps.executeUpdate();
			if(count!=0)
			{
				pw.println("Data Saved!!!!!");

			}
			else
			{
				pw.println("Some Problem!!!!!");
			}
			
		RequestDispatcher rd=req.getRequestDispatcher("login.html");
		rd.forward(req, res);
		}
		catch(Exception e)
		{ 
			pw.println("<h1>Some Eception Raised"+e.getMessage());
			e.getMessage();
			
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

		}
	}//doget
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}//class
