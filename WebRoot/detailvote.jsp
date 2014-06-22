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
    
    <title>查看投票</title>
    
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
    <h2>${ vote.title }</h2>
    <p><strong>${ vote.description }</strong></p>
    <h2>选项</h2>
    <p><a href="servlet/ManageChoice?action=create&vote_id=${ vote.id }">添加选项</a></p><br/>
  	<c:forEach var="choice" items="${choicelist}">
  		<li>${ choice.description } (${ choice.count })
  				<a class='btn btn-danger' href='servlet/ManageChoice?action=delete&vote_id=${ vote.id }&id=${ choice.id }'>删除</a>
  				</li>
  	</c:forEach>
  </body>
</html>
