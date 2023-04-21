package com.nit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HeadServlet extends HttpServlet {
@Override
public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
// get printWriter
	PrintWriter pw = res.getWriter();
	// set content type
	res.setContentType("text/hmtl");
	pw.println("<div style='background-color:green'>");
	pw.println("This is Header Part</h1><br><br>");
	pw.println("<h1>Welcome to My World!!!!1</h1><br><br>");
	pw.println("<h1>--------End of  Header Part</h1>------<br><br>");
	pw.println("</div>");
}
}
