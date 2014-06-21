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
		
		// ��֤�Ƿ��¼
		HttpSession session = request.getSession();
		if((Boolean)session.getAttribute("logged") == null||(Boolean)session.getAttribute("logged") != true){
  			session.setAttribute("alert", "�˲�����Ҫ��¼��");
  			response.sendRedirect("../login.jsp");
  			return;
  		}
		 //��ȡ���ﳵ
		ArrayList<Books> booklist = (ArrayList<Books>)session.getAttribute("cart");
		
		// ��ȡ�����������ж��ǽ��л��ܻ���д�����ݿ�
		String action = request.getParameter("action");
		if(action==null){		// -- û��ָ����������л���
			Double sum = 0.0;
			for(Books book: booklist){
				sum += book.getAllPrice();
			}
			session.setAttribute("cart", booklist);
			request.setAttribute("sum", sum);
		} else if(action!=null&&action.equals("submit")){  // -- submit������ʾ������д�����ݿ�
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

				Integer uid = (Integer)session.getAttribute("user_id");  // ��ȡ��¼�û���id
				
				// ��ȡ��ǰʱ��
				Date date1 = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = df.format(date1);
				
				// �����������¼�������ݿ�
				String sql = "insert into records(uid, date) values(?, ?)";
				ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
				System.out.println(uid);
				ps.setInt(1, uid);
				ps.setString(2, date);
				ps.executeUpdate();
				
				// -- ��ȡ������¼��id
				rs = ps.getGeneratedKeys();
				rs.next();
				Integer record_id = rs.getInt(1);
				
				// �����������¼������Ŀ�������ݿ⣬�˴�Ϊһ�Զ��ϵ�� һ�������¼��Ӧ����������Ŀ
				for(Books book: booklist){
					String sql1 = "insert into bills(bid, count, rid) values('"+String.valueOf(book.getId())+"', '"+String.valueOf(book.getCount())+"', '"+String.valueOf(record_id)+"')";
					int i = stat.executeUpdate(sql1);
				}
				session.setAttribute("alert", "���������ɹ���"); // ��ʾ���û�
				session.removeAttribute("cart");		// ��չ��ﳵ
				rs.close();
				ps.close();
				conn.close();
				response.sendRedirect("UserListBook");	// �ض���
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
