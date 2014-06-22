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
    
    <title>所有投票</title>
    
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
    <h1>所有投票</h1>
  <a href="panel.jsp">后台首页</a>
  <a href="servlet/ManageVote?action=create">添加投票</a>
  <hr/>
    <%@include file="include/alert.jsp" %>
    <%@include file="include/check_auth_admin.jsp" %>  
    <table border="1">
  	<thead>
  		<tr>
  			<th>ID</th><th>标题</th><th>描述</th><th>操作</th>
  		</tr>
  	</thead>
  	<tbody>
  	<c:forEach var="vote" items="${votelist}">
	  	<tr>
  			<td>${ vote.id }</td>
  			<td><a href="servlet/ManageVote?action=detail&id=${ vote.id }">${ vote.title }</a></td>
  			<td>${ vote.description }</td>
  			<td>
  				<a class='btn btn-info' href='servlet/ManageVote?action=update&id=${ vote.id }'>修改</a>
  				<a class='btn btn-danger' href='servlet/ManageVote?action=delete&id=${ vote.id }'>删除</a>
  			</td>
  		</tr>
  	</c:forEach>

  	</tbody>
  </table>
  </body>
</html>
