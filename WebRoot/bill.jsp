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
    
    <title>提交账单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
  <h1>提交账单</h1>
  <hr/>
  <%@include file="include/alert.jsp" %>
  <%@include file="include/check_auth_user.jsp" %>
    <!-- 通过循环显示购物车中所有图书  -->
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
  	<h3>共${sum}元</h3>
  	<c:if test="${cart!= null}">
  		<a href="servlet/UserListBook">继续购物</a>
  		<a href="servlet/CloseCart?action=submit">结算订单</a>
  	</c:if>
  </ul>
  </body>
</html>
