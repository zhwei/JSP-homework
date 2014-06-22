<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path1 = request.getContextPath();
	String basePath1 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path1+"/";
%>
<%		
  		if((Boolean)session.getAttribute("logged") == null||(Boolean)session.getAttribute("logged") != true){
  			session.setAttribute("alert", "请登录！");
  			response.sendRedirect(basePath1 + "login.jsp");
  		}
%>