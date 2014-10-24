package org.eclipse.jetty.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="*.hello")
public class HelloWorldServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String msg = req.getServletPath().replace('/', ' ').replaceFirst("\\.hello$", "");
		out.println("<html><head><title>Hello</title></head>");
		out.println("<body style='background-color: #eeffee;'>");
		out.printf("Hello%s%n",msg);
		out.println("</body></html>");
	}
}
