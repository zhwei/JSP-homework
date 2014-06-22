package servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ChoiceDao;
import Dao.VoteDao;
import beans.*;

public class ManageChoice extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ManageChoice() {
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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer vote_id = Integer.parseInt(request.getParameter("vote_id"));
		if(request.getParameter("action").equals("create")){
			try {
				request.setAttribute("vote", VoteDao.getVoteById(vote_id));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("../createchoice.jsp").forward(request, response);
			return;
		} else if(request.getParameter("action").equals("delete")){
			Integer id = Integer.parseInt(request.getParameter("id"));
			try {
				Choice choice = ChoiceDao.getChoiceById(id);
				request.setAttribute("name", choice.getDescription());
				request.getRequestDispatcher("../confirm_delete.jsp").forward(request, response);
				return;
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Integer vote_id = Integer.parseInt(request.getParameter("vote_id"));
		if(request.getParameter("action").equals("create")){
			String description = request.getParameter("description");
			try {
				Integer  i = ChoiceDao.createChoice(vote_id, description);
				session.setAttribute("alert", "成功添加"+String.valueOf(i)+"条选项！");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if(request.getParameter("action").equals("delete")){
			try {
				if(request.getParameter("sure").equals("yes")){
					Integer id = Integer.parseInt(request.getParameter("id"));
					Integer  i = ChoiceDao.deleteChoice(id);
					session.setAttribute("alert", "成功删除"+String.valueOf(i)+"条选项！");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		response.sendRedirect("ManageVote?action=detail&id="+String.valueOf(vote_id));
		return;
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
