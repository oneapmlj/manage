<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>OneApm</title>
	
	<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/layout.css" type="text/css" media="screen" />
	<!--[if lt IE 9]>
	<link rel="stylesheet" href="css/ie.css" type="text/css" media="screen" />
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
	<script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
<style type="text/css">
	.login{width:240px;float:left;height:40px;}
</style>
</head>
<body>
	<div style="margin:200px auto; width:300px; height:200px; border:1px solid #000000;">
		<div style="text-align:center;height:40px;">
			<span style="line-height:40px;font-size:18px;">OneApm后台管理登录</span>
		</div>
		<div style="height:20px;text-align:center;">
			<s:if test="%{#session.login_error == 1}">
				<span style="color:red;line-height:20px;font-size:14px;"><s:property value="#session.backmessage"/></span>
			</s:if>
		</div>
		<div style="margin:10px 0 0 50px;">
			<form action="${applicationScope.basePath}login" method="post">
				<div class="login">帐号:<input type="text" name="username"/></div> 
				<div class="login">密码:<input type="password" name="password"/></div>  
		    	<div style="float:right;width:60px;margin:5px 15px 0 0;"><input type="submit" value="登录"/></div>
		    	<div style="float:right;width:80px;margin:5px 15px 0 0;"><a href="account_password.action">忘记密码</a></div>
		    </form>
		</div>
	</div>
</body>
</html>