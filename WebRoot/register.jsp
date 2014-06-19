<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Register</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="bootstrap.min.css">

  </head>
  
  <body>
  <div class="container">
  	<div class="col-md-4" style="float: none; margin: 0 auto;">
	<!-- 注册表单
	 -->
    <form action="servlet/Register" method="post" class="form">
    	<h1>Register</h1>
    	<hr/>
    	<%@include file="include/alert.jsp" %>
    	<p><label>用户名：</label><input name="username" type="text" class="form-control"></p>
    	<p><label>密码：</label><input name="password1" type="password" class="form-control"></p>
    	<p><label>重复密码：</label><input name="password2" type="password" class="form-control"></p>
    	<p><label>性别：</label><input name="sex" type="text" class="form-control"></p>
    	<p><input value="注册" type="submit" class="btn btn-primary btn-block">
    	</p>
    </form>
      	</div>
  </div>
    
  </body>
</html>
