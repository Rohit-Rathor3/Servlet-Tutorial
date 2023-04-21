package com.nit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VaccineElligibilityCheckServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	// get PrintWriter
	res.setContentType("text/html");
	PrintWriter pw =res.getWriter();
	// getting parameters values from req object
	// getting name
	String name = req.getParameter("pname");
	//getting address
	String address = req.getParameter("paddrs");
	//getting age
	String age1 =req.getParameter("page");
	int age=0;
	
	// validating the input
	List<String> errorList = new ArrayList();
	
	if(name==null || name.length()==0 || name.equalsIgnoreCase(""))
		errorList.add("Name is required!!");
	if(address==null || address.length()==0 || address.equalsIgnoreCase(""))
		errorList.add("Address is required!!");
	else if(address.length()<10)
		errorList.add("Adress must contain atleast 10 chars..");
	if(age1==null || age1.length()==0 || age1.equalsIgnoreCase(""))
	{
		errorList.add("Age is required!!");
	}
	else
	{
		try {
			age=Integer.parseInt(age1);
			if(age<=0 && age>125)
				errorList.add("Please enter correct age");
		}
		catch(NumberFormatException e) {
			errorList.add("Please enter valid age, Numeric type!!");
			
		}
	}
		
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
	// writing response to the browser
	if(age<18)
		pw.println("<h1 style='text-align:center;color:red'>Mr/Miss/Mrs. "+name+" , you are not eligible for Vaccination!!!! </h1>");
	else
		pw.println("<h1 style='text-align:center;color:green'>Mr/Miss/Mrs. "+name+" , you are eligible for Vaccination!!!! </h1>");
	
	//creating home link
	pw.println("<a href='http://localhost:3030/CoronaVaccineApp/corona_vaccine.html'>Home</a>");
//closing stream
	pw.close();	
}
}
