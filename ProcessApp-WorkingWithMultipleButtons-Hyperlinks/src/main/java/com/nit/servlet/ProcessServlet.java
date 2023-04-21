package com.nit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");;
		// get PrintWriter
		PrintWriter pw =res.getWriter();
		
		// reading form data
		String link =req.getParameter("s1");
		int v1=0 , v2=0;
		if(!link.equals("link1") && !link.equals("link2"))
		{
			v1=Integer.parseInt(req.getParameter("val1"));
			v2=Integer.parseInt(req.getParameter("val2"));
		}
		
		// writing logics for the buttons
		if(link.equalsIgnoreCase("Add"))
		{
			pw.println("<h2>Sum => "+(v1+v2)+"</h2>");
		}
		else if(link.equalsIgnoreCase("Sub"))
		{
			pw.println("<h2>Sum => "+(v1-v2)+"</h2>");
		}
		else if(link.equalsIgnoreCase("Mul"))
		{
			pw.println("<h2>Sum => "+(v1*v2)+"</h2>");
		}
		else if(link.equalsIgnoreCase("Div"))
		{
			pw.println("<h2>Sum => "+((float)v1/v2)+"</h2>");
		}
		else if(link.equalsIgnoreCase("link1"))
		{
			pw.println("<h1> System Properties Are-----");
			pw.println("<b>"+System.getProperties()+"</b>");
		}
		else
		{
			pw.println("<h2>"+LocalDateTime.now()+"</h2>");
		}
		
		pw.println("<a href='form.html'> Home </a>");
		
		pw.close();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
	
}
