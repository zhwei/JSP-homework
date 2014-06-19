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
import beans.Record;
import beans.Bill;

public class RecordManage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RecordManage() {
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

		// 验证是否为管理员
		HttpSession session = request.getSession();
		if((Boolean)session.getAttribute("admin") == null||(Boolean)session.getAttribute("logged") != true){
  			session.setAttribute("alert", "您没有权限访问该页面！");
  			response.sendRedirect("../login.jsp");
  			return;
  		}
		
		// 连接到数据库
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
			// 获取动作
			String action = request.getParameter("action");
			if(action==null){		// 如果动作为空则将所有购买记录传递到相关jsp模版
				String sql = "select id, uid, date from records";
				ResultSet rs = stat.executeQuery(sql);
				
				ArrayList<Record> recordlist = new ArrayList<Record>();
				while(rs.next()){
					Record record = new Record();	// -- 实例化Record
					record.setId(rs.getInt("id"));
					record.setUid(rs.getInt("uid"));
					record.setDate(rs.getString("date"));
					
					recordlist.add(record);	// -- 将该实例添加到购买记录列表
				}
				// -- 传递到jsp模版
				request.setAttribute("recordlist", recordlist);
				
			} else if(action!=null&&action.equals("bills")){	// 如果action为bills，则传递属于该条购买记录的所有条目到jsp模版

				// 通过传递过来的id获取该条购买记录
				String record_id = request.getParameter("id");
				String sql1 = "select id, uid, date from records where id='"+record_id+"'";
				ResultSet rs = stat.executeQuery(sql1);
				
				Record record = new Record();
				while(rs.next()){
					record.setId(rs.getInt("id"));
					record.setUid(rs.getInt("uid"));
					record.setDate(rs.getString("date"));
				}
				ArrayList<Bill> billlist = record.getMyBills();	// 使用类中定义的方法
				request.setAttribute("billlist", billlist);
			}
			request.getRequestDispatcher("../manage_record.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
