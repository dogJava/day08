package dogJava;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;

public class DeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		try {
			
			String id = req.getParameter("id").toString();
			
			// db
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/dogjava";
			String user = "root";
			String pass = "ill3628#";

			java.sql.Connection conn = DriverManager.getConnection(url, user,
					pass);
			
			String pquery = "delete from dogboard where id = ?";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(pquery);
			ps.setInt(1, Integer.parseInt(id));
			System.out.println(ps.toString());
			ps.execute();
			
			ps.close();
			conn.close();
			
			RequestDispatcher rd = req.getRequestDispatcher("/readServlet");
			rd.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
