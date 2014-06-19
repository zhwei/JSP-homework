package servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Books;

public class ListBook extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ListBook() {
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
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 验证是否管理员
		HttpSession session = request.getSession();
		if((Boolean)session.getAttribute("admin") == null||(Boolean)session.getAttribute("logged") != true){
  			session.setAttribute("alert", "您没有权限访问该页面！");
  			response.sendRedirect("../login.jsp");
  			return;
  		}
		// 连接数据库
		String dbUrl = "jdbc:sqlite:d:/db.sqlite";
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = null;
		Statement stat;
		try {
			conn = DriverManager.getConnection(dbUrl);
			stat = conn.createStatement();
			
			// 从数据库获取全部图书，通过request传递到jsp模版
			String sql = "select id, name, author, price from books";
			ResultSet rs = stat.executeQuery(sql);
			ArrayList<Books> booklist = new ArrayList<Books>();
			while(rs.next()){
				Books book = new Books();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name")); 
				book.setAuthor(rs.getString("author"));
				book.setPrice(Double.valueOf(rs.getString("price")));
				booklist.add(book);
			}
			request.setAttribute("booklist", booklist);
			request.getRequestDispatcher("../listbooks.jsp").forward(request, response);
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
