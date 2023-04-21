package com.nit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WishMessageServlet extends HttpServlet {

	@Override 
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get PrintWriter
		res.setHeader("refresh", "1");
		PrintWriter pw = res.getWriter();
		//getting current time
		LocalDateTime ld =LocalDateTime.now();
		int hour =ld.getHour();
		pw.println("<h1>"+ld.toString()+"</h1>");
		if(hour>12)
			pw.println("<h1 style='color:red;text-align:center;'> Good Morning </h1>");
		else if(hour>16)
			pw.println("<h1 style='color:red;text-align:center;'> Good Afternoon</h1>");
		else if(hour>20)
			pw.println("<h1 style='color:red;text-align:center;'> Good Evening </h1>");
		else
			pw.println("<h1 style='color:red;text-align:center;'> Good Night </h1>");
	
		// adding home url
		pw.println("<a href ='http://localhost:3030/WishApp/page.html'>Home</a>");
		
		//close stream
		pw.close();
	}//service(-,-)
	
}
