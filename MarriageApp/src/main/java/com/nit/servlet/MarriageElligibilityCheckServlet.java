package com.nit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MarriageElligibilityCheckServlet extends HttpServlet {

	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get writer
		PrintWriter pw=res.getWriter();
		//getting values from the form of webpage
		String name =req.getParameter("pname");
		String gender =req.getParameter("pgender");
		int age= Integer.parseInt(req.getParameter("page"));
		if(gender.equals("female"))
		{
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
				pw.println("<h1 style='color:red;text-align:center'> Mr "+name+" ,you are elligible not for Marriage!!");
				
			}
		}
		
	}
}
