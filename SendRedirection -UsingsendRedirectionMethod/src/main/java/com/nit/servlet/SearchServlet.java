package com.nit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/searchurl")
public class SearchServlet extends HttpServlet {
@Override
public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
// get printWriter
	PrintWriter pw =res.getWriter();
	res.setContentType("text/html");
	// read data from request object
	String key = req.getParameter("ss");
	String engine = req.getParameter("engine");
	String url=null;
	if(engine.equalsIgnoreCase("google"))                        //https://www.google.com/search?q=youtube
	 url="https://www.google.com/search?q="+key;
	else if(engine.equalsIgnoreCase("yahoo"))
		url="https://in.search.yahoo.com/search?p="+key;
	else
		url="https://www.bing.com/search?q="+key;
	
	pw.println("<b>Hello !!</b>");
	res.sendRedirect(url);
	pw.println("<b>Bye</b>"); 
	pw.println("<br><br><h1 style='color:red'><a href='search.hmtl'> <h1>");
	pw.close();
}
}
