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

  </head>
  
  <body>
    <h1>所有投票</h1>
  <a href="panel.jsp">后台首页</a>
  <a href="servlet/ManageVote?action=create">添加投票</a>
  <hr/>
    <%@include file="../include/alert.jsp" %>
    <%@include file="../include/check_auth_user.jsp" %>  
    <table border="1">
  	<thead>
  		<tr>
  			<th>ID</th><th>标题</th><th>描述</th><th>查看</th>
  		</tr>
  	</thead>
  	<tbody>
  	<c:forEach var="vote" items="${votelist}">
	  	<tr>
  			<td>${ vote.id }</td>
  			<td><a href="servlet/UserVoteControl?action=detail&id=${ vote.id }">${ vote.title }</a></td>
  			<td>${ vote.description }</td>
  			<td>
  				<a class='btn btn-info' href='servlet/UserVoteControl?action=detail&id=${ vote.id }'>查看</a>
  			</td>
  		</tr>
  	</c:forEach>

  	</tbody>
  </table>
  </body>
</html>
