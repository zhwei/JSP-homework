package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

import javax.servlet.http.*;

public class Login extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Login() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String dbUrl = "jdbc:sqlite:d:/db.sqlite";
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbUrl);
			Statement stat = conn.createStatement();
			response.setContentType("text/html,charset=utf-8");
			PrintWriter out = response.getWriter();
			String username = request.getParameter("username");
	    	String password = request.getParameter("password");
	    	String sql = "select id, password from users where username='"+username+"';";
	    	ResultSet rs = stat.executeQuery(sql);
	    	HttpSession session = request.getSession();
	    	int rs_count = 0;
	    	while(rs.next()){
	    		
	    		String db_password = rs.getString("password");
	    		if(db_password.equals(password)){
	    			Integer user_id = rs.getInt("id");
	    			
	    			session.setAttribute("logged", true);
	    			session.setAttribute("user_id", user_id);
	    			session.setAttribute("username", username);
	    			
	    			if(username.equals("admin")){
	    				// 跳转到管理员界面
	    				session.setAttribute("admin", true);
	    				response.sendRedirect("../panel.jsp");
	    			}
	    			else{
	    				// 普通用户界面
	    				response.sendRedirect("../user.jsp");
	    			}
	    		}
	    		else{
	    			session.setAttribute("alert", "登录失败，用户名或密码错误！");
	    			response.sendRedirect("../login.jsp");
	    		}
	    		rs_count += 1;
	    	}
	    	if(rs_count==0){
	    		session.setAttribute("alert", "登录失败，用户名或密码错误！");
	    		response.sendRedirect("../login.jsp");
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
