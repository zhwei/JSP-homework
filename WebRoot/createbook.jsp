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
    
    <title><c:if test="${ book == null }">添加</c:if><c:if test="${ book != null }">修改</c:if>图书</title>
    
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
    <h1><c:if test="${ book == null }">添加</c:if><c:if test="${ book != null }">修改</c:if>图书</h1>
    <hr>
    <%@include file="include/alert.jsp" %>
    <%@include file="include/check_auth_admin.jsp" %>
    <form action="<c:if test="${ book == null }">servlet/CreateBook</c:if><c:if test="${ book != null }">servlet/UpdateBook?id=${ book.id }</c:if>" method="POST">
    	<p>
    		<label>图书名称</label>
    		<input name="name" type="text" value="${ book.name }" />
    	</p>
    	<p>
    		<label>图书作者</label>
    		<input name="author" type="text"  value="${ book.author }"/>
    	</p>
    	<p>
    		<label>图书价格</label>
    		<input name="price" type="text"  value="${ book.price }"/>
    	</p>
    	<p>
    		<input type="submit" value="提交">
    	</p>
    </form>
  </body>
</html>
