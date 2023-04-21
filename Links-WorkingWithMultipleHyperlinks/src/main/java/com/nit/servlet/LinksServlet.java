package com.nit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LinksServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//getting printwriter
		PrintWriter pw =res.getWriter();
		
		res.setContentType("text/html");
		// reding data 
		String link =req.getParameter("p1");
		
		//logic of the code
		Locale locals[] =Locale.getAvailableLocales();
		
		if(link.equals("link1"))
		{
			// writing logic to get all languages
			pw.println("<ul>");
			for(Locale l :locals)
			{
				pw.println("<l1>"+l.getDisplayLanguage()+"</l1>"+"<br>");
			}
			pw.println("</ul>");
		}

		else if(link.equals("link2"))
		{
			// writing logic to get all countries
			pw.println("<ul");
			for(Locale l :locals)
			{
				pw.println("<l1>"+l.getDisplayLanguage()+"</l1>"+"<br>");
			}
			pw.println("</ul>");
		}
		else
		{
			LocalDate d =LocalDate.now();
			pw.println("<h1>"+d+"</h1>");
		}
		pw.close();
 }
}
