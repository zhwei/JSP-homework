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

  </head>
  
  <body>
    <h1><c:if test="${ vote == null }">添加</c:if><c:if test="${ vote != null }">修改</c:if>投票</h1>
    <hr>
    <%@include file="include/alert.jsp" %>
    <%@include file="include/check_auth_admin.jsp" %>
    
    <form action="<c:if test="${ vote == null }">servlet/ManageVote?action=create</c:if><c:if test="${ vote != null }">servlet/ManageVote?action=update&id=${ vote.id }</c:if>" method="POST">
    	<p>
    		<label>标题</label>
    		<input name="title" type="text" value="${ vote.title }" />
    	</p>
    	<p><label>描述</label></p>
    	<p>
    		<textarea rows="" cols="100" name="description">${ vote.description }</textarea>
    	</p>
    	<p>
    		<input type="submit" value="提交">&nbsp;
    	</p>
    </form>
  </body>
</html>