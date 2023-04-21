package com.nit.servlet;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.*;

public class ExcelServlet extends HttpServlet
{
protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException , ServletException
{
// set response content type
res.setContentType("application/vnd.ms-excel");
//get PrintWriter Stream
PrintWriter pw =res.getWriter();
//write content to browser
pw.println("<table border='1' align='center'>");
pw.println("<tr><th>Player Name</th><th>Medal</th><th>Category</th></tr>");
pw.println("<tr><td>Neeraj Chopra</td><td>Gold</td><td>Javeline throw</td></tr>");
pw.println("<tr><td>Meera Bai Chanu</td><td>Silver</td><td>Weight lifting</td></tr>");
pw.println("<tr><td>RaviKumar Dahiya</td><td>Silver</td><td>Wrestling</td></tr>");
pw.println("<tr><td>PV Sindhu</td><td>Bronze</td><td>Badminton</td></tr>");
pw.println("<tr><td>Hockey</td><td>Bronze</td><td>Mens Hockey</td></tr>");
pw.println("<tr><td>Bajrang Puniya</td><td>Bronze</td><td>Wrestling</td></tr>");
pw.println("</table>");
//close stream
pw.close();
}//service
}//class