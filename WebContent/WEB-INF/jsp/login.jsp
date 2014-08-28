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
	<title>乐培生师生平台考试数据检查与统计</title>
	<script src="js/lib/es5-shim.js"></script>
	<script src="js/lib/es5-sham.js"></script>
	  <!--[if lte IE 8]>
	    <script src="js/lib/html5shiv.js"></script>
	    <script src="js/lib/json3.js"></script>
	    <script src="js/lib/ie-fix.js"></script>
	  <![endif]-->
	<style type="text/css">
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
			background-color: #016aa9;
			overflow:hidden;
		}
		.STYLE1 {
			color: #000000;
			font-size: 12px;
		}
	</style>
</head>

<body data-ng-controller="Login">
	<form data-ng-submit="login()">
		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<table width="962" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td height="235" background="img/login_03.gif">&nbsp;</td>
						</tr>
						<tr>
							<td height="53" style="position: relative;">
								<span data-ng-bind="errorMsg"
									style="position: absolute;top: -15px;text-align: center;width: 100%;font-size: 12px;color: red;"></span>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="394" height="78" background="img/login_05.gif">&nbsp;</td>
										<td width="206" background="img/login_06.gif">
											<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="16%" height="25">
														<div align="right">
															<span class="STYLE1">用户</span>
														</div></td>
													<td width="57%" height="25">
														<div align="center">
															<input type="text" data-ng-model="user.username" 
																style="width: 105px; height: 17px; background-color: #292929; border: solid 1px #7dbad7; font-size: 12px; color: #6cd0ff">
														</div>
													</td>
													<td width="27%" height="25">&nbsp;</td>
												</tr>
												<tr>
													<td height="25">
														<div align="right">
															<span class="STYLE1">密码</span>
														</div></td>
													<td height="25">
														<div align="center">
															<input type="password" data-ng-model="user.password"
																style="width: 105px; height: 17px; background-color: #292929; border: solid 1px #7dbad7; font-size: 12px; color: #6cd0ff">
														</div>
													</td>
													<td height="25">&nbsp;</td>
												</tr>
												<tr>
													<td height="25">
														<div align="right">
															<span class="STYLE1">角色</span>
														</div></td>
													<td height="25">
														<div align="center" style="position: relative;">
															<input type="radio" name="role" value="teacher" data-ng-model="user.role" data-ng-checked="user.role=='teacher'"
																style="position: absolute;top: -7px;left: 5%;">
															<span class="STYLE1" style="position: absolute;top: -5px;left: 25%;">教师</span>
															<input type="radio" name="role" value="admin" data-ng-model="user.role" data-ng-checked="user.role=='admin'"
																style="position: absolute;top: -7px;left: 50%;">
															<span class="STYLE1" style="position: absolute;top: -5px;left: 70%;">运维</span>
															<!-- <input type="password" style="width: 105px; height: 17px; background-color: #292929; border: solid 1px #7dbad7; font-size: 12px; color: #6cd0ff"> -->
														</div>
													</td>
													<td height="25">
														<div align="left">
															<a href="javascript:void(0)" data-ng-click="login()">
																<img src="img/dl.gif" width="49" height="18" border="0">
															</a>
														</div>
													</td>
												</tr>
											</table>
										</td>
										<td width="362" background="img/login_07.gif">&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="213" background="img/login_08.gif">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<input type="submit" value="" style="visibility: hidden;" >
	</form>
	<script data-main="js/ng/login" src="js/lib/require.js"></script>
</body>
</html>