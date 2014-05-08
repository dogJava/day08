package dogJava;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;

public class WriteServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		// parameter
		String title = req.getParameter("title").toString();
		String content = req.getParameter("content").toString();
		String writer = req.getParameter("writer").toString();

		try {

			// db
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/dogjava";
			String user = "root";
			String pass = "ill3628#";

			java.sql.Connection conn = DriverManager.getConnection(url, user,
					pass);
			
			// Statement
			/*
			StringBuffer sb = new StringBuffer();
			sb.append("insert into dogboard (title, content, write_date, writer, edit_date, editer, isopen) values ('");
			sb.append(title);
			sb.append("','");
			sb.append(content);
			sb.append("',");
			sb.append("now()");
			sb.append(",'");
			sb.append(writer);
			sb.append("',");
			sb.append("now()");
			sb.append(",'");
			sb.append(writer);
			sb.append("',");
			sb.append("1");
			sb.append(")");
			Statement stmt = conn.createStatement();
			String query = sb.toString();
			System.out.println(query);
			stmt.execute(query);
			stmt.close();
			*/
			
			String pquery = "insert into dogboard (title, content, write_date, writer, edit_date, editer, isopen) values (?,?,now(),?,now(),?,?)";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(pquery);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setString(3, writer);
			ps.setString(4, writer);
			ps.setInt(5, 1);
			System.out.println(ps.toString());
			ps.execute();
			ps.close();

			conn.close();
			
			res.sendRedirect("/readServlet");

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
