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
  <!-- 订单管理页面，包括显示购物车汇总信息和单条购买记录信息 -->
    <h1>购买记录管理</h1>
     <a href="servlet/Logout">注销</a>
    <hr>
    <%@include file="include/alert.jsp" %>
    <%@include file="include/check_auth_admin.jsp" %>
    <c:if test="${recordlist!=null}">
    <table border="1">
  	<thead>
  		<tr>
  			<th>编号</th><th>用户</th><th>时间</th><th>查看</th>
  		</tr>
  	</thead>
  	<tbody>
  	<!-- 循环打印所有购买记录
  	 -->
  	<c:forEach var="record" items="${recordlist}">
	  	<tr>
  			<td><a></a>${ record.id }</td>
  			<td>${ record.uid }</td>
  			<td>${ record.date }</td>
  			<td><a href="servlet/RecordManage?action=bills&id=${ record.id }">查看</a></td>
  		</tr>
  	</c:forEach>
  	</tbody>
  </table>
  </c:if>
  <c:if test="${billlist!=null}">
  	<h2>详情</h2>
  	<hr />
	  <table border="1"> 
	  	<thead>
	  		<tr>
	  			<th>编号</th><th>图书编号</th><th>数目</th>
	  		</tr>
	  	</thead>
	  	<tbody>
	  	<!-- 循环打印某条购买记录的所有条目 -->
	  	<c:forEach var="bill" items="${billlist}">
		  	<tr>
	  			<td>${ bill.id }</td>
	  			<td>${ bill.bid } <a href="servlet/UpdateBook?id=${ bill.bid }">查看</a></td>
	  			<td>${ bill.count } 本</td>
	  		</tr>
	  	</c:forEach>
	  	</tbody>
	  </table>
  </c:if>
  </body>
</html>
