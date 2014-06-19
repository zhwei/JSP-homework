package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateBook extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CreateBook() {
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
		
		// �ж��Ƿ��ǹ���Ա
		HttpSession session = request.getSession();
		if((Boolean)session.getAttribute("admin") == null||(Boolean)session.getAttribute("logged") != true){
  			session.setAttribute("alert", "��û��Ȩ�޷��ʸ�ҳ�棡");
  			response.sendRedirect("login.jsp");
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
			
			// ��ȡ���ύ������д�����ݿ�
			String name = request.getParameter("name");
			String author = request.getParameter("author");
			String price = request.getParameter("price");
			String sql = "insert into books(name,author,price) values('"+name+"','"+author+"', '"+price+"')";
			int i = stat.executeUpdate(sql);
			if(i == 1){
				session.setAttribute("alert", "�ɹ����"+String.valueOf(i)+"��ͼ�飡");
				response.sendRedirect("ListBook");	// ������
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
