<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
  		if((Boolean)session.getAttribute("logged") != true){
  			session.setAttribute("alert", "请登录！");
  			response.sendRedirect("login.jsp");
  		}
%>