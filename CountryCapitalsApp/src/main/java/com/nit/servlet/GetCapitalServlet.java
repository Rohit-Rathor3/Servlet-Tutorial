package com.nit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetCapitalServlet extends HttpServlet {
	
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	 System.out.println("This is getPost() method!!");
	
	//get Writer
	PrintWriter pw =res.getWriter();
	//set response type
	res.setContentType("text/html");
	//get Capital city name
	String[] cnty = {"India","Pakistan","USA","China","Thailand"};
	String[] capital = {"New Delhi","Islamabaad","WashingtonDC","Beijing","Bankok"};
	//read form data
	int country =Integer.parseInt(req.getParameter("country"));
	pw.println("<h1 style='color:blue;text-align:center'>Capital name of country"+cnty[country]+" is::"+capital[country]);
	//add homework
	pw.println("<a href='home.html'>Home</a>");
	
	//close stream
	pw.close();
}

@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    System.out.println("This is doPost() method!!");
	doGet(req,res);
	}

}
