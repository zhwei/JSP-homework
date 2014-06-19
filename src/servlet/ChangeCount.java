package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Books;

public class ChangeCount extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ChangeCount() {
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
		response.setContentType("text/html, charset=utf-8");
		HttpSession session = request.getSession();
		if((Boolean)session.getAttribute("logged") == null||(Boolean)session.getAttribute("logged") != true){
  			session.setAttribute("alert", "此操作需要登录！");
  			response.sendRedirect("login.jsp");
  			return;
  		}
		
		// 获取购物车内容
		ArrayList<Books> booklist = (ArrayList<Books>)session.getAttribute("cart");
		// 获取待修的图书id
		String book_id = request.getParameter("id");
		// 获取操作名称，用来判断是增长还是减少
		String action = request.getParameter("action");
		
		// 循环判断调用增长或减少
		for(Books book: booklist){
			if(book.getId()==Integer.parseInt(book_id)){
				if(action.equals("incr")){
					// -- 递增数目
					book.incrCount();
				} else if(action.equals("decr")) {
					// -- 递减数目
					book.decrCount();
				}
				
			}
		}

		session.setAttribute("cart", booklist);
		// 重定向
		response.sendRedirect("UserListBook");
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
