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

public class ReadOneServlet extends HttpServlet {

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
			
			String pquery = "select * from dogboard where id = ?";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(pquery);
			ps.setInt(1, Integer.parseInt(id));
			System.out.println(ps.toString());
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				req.setAttribute("id",rs.getInt("id"));            
				req.setAttribute("title",rs.getString("title"));      
				req.setAttribute("content",rs.getString("content"));    
				req.setAttribute("write_date",rs.getDate("write_date"));   
				req.setAttribute("writer",rs.getString("writer"));     
				req.setAttribute("edit_date",rs.getDate("edit_date"));    
				req.setAttribute("editer",rs.getString("editer"));     
				req.setAttribute("isopen",rs.getInt("isopen"));
			}
			
			ps.close();
			conn.close();
			
			RequestDispatcher rd = req.getRequestDispatcher("/dogBoardModify.jsp");
			rd.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}