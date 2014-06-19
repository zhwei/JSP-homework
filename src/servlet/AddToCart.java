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

public class AddToCart extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddToCart() {
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
		// 将指定图书添加到购物车
		
		HttpSession session = request.getSession();	// 获取session
		// 判断用户是否登录， 没有登录则重定向到登录界面
		if((Boolean)session.getAttribute("logged") == null||(Boolean)session.getAttribute("logged") != true){
  			session.setAttribute("alert", "此操作需要登录！");
  			response.sendRedirect("../login.jsp");
  			return;
  		}
		
		// 连接到数据库
		String dbUrl = "jdbc:sqlite:d:/db.sqlite";
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbUrl);
			Statement stat = conn.createStatement();
			String book_id = request.getParameter("id");
			String sql1 = "select name, author, price from books where id='"+book_id+"'";
			ResultSet rs = stat.executeQuery(sql1);
			
			// 如果session中不存在购物车则创建购物车
			ArrayList<Books> booklist = new ArrayList<Books>();
			if(session.getAttribute("cart") != null){
				booklist = (ArrayList<Books>)session.getAttribute("cart");
			}
			
			// 将指定的图书添加到购物车
			while(rs.next()){
				// -- 创建图书实例
				Books book = new Books();
				book.setId(Integer.valueOf(book_id));
				book.setName(rs.getString("name")); 
				book.setAuthor(rs.getString("author"));
				book.setPrice(Double.valueOf(rs.getString("price")));
				book.setCount(1);
				
				// -- 判断购物车中是否存在该图书，如果存在则增长数目，不存在就添加到购物车
				Boolean t = false;
				for(Books bo: booklist){
					if(bo.equals(book)){
						t = true;
						bo.incrCount();
					}
				}
				if(t == false){
					booklist.add(book);
				}
			}
			stat.close();
			conn.close();
			// 将购物车图书列表重新写入session
			session.setAttribute("cart", booklist);
			// 重定向到所有图书界面
			response.sendRedirect("UserListBook");
			return;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
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
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
