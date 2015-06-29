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
	<h3 class="tabs_involved">激活帐号</h3>
	</header> 
			<div style="width:100%;min-height:500px;">
				<div style="padding:30px;margin-left:40px;">
					<div  class="hidden"  style="width:200px;height:20px;font-size:14px;line-height:20px;">
						<div style="width:60px;float:left;">ID:</div>
						<div style="width:140px;float:left;"><input value="${id }" type="text" id="account_id"/></div>
					</div>
					<div class="hidden" style="width:200px;height:20px;font-size:14px;line-height:20px;">
						<div style="width:60px;float:left;">token:</div>
						<div style="width:140px;float:left;"><input value="${token }" type="text" id="account_token"/></div>
					</div>
					<div style="width:200px;height:20px;font-size:14px;line-height:20px;">
						<div style="color:red;width:240px;float:left;">帐号为英文字母，请不要用中文</div>
						<div style="width:60px;float:left;">帐号:</div>
						<div style="width:140px;float:left;"><input type="text" id="account_username"/></div>
					</div>
					<div style="width:200px;height:20px;font-size:14px;line-height:20px;">
						<div style="width:60px;float:left;">电话:</div>
						<div style="width:140px;float:left;"><input type="text" id="account_phone"/></div>
					</div>
					<div style="width:200px;height:20px;font-size:14px;line-height:20px;">
						<div style="color:red;width:240px;float:left;">职位用于生成名片，请认真填写</div>
						<div style="width:60px;float:left;">职位:</div>
						<div style="width:140px;float:left;"><input type="text" id="account_position"/></div>
					</div>
					<div style="width:200px;height:40px;font-size:14px;line-height:20px;">
						<div style="color:red;width:240px;float:left;">昵称用于生成名片，请认真填写</div>
						<div style="width:60px;float:left;">昵称:</div>
						<div style="width:140px;float:left;"><input type="text" id="account_nick"/></div>
					</div>
					<div style="width:200px;height:20px;font-size:14px;line-height:20px;">
						<div style="width:60px;float:left;">密码:</div>
						<div style="width:140px;float:left;"><input type="text" id="account_password"/></div>
					</div>
					<div style="width:200px;height:20px;font-size:14px;line-height:20px;">
						<input class="hand" type="button" id="sign_account" value="提交"/>
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
			$("#sign_account").live('click', function(){
				var id = $("#account_id").val();
				var token = $("#account_token").val();
				var phone = $("#account_phone").val();
				var position = $("#account_position").val();
				var nick = $("#account_nick").val();
				var username = $("#account_username").val();
				var password = $("#account_password").val();
				if(phone == null || phone.length <= 0){
					alert("电话不能为空！");
					return;
				}
				if(position == null || position.length <= 0){
					alert("职位不能为空！");
					return;
				}
				if(password == null || password.length <= 0){
					alert("密码不能为空！");
					return;
				}
				if(username == null){
					alert("帐号不能为空");
					return;
				}
				$.ajax({
					dataType:'json',
					url:'account_update.action',
					data:{username:username,id:id,token:token,phone:phone,position:position,nickName:nick,password:password}
				}).done(function(da){
					if(da.status == 1){
						alert("激活成功");
						 window.location.href="http://manage.oneapm.com"
					}else{
						alert(da.msg);
					}
				});
			})
		});
	</script>
</body>
</html>