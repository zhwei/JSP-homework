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

public class RemoveFromCart extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RemoveFromCart() {
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
		
		// 验证是否是管理员
		response.setContentType("text/html, charset=utf-8");
		HttpSession session = request.getSession();
		if((Boolean)session.getAttribute("logged") == null||(Boolean)session.getAttribute("logged") != true){
  			session.setAttribute("alert", "此操作需要登录！");
  			response.sendRedirect("login.jsp");
  			return;
  		}
		
		ArrayList<Books> booklist = (ArrayList<Books>)session.getAttribute("cart");
		String book_id = request.getParameter("id");
		
		// 使用循环删除，由于要修改自身数据，不能使用普通循环方式
		Iterator<Books> bookListIterator = booklist.iterator();
		while(bookListIterator.hasNext()){  
		    Books book = bookListIterator.next();  
		    if(book.getId() == Integer.parseInt(book_id)){  
		    	bookListIterator.remove();
		    }  
		}  

		session.setAttribute("cart", booklist);	// 写入session
		response.sendRedirect("UserListBook");	// 重定向
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
