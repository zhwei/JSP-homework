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
  <a href="user.jsp">我的首页</a>
  <hr/>
    <%@include file="include/alert.jsp" %>
  <table border="1">
  	<thead>
  		<tr>
  			<th>ID</th><th>书名</th><th>作者</th><th>价格</th><th>加入购物车</th>
  		</tr>
  	</thead>
  	<tbody>
  	<!-- 循环打印数据库中所有的图书 -->
  	<c:forEach var="book" items="${booklist}">
	  	<tr>
  			<td>${ book.id }</td>
  			<td>${ book.name }</td>
  			<td>${ book.author }</td>
  			<td>${ book.price } 元</td>
  			<td>
  				<a class='btn btn-info' href='servlet/AddToCart?id=${ book.id }'>加入购物车</a>
  			</td>
  		</tr>
  	</c:forEach>

  	</tbody>
  </table>
  
  <hr>
  <c:if test="${cart!=null}">
  	<h2>购物车</h2>
 	<a href="servlet/CloseCart">提交订单</a>
  </c:if>
  <ul>
  <!-- 循环打印购物车中所有图书 -->
  <c:forEach var="book" items="${cart}">
	 <li>			
  			${ book.name }
  			${ book.author }
  			${ book.price } 元
  			${ book.count } 本
  			<a href="servlet/ChangeCount?id=${ book.id }&action=incr">增加</a>
  			<a href="servlet/ChangeCount?id=${ book.id }&action=decr">减少</a>
  			<a href="servlet/RemoveFromCart?id=${ book.id }">从购物车删除</a>
     </li>
  	</c:forEach>
  	
  </ul>
  
  </body>
</html>
