package com.nt.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class TimeCheckerFilter extends HttpFilter {
	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException{
		//get PrintWriter
	PrintWriter pw =res.getWriter();
	//set response content type
	res.setContentType("text/html");
	//get system date and time
	LocalDateTime ldt = LocalDateTime.now();
	//get current hour of the day
	int hour =ldt.getHour();
    if(hour>=9 && hour<=17)
    	chain.doFilter(req, res);
    else
    {
    	pw.println("<h1 style='color:red;text-align:center'> Request must be given between 9 am to 5 pm</h1>");

    }
    }//doFilter
	
}//class
