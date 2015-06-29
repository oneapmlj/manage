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
</head>
<body>
	<s:include value="/WEB-INF/pages/public/header.jsp" />
	<section id="main" class="column"> <article
		class="module width_3_quarter_customer"> <header>
	<h3 class="tabs_involved">添加邮件模板</h3>
	</header> 
			<div style="width:800px;">
				<div style="width:260px;padding:50px;">
					<div style="width:240px;float:left;">
						<div style="font-size:14px;width:80px;float:left;">标题：</div><input id="title" style="float:left;width:150px;"/>
					</div>
					<div style="width:240px;float:left;margin-top:5px;">
						<div style="font-size:14px;width:80px;float:left;">描述：</div><input id ="desc" style="float:left;width:150px;"/>
					</div>
					<div style="width:240px;float:left;margin-top:5px;">
						<div style="font-size:14px;width:80px;float:left;">内容：</div><input id ="content" style="float:left;width:150px;"/>
					</div>
					<div style="width:240px;float:left;margin-top:5px;">
						<div style="font-size:14px;width:80px;float:left;">状态：</div>
						<select id="status" style="float:left;width:150px;">
							<option value="1">未激活</option>
							<option value="2">激活</option>
							<option value="3">限制个人使用</option>
							<option value="4">限制公共使用</option>
							<option value="0">全部可以使用</option>
						</select>
					</div>
					<div style="width:240px;float:left;margin-top:5px;">
						<div style="font-size:14px;width:80px;float:left;">时间来源：</div>
						<select id="timetype" style="float:left;width:150px;">
							<option value="1">注册时间</option>
							<option value="2">登录时间</option>
							<option value="3">下载时间</option>
							<option value="4">添加时间</option>
							<option value="5">有数据时间</option>
						</select>
					</div>
						<div style="margin-top:5px;float:left;margin-left:200px;"><input id="add" type="button" value="提交"/></div>
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
				$("#add").click(function(){
					var title = $("#title").val();
					var desc = $("#desc").val();
					var status = $("#status").val();
					var content = $("#content").val();
					var timetype = $("#timetype").val();
					$.ajax({
						dataType:'json',
						url:'mode_insert.action',
						data:{title:title,description:desc,status:status,content:content,timeType:timetype}
					}).done(function(da){
						if(da.status == 1){
							alert("添加成功!");
						}else{
							alert(da.msg);
						}
					});
				});
		});
	</script>
</body>
</html>