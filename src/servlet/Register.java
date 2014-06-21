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

import Dao.UserDao;

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
			
			// 获取表单数据
			String username = request.getParameter("username");
	        String password1 = request.getParameter("password1");
	        String password2 = request.getParameter("password2");
	        String sex = request.getParameter("sex");
	        
	        // 验证两次输入的密码是否相同
	        if(password1.equals(password2)==false){
	        	session.setAttribute("alert", "两次输入密码不相同，请重新注册！");
	        	response.sendRedirect("../register.jsp");
	        }
	        else{	// 通过验证则插入数据库
	        	 int i = UserDao.createUser(username, password1, sex);
		         if(i==1){
		        	  session.setAttribute("alert", "注册成功，请登录");
		        	  response.sendRedirect("../login.jsp");
		         }
		         stat.close();
		         conn.close();
	        }
		} catch (SQLException e) {
			if(e.getMessage().equals("column username is not unique")){
				session.setAttribute("alert", "该用户名已被注册，请重新注册！");	// 通过异常使用户唯一
				response.sendRedirect("../register.jsp");
			} 
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
