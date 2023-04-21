package com.nit.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

public class FileDownloadingServlet extends HttpServlet {
	// prepare query
		private static final String GET_PHOTO_PATH_BY_NO ="SELECT photo_path FROM UPLOAD_EMPLOYEE WHERE EID=?";
		private static final String GET_RESUME_PATH_BY_NO ="SELECT resume_path FROM UPLOAD_EMPLOYEE WHERE EID=?";
		
		@Override
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			// check which download hyperlink is clicked (resume or photo)
			String query=null;
			int eno=0;
			if(req.getParameter("resumeid")!=null) {
				// for resume
				query=GET_RESUME_PATH_BY_NO;
				eno=Integer.parseInt(req.getParameter("resumeid"));
				
			}
			else {
				//for photo
				query=GET_PHOTO_PATH_BY_NO;
				eno=Integer.parseInt(req.getParameter("photoid"));
			}
			//load the class jdbc deiver class
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}
			catch(ClassNotFoundException cnf)
			{
				cnf.printStackTrace();
			}
			String filePath=null;
			
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","System","Rohit123");
					PreparedStatement ps =con.prepareStatement(query);)
			{
				//set value to query param
				ps.setInt(1, eno);
				
				//execute the query
				try(ResultSet rs =ps.executeQuery()){
					if(rs.next())
						filePath=rs.getString(1);
				}//try2
			}//try1
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			// get Length of the file and make it response content length
			File file =new File(filePath);
			res.setContentLengthLong(file.length());
			//get the mime type make it as response conetent type
			ServletContext sc =getServletContext();
			String mimeType = sc.getMimeType(filePath);
			mimeType =mimeType!=null?mimeType:"application/octet-stream";
			res.setContentType(mimeType);
			// Give insruction to browser to make the recived content as the downlaodable file content
			res.setHeader("Content-Disposition", "attachment;fileName="+file.getName());
			//create inputStream pointing to the file
			InputStream is =new FileInputStream(filePath);
			// create OutputStream pointing to responseobject
			OutputStream os =res.getOutputStream(); // byte stream is taken to deal with both text and non text contetn
			//copy file content to rsponse object (this completes the downloading)
			IOUtils.copy(is,os);
			is.close();
			os.close();
		}
		
		@Override
		public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doGet(req,res);
		}
}
