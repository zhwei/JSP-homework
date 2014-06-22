package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Choice;
import beans.Vote;

import Dao.VoteDao;

public class ManageVote extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ManageVote() {
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
		
		if(request.getParameter("action")==null||request.getParameter("action")=="list"){
			try {
				ArrayList<Vote> votelist = VoteDao.listVote();
				request.setAttribute("votelist", votelist);
				request.getRequestDispatcher("../listvotes.jsp").forward(request, response);
				return;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if(request.getParameter("action").equals("create")){
			request.getRequestDispatcher("../createvote.jsp").forward(request, response);
			return;
			
		} else if(request.getParameter("action").equals("update")||request.getParameter("action").equals("delete")||request.getParameter("action").equals("detail")){
			Integer id = Integer.parseInt(request.getParameter("id"));
			try {
				Vote vote = VoteDao.getVoteById(id);
				request.setAttribute("vote", vote);
				if(request.getParameter("action").equals("update")){
					request.getRequestDispatcher("../createvote.jsp").forward(request, response);
					return;
				} else if(request.getParameter("action").equals("delete")){
					request.setAttribute("name", vote.getTitle());
					request.getRequestDispatcher("../confirm_delete.jsp").forward(request, response);
					return;
				} else if(request.getParameter("action").equals("detail")){
					request.setAttribute("vote", vote);
					ArrayList<Choice> choicelist = vote.getChoiceList();
					request.setAttribute("choicelist", choicelist);
					request.getRequestDispatcher("../detailvote.jsp").forward(request, response);
					return;
				}
				
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
		if(request.getParameter("action").equals("create")){
			
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			
			try {
				Integer  i = VoteDao.createVote(title, description);
				session.setAttribute("alert", "成功添加"+String.valueOf(i)+"条投票！");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if(request.getParameter("action").equals("update")){
			
			Integer id = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			
			try {
				Integer  i = VoteDao.updateVote(id, title, description);
				session.setAttribute("alert", "成功修改"+String.valueOf(i)+"条投票！");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(request.getParameter("action").equals("delete")){
			try {
				if(request.getParameter("sure").equals("yes")){
					Integer id = Integer.parseInt(request.getParameter("id"));
					Integer  i = VoteDao.deleteVote(id);
					session.setAttribute("alert", "成功删除"+String.valueOf(i)+"条投票！");
				} else {response.sendRedirect("ManageVote");}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.sendRedirect("ManageVote");
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
