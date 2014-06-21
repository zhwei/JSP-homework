package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

import javax.servlet.http.*;

import Dao.DBUtils;
import Dao.UserDao;
import beans.User;

public class Login extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Login() {
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
			throws ServletException, IOException{
		
		

		// ��ȡ���ύ������
		String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	
    	HttpSession session = request.getSession();
    	
    	User user = null;
		try {
			user = UserDao.getUserByUsername(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		if(user!=null&&user.getUsername().equals(password)){	// ���������ͬ��ʹ���¼���������Ϣ��ǵ�session
			
			session.setAttribute("logged", true); 	// -- ����û��ѵ�¼
			session.setAttribute("user_id", user.getId());	// -- ����û�id
			session.setAttribute("username", username);	// -- ����û���
			
			if(username.equals("admin")){
				
				session.setAttribute("admin", true);	// ����ǹ���Ա����Ϊ����Ա
				response.sendRedirect("../panel.jsp");	// ��ת������Ա����	
			}
			else{
				response.sendRedirect("../user.jsp");	// ��ͨ�û�����
			}
		}
		else{
			session.setAttribute("alert", "��¼ʧ�ܣ��û������������");
			response.sendRedirect("../login.jsp");
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
