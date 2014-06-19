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
		// ��ָ��ͼ����ӵ����ﳵ
		
		HttpSession session = request.getSession();	// ��ȡsession
		// �ж��û��Ƿ��¼�� û�е�¼���ض��򵽵�¼����
		if((Boolean)session.getAttribute("logged") == null||(Boolean)session.getAttribute("logged") != true){
  			session.setAttribute("alert", "�˲�����Ҫ��¼��");
  			response.sendRedirect("../login.jsp");
  			return;
  		}
		
		// ���ӵ����ݿ�
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
			
			// ���session�в����ڹ��ﳵ�򴴽����ﳵ
			ArrayList<Books> booklist = new ArrayList<Books>();
			if(session.getAttribute("cart") != null){
				booklist = (ArrayList<Books>)session.getAttribute("cart");
			}
			
			// ��ָ����ͼ����ӵ����ﳵ
			while(rs.next()){
				// -- ����ͼ��ʵ��
				Books book = new Books();
				book.setId(Integer.valueOf(book_id));
				book.setName(rs.getString("name")); 
				book.setAuthor(rs.getString("author"));
				book.setPrice(Double.valueOf(rs.getString("price")));
				book.setCount(1);
				
				// -- �жϹ��ﳵ���Ƿ���ڸ�ͼ�飬���������������Ŀ�������ھ���ӵ����ﳵ
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
			// �����ﳵͼ���б�����д��session
			session.setAttribute("cart", booklist);
			// �ض�������ͼ�����
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
