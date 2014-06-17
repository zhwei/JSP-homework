<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
  		//if((Boolean)session.getAttribute("admin") != true){
  		if((Boolean)session.getAttribute("admin") == null||(Boolean)session.getAttribute("logged") != true){
  			session.setAttribute("alert", "您没有权限访问该页面！");
  			response.sendRedirect("login.jsp");
  		}
%>