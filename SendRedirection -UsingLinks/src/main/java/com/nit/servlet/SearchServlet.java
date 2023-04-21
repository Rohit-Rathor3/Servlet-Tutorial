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
	if(engine.equalsIgnoreCase("google"))                        //https://www.google.com/search?q=youtube
	 pw.println("<h1 style='color:red;text-align:center'><a href='https://www.google.com/search?q="+key+"'>Click Me (Google)! </h1>");
	else if(engine.equalsIgnoreCase("yahoo"))
		pw.println("<h1 style='color:red;text-align:center'><a href='https://in.search.yahoo.com/search?p="+key+"'>Click Me (Yahoo)! </h1>");
	else
		pw.println("<h1 style='color:red;text-align:center'><a href='https://www.bing.com/search?q="+key+"'>Click Me (Bing)! </h1>");
	 
	pw.println("<br><br><h1 style='color:red'><a href='search.hmtl'> <h1>");
	pw.close();
}
}
