package com.nt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class BrowserCheckFilter extends HttpFilter {
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// Get printwriter
		PrintWriter pw =res.getWriter();
		
		//set content type
		res.setContentType("text/html");
		//get browser name
		String browser =req.getHeader("user-agent");
		System.out.println(browser);
		if(!browser.contains("Edg"))  // request not accepted . if it is coming from Edge browser
		{  System.out.println("Browser name =========== "+browser);
			System.out.println("----------------------------------Checking Browser ------------------------");
			chain.doFilter(req, res);	
		}
		else
		{
			pw.println("<h1 style='color:red;text-align:center'>Request must be given from Chrome Browser</h1>");
		    return;
		}
		//close strem
		pw.close();
		
	}//doFilter
	
}//close
