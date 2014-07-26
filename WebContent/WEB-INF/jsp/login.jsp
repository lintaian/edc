<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" id="ng-app">
<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
	<title>登陆</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.min.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body data-ng-controller="Login" style="background-image: url(img/bg1.jpg);">
	<div style="margin: 300px auto; width: 500px;position: relative;">
		<div class="errorMsg" style="text-align: center;position: absolute;top: -50px;left: 180px;">
			<div class="alert alert-error ng-hide" data-ng-show="errorMsg != null">
				<span data-ng-bind="errorMsg"></span>
			</div>
		</div>
		<form class="form-horizontal" id="loginForm" data-ng-submit="login()">
			<div class="control-group info">
				<label class="control-label" for="username">用户名:</label>
				<div class="controls">
					<input type="text" id="username" data-ng-model="user.username" placeholder="用户名"/>
				</div>
			</div>
			<div class="control-group info">
				<label class="control-label" for="password">密&nbsp;&nbsp;码:</label>
				<div class="controls">
					<input type="password" id="password" data-ng-model="user.password" placeholder="密码"/>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<label class="radio inline">
						<input type="radio" data-ng-model="user.role" name="role" value="teacher">&nbsp;教师
					</label>
					<label class="radio inline">
						<input type="radio" data-ng-model="user.role" name="role" value="admin" checked="checked">&nbsp;运维
					</label>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<input class="btn btn-info" value="登陆" type="submit"/>
				</div>
			</div>
		</form>				
	</div>
	<script data-main="js/ng/login" src="js/lib/require.js"></script>
</body>
</html>