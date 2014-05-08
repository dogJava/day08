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

public class ReadServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		try {

			// db
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/dogjava";
			String user = "root";
			String pass = "ill3628#";

			java.sql.Connection conn = DriverManager.getConnection(url, user,
					pass);
			
			String pquery = "select * from dogboard order by id desc";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(pquery);
			System.out.println(ps.toString());
			
			ResultSet rs = ps.executeQuery();
			
			// List<DogBoard> dogBoardList = null;
			
			List<Map<String,Object>> dogBoardList = new ArrayList<Map<String,Object>>();
			Map<String,Object> boardMap;
			while (rs.next()) {
				boardMap = new HashMap<String,Object>();
				boardMap.put("id",rs.getInt("id"));            
				boardMap.put("title",rs.getString("title"));      
				boardMap.put("content",rs.getString("content"));    
				boardMap.put("write_date",rs.getDate("write_date"));   
				boardMap.put("writer",rs.getString("writer"));     
				boardMap.put("edit_date",rs.getDate("edit_date"));    
				boardMap.put("editer",rs.getString("editer"));     
				boardMap.put("isopen",rs.getInt("isopen"));        
				dogBoardList.add(boardMap);
			}
			
			ps.close();
			conn.close();
			
			req.setAttribute("dogBoardList", dogBoardList);
			RequestDispatcher rd = req.getRequestDispatcher("/dogBoardList.jsp");
			rd.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
