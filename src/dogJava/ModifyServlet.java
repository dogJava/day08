package dogJava;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;

public class ModifyServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		System.out.println("ModifyServlet doPost");

		try {
			req.setCharacterEncoding("UTF-8");

			// parameter
			String id = req.getParameter("id").toString();
			String title = req.getParameter("title").toString();
			String content = req.getParameter("content").toString();
			String editer = req.getParameter("editer").toString();

			// db
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/dogjava";
			String user = "root";
			String pass = "ill3628#";

			Connection conn = DriverManager.getConnection(url, user, pass);

			String pquery = "update dogboard set title = ?, content = ?, edit_date = now(), editer = ? where id = ?";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(pquery);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setString(3, editer);
			ps.setInt(4, Integer.parseInt(id));
			ps.execute();

			ps.close();
			conn.close();
			
			res.sendRedirect("/readServlet");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
