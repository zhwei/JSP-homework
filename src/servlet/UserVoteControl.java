package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ChoiceDao;
import Dao.VoteDao;
import beans.Choice;
import beans.Vote;

public class UserVoteControl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserVoteControl() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	
	public String getIpAddr(HttpServletRequest request) {  
	    String ip = request.getHeader("x-forwarded-for");  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }  
	    return ip;  
	} 


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(request.getParameter("action")==null||request.getParameter("action")=="list"){
			try {
				ArrayList<Vote> votelist = VoteDao.listVote();
				request.setAttribute("votelist", votelist);
				request.getRequestDispatcher("../user/listvotes.jsp").forward(request, response);
				return;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if(request.getParameter("action").equals("detail")){
			Integer id = Integer.parseInt(request.getParameter("id"));
			try {
				Vote vote = VoteDao.getVoteById(id);
				request.setAttribute("vote", vote);
				if(request.getParameter("action").equals("detail")){
					request.setAttribute("vote", vote);
					ArrayList<Choice> choicelist = vote.getChoiceList();
					request.setAttribute("choicelist", choicelist);
					request.getRequestDispatcher("../user/detailvote.jsp").forward(request, response);
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if(request.getParameter("action").equals("vote")){
			Integer id = Integer.parseInt(request.getParameter("id"));
			String vote_id = request.getParameter("vote_id");
			Integer user_id = (Integer)session.getAttribute("user_id");
			try {
				ServletContext application=this.getServletContext();
				ArrayList<String> votelimit = null;
				if(application.getAttribute("votelimit") == null){
					votelimit = new ArrayList();
				} else {
					votelimit = (ArrayList<String>)application.getAttribute("votelimit");
				}
				String ip = getIpAddr(request);
				String current = String.valueOf(user_id) + "+" + ip + "+" + vote_id;
				Boolean t = true;
				for(String target: votelimit){
					if(target.equals(current)){
						session.setAttribute("alert", "您已投过票了！");
						t = false;
					}
				}
				if(t){
					ChoiceDao.incrChoiceCount(id);
					votelimit.add(current);
					application.setAttribute("votelimit", votelimit);
				}
				response.sendRedirect("UserVoteControl?action=detail&id="+vote_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
//	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
//		out.println("<HTML>");
//		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
//		out.println("  <BODY>");
//		out.print("    This is ");
//		out.print(this.getClass());
//		out.println(", using the POST method");
//		out.println("  </BODY>");
//		out.println("</HTML>");
//		out.flush();
//		out.close();
//	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
