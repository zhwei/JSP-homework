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

public class UserListBook extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserListBook() {
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
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html, charset=utf-8");
		HttpSession session = request.getSession();
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
			String sql = "select id, name, author, price from books";
			ResultSet rs = stat.executeQuery(sql);
			// 创建图书列表
			ArrayList<Books> booklist = new ArrayList<Books>();
			while(rs.next()){
				Books book = new Books();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name")); 
				book.setAuthor(rs.getString("author"));
				book.setPrice(Double.valueOf(rs.getString("price")));
				booklist.add(book);
				
			}
			request.setAttribute("booklist", booklist);	 // 将图书列表传往jsp模版
			// 获取购物车数据
			ArrayList<Books> cart = (ArrayList<Books>)session.getAttribute("cart");
			request.setAttribute("cart", cart);	// 將购物车数据传到模版
			
			request.getRequestDispatcher("../user-listbooks.jsp").forward(request, response);
			
			return;
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
