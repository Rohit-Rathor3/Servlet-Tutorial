package com.host.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormServlet extends HttpServlet {
@Override
public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//get printwriter
	PrintWriter pw =res.getWriter();
	
	//read data
	String email =req.getParameter("email");
	String pwd =req.getParameter("pwd");
	
	//validate the data
List<String> errorList = new ArrayList();
	
	if(email==null || email.length()==0 || email.equalsIgnoreCase(""))
		errorList.add("Email is required!!");
	if(pwd==null || pwd.length()==0|| pwd.equalsIgnoreCase(""))
		errorList.add("Password is required ");
	
	if(errorList.size()>0)
	{
		pw.println("<ul style='color:red';text-align:center>");
		for(String error :errorList)
		{
			pw.println("<li>"+error+"</li>");
		}
		pw.println("</ul>");
		return;
	}
	pw.println("SignUp Successfully !!!!");
	
	//closing the PrintWriter
	pw.close();
}
@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	doPost(req, res);
	}
	
}
