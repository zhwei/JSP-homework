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
    
    <title>添加选项</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
    <h1>添加选项</h1>
    <hr>
    <%@include file="include/alert.jsp" %>
    <%@include file="include/check_auth_admin.jsp" %>
    
    <form action="servlet/ManageChoice?action=create&vote_id=${ vote.id }" method="POST">
    	<p><label>描述</label></p>
    	<p>
    		<textarea rows="" cols="100" name="description"></textarea>
    	</p>
    	<p>
    		<input type="submit" value="提交">&nbsp;
    	</p>
    </form>
  </body>
</html>