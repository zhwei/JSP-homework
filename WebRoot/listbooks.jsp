<%@ page language="java" import="java.util.*,beans.Books" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>显示全部图书</title>
    
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
  <h1>全部图书</h1>
  <a href="panel.jsp">后台首页</a>
  <a href="createbook.jsp">添加图书</a>
  <hr/>
    <%@include file="include/alert.jsp" %>
    <%@include file="include/check_auth_admin.jsp" %>  
  <table border="1">
  	<thead>
  		<tr>
  			<th>ID</th><th>书名</th><th>作者</th><th>价格</th><th>操作</th>
  		</tr>
  	</thead>
  	<tbody>
  	<!-- 循环打印所有图书，手机用jstl -->
  	<c:forEach var="book" items="${booklist}">
	  	<tr>
  			<td>${ book.id }</td>
  			<td>${ book.name }</td>
  			<td>${ book.author }</td>
  			<td>${ book.price } 元</td>
  			<td>
  				<a class='btn btn-info' href='servlet/UpdateBook?id=${ book.id }'>修改</a>
  				<a class='btn btn-danger' href='servlet/DeleteBook?id=${ book.id }'>删除</a>
  			</td>
  		</tr>
  	</c:forEach>

  	</tbody>
  </table>
  </body>
</html>
