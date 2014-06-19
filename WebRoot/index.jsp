<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>网上书城-网站首页</title>
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
		<h1>
			网上书城
		</h1>
		<ul>
			<li><a href="login.jsp">登录</a></li>
			<li><a href="register.jsp">注册</a></li>
			
		</ul>
		<h2>
			功能描述
		</h2>
		<h3>将数据库`db.sqlite`放到d盘根目录，网站程序中定义的路径为：<strong>d:/db.sqlite</strong></h3>
		<ul>
			<li>
				<p>
					用户
				</p>
				<ul>
				
					<li>测试管理员：admin，测试普通用户：123，密码均为：123</li>
					<li>
						角色分为普通用户和管理员用户
					</li>
					<li>
						普通用户可以购买图书
					</li>
					<li>
						管理员可以添加图书、修改图书定价、删除图书
					</li>
				</ul>
			</li>
			<li>
				<p>
					购物车
				</p>
				<ul>
					<li>
						添加图书到购物车
					</li>
					<li>
						修改数目
					</li>
					<li>
						从购物车删除
					</li>
					<li>
						重复购买
					</li>
				</ul>
			</li>
			<li>
				<p>
					订单
				</p>
				<ul>
					<li>
						普通用户可以创建订单
					</li>
					<li>
						订单和购买条目一对多关系
					</li>
					<li>
						统计购物车中总金额
					</li>
					<li>
						管理员可以查看所有订单
					</li>
				</ul>
			</li>
		</ul>
	</body>
</html>
