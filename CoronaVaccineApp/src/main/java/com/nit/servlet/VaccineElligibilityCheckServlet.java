package com.nit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VaccineElligibilityCheckServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	// get PrintWriter
	PrintWriter pw =res.getWriter();
	// getting parameters values from req object
	// getting name
	String name = req.getParameter("pname");
	//getting address
	String address = req.getParameter("paddrs");
	//getting age
	int age =Integer.parseInt(req.getParameter("page"));
	
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
