<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>订单管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h1>订单管理</h1>
     <a href="servlet/Logout">注销</a>
    <hr>
    <%@include file="include/alert.jsp" %>
    <%@include file="include/check_auth_admin.jsp" %>
    <table border="1">
  	<thead>
  		<tr>
  			<th>编号</th><th>用户</th><th>时间</th>
  		</tr>
  	</thead>
  	<tbody>
  	<c:forEach var="record" items="${recordlist}">
	  	<tr>
  			<td>${ record.id }</td>
  			<td></td>
  			<td>${ record.date }</td>
  		</tr>
  	</c:forEach>

  	</tbody>
  </table>
  </body>
</html>
