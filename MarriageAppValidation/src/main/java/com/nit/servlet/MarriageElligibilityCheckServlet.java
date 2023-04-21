package com.nit.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MarriageElligibilityCheckServlet extends HttpServlet {
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get writer
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		//getting values from the form of webpage
		String name =req.getParameter("pname");
		String gender =req.getParameter("pgender");
		String age1= req.getParameter("page");
		String hv =req.getParameter("hiddenCol");
		int age=0;
		List<String> errorList=new ArrayList<String>();
		if(hv.equals("no"))
		{
			System.out.println("Server Side Form Validation");
			pw.println(hv);
			if(name==null || name.equalsIgnoreCase("") || name.length()==0)
				errorList.add("Name is required!!");
			if(gender==null)
				errorList.add("Gender is required!!");
			if(age1==null)
				errorList.add("Age is required!!");
		}//if
		try {
			age=Integer.parseInt(age1);
			if(age<=0 || age>125)
				errorList.add("Age must be Valid !!");
		}
		catch(NumberFormatException e)
		{
			errorList.add("Age must be numeric value!!");
		}
		if(errorList.size()>0)
		{
			pw.println("<ul>");
			for(String error:errorList)
				pw.println("<li>"+error+"</li>");
			pw.println("</ul>");
				return;
		}
		if(gender.equals("female"))
		{   pw.println(hv);
			if(age>=18)
			{
				pw.println("<h1 style='color:green;text-align:center'> Miss "+name+" ,you are elligible for Marriage!!");
			}
			else
			{
				pw.println("<h1 style='color:red;text-align:center'> Miss "+name+" ,you are not elligible for Marriage!!");
			}
		}
		else
		{
			if(age>=21)
			{
				pw.println("<h1 style='color:green;text-align:center'> Mr. "+name+" ,you are elligible for Marriage!!");
			}
			else
			{
				pw.println("<h1 style='color:red;text-align:center'> Mr "+name+" ,you are  not elligible for Marriage!!");
				
			}
		}
	}//service
}//class
