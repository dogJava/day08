package dogJava;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
	
	private static final long serialVersionUID = 771578936675722864L;

	public void doGet(HttpServletRequest req,HttpServletResponse res)
			throws ServletException,IOException {
    
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		out.println("<HTML>");
		out.println("<BODY>");
		out.println("안녕, 세상아!");
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
	}
	
}
