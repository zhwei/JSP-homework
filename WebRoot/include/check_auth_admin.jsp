<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
  		if((Boolean)session.getAttribute("admin") != true){
  			session.setAttribute("alert", "您没有权限访问该页面！");
  			response.sendRedirect("login.jsp");
  		}
%>