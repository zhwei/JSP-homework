package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Books;

public class UpdateBook extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateBook() {
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

		// ��֤Ȩ���Ƿ��ǹ���Ա
		response.setContentType("text/html,charset=utf-8");
		HttpSession session = request.getSession();
		
		if((Boolean)session.getAttribute("admin") == null||(Boolean)session.getAttribute("logged") != true){
  			session.setAttribute("alert", "��û��Ȩ�޷��ʸ�ҳ�棡");
  			response.sendRedirect("../login.jsp");
  		}
		
		// �������ݿ�
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
			while(rs.next()){
				Books book = new Books();
				
				book.setId(Integer.valueOf(book_id));
				book.setName(rs.getString("name")); 
				book.setAuthor(rs.getString("author"));
				book.setPrice(Double.valueOf(rs.getString("price")));
				// ��ͼ���ԭ����Ϣ����jspģ��
				request.setAttribute("book", book);
				request.getRequestDispatcher("../createbook.jsp").forward(request, response);
			}
			stat.close();
			conn.close();
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
		// ��֤Ȩ��
		response.setContentType("text/html,charset=utf-8");
		HttpSession session = request.getSession();
		
		if((Boolean)session.getAttribute("admin") == null||(Boolean)session.getAttribute("logged") != true){
  			session.setAttribute("alert", "��û��Ȩ�޷��ʸ�ҳ�棡");
  			response.sendRedirect("login.jsp");
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
			// ��ȡ������
			String name = request.getParameter("name");
			String author = request.getParameter("author");
			String price = request.getParameter("price");
			
			// ���޸ĵ����ݲ��뵽���ݿ�
			String book_id = request.getParameter("id");
			String sql = "update books set name='"+name+"',author='"+author+"',price='"+price+"' where id="+book_id;
			int i = stat.executeUpdate(sql);
			if(i == 1){
				session.setAttribute("alert", "�ɹ��޸�"+String.valueOf(i)+"��ͼ�飡");
				response.sendRedirect("ListBook");
			} else {
				System.out.println(i);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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
