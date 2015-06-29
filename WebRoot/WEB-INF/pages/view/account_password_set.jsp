<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>OneApm</title>

<link rel="stylesheet"
	href="${applicationScope.staticPath}skin/css/layout.css"
	type="text/css" media="screen" />
<link rel="stylesheet"
	href="${applicationScope.staticPath}skin/css/index.css" type="text/css"
	media="screen" />
<!--[if lt IE 9]>
	<link rel="stylesheet" href="css/ie.css" type="text/css" media="screen" />
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
<script src="${applicationScope.staticPath}skin/js/jquery.min.js"></script>
<script src="${applicationScope.staticPath}skin/js/timeAjax.js"></script>
</head>
<body>
	<s:include value="/WEB-INF/pages/public/header.jsp" />
	<section id="main" class="column"> <article
		class="module width_3_quarter_customer"> <header>
	<h3 class="tabs_involved">重置密码</h3>
	</header> 
			<div style="width:100%;">
				<div style="width:400px;height:300px;margin-top:200px;margin-left:auto;margin-right:auto;">
					<div style="width:400px;font-size:14px;line-height:20px;">
						<input class="hidden" id="id" value="${id }"/>
						<input class="hidden" id="token" value="${token }"/>
						<div style="width:400px;float:left;">请输入您的新密码：</div>
						<div style="width:200px;float:left;"><input type="text" id="password"/></div>
						<input style="float:left;margin-left:50px;" class="hand" type="button" id="account_password" value="提交"/>
					</div>
				</div>
			</div>
	</article>
	<div class="clear"></div>
	<div class="spacer"></div>
	<div class="hidden totalPage">${totalPage}</div>
	<div class="hidden nowPage">${nowPage}</div>
	<div class="hidden nowId">${id}</div>
	</section>

	<script src="${applicationScope.staticPath}skin/js/hideshow.js"
		type="text/javascript"></script>
	<script
		src="${applicationScope.staticPath}skin/js/jquery.tablesorter.min.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="${applicationScope.staticPath}skin/js/jquery.equalHeight.js"></script>
	<script type="text/javascript"
		src="${applicationScope.staticPath}skin/js/index.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#account_password").live('click', function(){
				var password = $("#password").val();
				var id = $("#id").val();
				var token = $("#token").val();
				$.ajax({
					dataType:'json',
					url:'account_password_update.action',
					data:{password:password,token:token,id:id}
				}).done(function(da){
					if(da.status == 1){
						window.location.href="http://manage.oneapm.com";
					}else{
						alert(da.msg);
					}
				});
			})
		});
	</script>
</body>
</html>