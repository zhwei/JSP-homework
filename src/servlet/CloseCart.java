package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Books;

public class CloseCart extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CloseCart() {
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
		
		// 验证是否登录
		HttpSession session = request.getSession();
		if((Boolean)session.getAttribute("logged") == null||(Boolean)session.getAttribute("logged") != true){
  			session.setAttribute("alert", "此操作需要登录！");
  			response.sendRedirect("../login.jsp");
  			return;
  		}
		 //获取购物车
		ArrayList<Books> booklist = (ArrayList<Books>)session.getAttribute("cart");
		
		// 获取动作，用来判断是进行汇总还是写入数据库
		String action = request.getParameter("action");
		if(action==null){		// -- 没有指定动作则进行汇总
			Double sum = 0.0;
			for(Books book: booklist){
				sum += book.getAllPrice();
			}
			session.setAttribute("cart", booklist);
			request.setAttribute("sum", sum);
		} else if(action!=null&&action.equals("submit")){  // -- submit动作表示将订单写入数据库
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
				PreparedStatement ps = null; 
				ResultSet rs = null; 

				Integer uid = (Integer)session.getAttribute("user_id");  // 获取登录用户的id
				
				// 获取当前时间
				Date date1 = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = df.format(date1);
				
				// 将该条购买记录插入数据库
				String sql = "insert into records(uid, date) values(?, ?)";
				ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
				System.out.println(uid);
				ps.setInt(1, uid);
				ps.setString(2, date);
				ps.executeUpdate();
				
				// -- 获取本条记录的id
				rs = ps.getGeneratedKeys();
				rs.next();
				Integer record_id = rs.getInt(1);
				
				// 将该条购买记录所属条目插入数据库，此处为一对多关系， 一条购买记录对应多条购买条目
				for(Books book: booklist){
					String sql1 = "insert into bills(bid, count, rid) values('"+String.valueOf(book.getId())+"', '"+String.valueOf(book.getCount())+"', '"+String.valueOf(record_id)+"')";
					int i = stat.executeUpdate(sql1);
				}
				session.setAttribute("alert", "订单创建成功！"); // 显示给用户
				session.removeAttribute("cart");		// 清空购物车
				rs.close();
				ps.close();
				conn.close();
				response.sendRedirect("UserListBook");	// 重定向
				return;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher("../bill.jsp").forward(request, response);
		return;
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
