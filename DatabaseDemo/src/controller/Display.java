package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Servlet implementation class Display
 */
@WebServlet("/Display.do")
public class Display extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Display() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		String query = request.getParameter("query");
		query = "SELECT * FROM" + " " + query + " LIMIT 10";
		//query += " UserDetails";
		
		try{
			
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/jndiname");
			java.sql.Connection conn = ds.getConnection();
			PreparedStatement statement = (PreparedStatement) conn.prepareStatement(query);
			ResultSet rs = statement.executeQuery(query);
			JSONObject json = new JSONObject();
			JSONArray arr = new JSONArray();
			
			while(rs.next())
			{
				String input = rs.getString("input");
				arr.put(input);
			}
			json.put("data", arr);
			out.print(json.toString());
			out.flush();
			
			conn.close();
			statement.close();
			out.close();
			
		}catch(Exception e){
			out.println("Query Unsuccesful!");
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
