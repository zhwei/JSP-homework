package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Register extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Register() {
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

		response.setContentType("text/html");
		HttpSession session = request.getSession();
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
			String username = request.getParameter("username");
	        String password1 = request.getParameter("password1");
	        String password2 = request.getParameter("password2");
	        String sex = request.getParameter("sex");
	        
//	        int rs1 = stat.executeUpdate("select count(*) from users where username='"+ username+"';");
//	        System.out.println(rs1);
//	        if(rs1 != 0){
//	        	session.setAttribute("alert", "该用户名已被注册，请重新注册！");
//	        	response.sendRedirect("../register.jsp");
//	        }
	        
	        if(password1.equals(password2)==false){
	        	session.setAttribute("alert", "两次输入密码不相同，请重新注册！");
	        	response.sendRedirect("../register.jsp");
	        }
	        else{
	            String sql = "insert into users(username, password, sex) values('"+username+"','"+password1+"','"+ sex +"');";
	          int i = stat.executeUpdate(sql);
	          if(i==1){
	        	  session.setAttribute("alert", "注册成功，请前往登录页面");
	        	  response.sendRedirect("../login.jsp");
	          }
	          stat.close();
	          conn.close();
	        }
		} catch (SQLException e) {
			if(e.getMessage().equals("column username is not unique")){
				session.setAttribute("alert", "该用户名已被注册，请重新注册！");
				response.sendRedirect("../register.jsp");
			}
			// System.out.println(e.getMessage());
			// e.printStackTrace();
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
