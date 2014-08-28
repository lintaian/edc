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
	<script src="js/lib/es5-shim.js"></script>
	<script src="js/lib/es5-sham.js"></script>
	  <!--[if lte IE 8]>
	    <script src="js/lib/html5shiv.js"></script>
	    <script src="js/lib/json3.js"></script>
	    <script src="js/lib/ie-fix.js"></script>
	  <![endif]-->
	<style type="text/css">
		body {
			width: 100%;
			height: 100%;
			overflow: hidden;
		}
		.ng-hide {
			display: none;
		}
		.bg {
			position: absolute;
			width: 100%;
			height: 100%;
			left: 0px;
			top: 0px;
			z-index: 0;
		}
		.bg .img {
			width: 100%;
			height: 100%;
		}
		.myLabel {
			font: 20px bold;
			width: 80px!important;
			margin-right: 15px;
		}
		.controls {
			margin-left: 0px!important;
		} 
	</style>
</head>
<body data-ng-controller="Login">
	<div class="bg">
		<img class="img" src="img/bg2.jpg">
	</div>
	<div style="width: 330px;position: absolute;top: 45%;left: 50%;background: rgb(237, 249, 255);">
		<div class="errorMsg" style="text-align: center;position: absolute;top: -50px;">
			<div class="alert alert-error ng-hide" data-ng-show="errorMsg != null">
				<span data-ng-bind="errorMsg"></span>
			</div>
		</div>
		<div class="tabbable"> <!-- Only required for left/right tabs -->
		  <ul class="nav nav-tabs">
		    <li data-ng-class="{active: user.role=='teacher'}">
		    	<a href="#tab1" data-toggle="tab" data-ng-click="setRole('teacher')">教师</a>
		    </li>
		    <li data-ng-class="{active: user.role=='admin'}">
		    	<a href="#tab2" data-toggle="tab" data-ng-click="setRole('admin')">运维</a>
		    </li>
		  </ul>
		</div>
		<form class="form-horizontal" id="loginForm" data-ng-submit="login()">
			<div class="control-group info">
				<label class="control-label myLabel" for="username">用户名:</label>
				<div class="controls">
					<input type="text" id="username" data-ng-model="user.username" placeholder="用户名"/>
				</div>
			</div>
			<div class="control-group info">
				<label class="control-label myLabel" for="password">密&nbsp;&nbsp;码:</label>
				<div class="controls">
					<input type="password" id="password" data-ng-model="user.password" placeholder="密码"/>
				</div>
			</div>
			<div class="control-group">
				<div class="controls" style="text-align: center;">
					<input class="btn btn-info" value="登陆" type="submit" style="width: 90%;"/>
				</div>
			</div>
		</form>				
	</div>
	<script data-main="js/ng/login" src="js/lib/require.js"></script>
</body>
</html>