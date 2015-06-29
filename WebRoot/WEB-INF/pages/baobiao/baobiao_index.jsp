<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>OneApm</title>

<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/layout.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/index.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/view.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/public.css" type="text/css" media="screen" />
<script src="${applicationScope.staticPath}skin/js/jquery.min.js"></script>
</head>
<body>
	<s:include value="/WEB-INF/pages/public/header.jsp" />
	<section id="main" class="column"> 
		<div class="public_main width_3_quarter_customer">
		<div  class="header">
			<div  style="float:left">
				<h3 class="tabs_involved">统计报表</h3>
			</div>
			<div style="width:700px;float:left;">
				<s:if test="%{type <= 1}"><div style="float:left;width:45px;" id="type=1" class="view biankuang_blue_ding menu_button_2">每天</div></s:if>
				<s:else><div style="float:left;width:45px;" id="type=1" class="view biankuang_gray menu_button_2">每天</div></s:else>
				<s:if test="%{type == 2}"><div style="float:left;width:45px;" id="type=2" class="view biankuang_blue_ding menu_button_2">每周</div></s:if>
				<s:else><div style="float:left;width:45px;" id="type=2" class="view biankuang_gray menu_button_2">每周</div></s:else>
				<s:if test="%{type == 3}"><div style="float:left;width:45px;" id="type=3" class="view biankuang_blue_ding menu_button_2">每月</div></s:if>
				<s:else><div style="float:left;width:45px;" id="type=3" class="view biankuang_gray menu_button_2">每月</div></s:else>
			</div>
		</div> 
		<div style="width:100%;">
			<div style="width:100%;">
				<div style="width:100%;height:30px;">
					<div style="margin-left:30px;">
						<s:if test="%{show <= 1}"><div style="float:left;width:45px;" id="show=1" class="lie biankuang_blue_ding menu_button_2">用户</div></s:if>
						<s:else><div style="float:left;width:45px;" id="show=1" class="lie biankuang_gray menu_button_2">用户</div></s:else>
						<s:if test="%{show == 2}"><div style="float:left;width:45px;" id="show=2" class="lie biankuang_blue_ding menu_button_2">下载</div></s:if>
						<s:else><div style="float:left;width:45px;" id="show=2" class="lie biankuang_gray menu_button_2">下载</div></s:else>
						<s:if test="%{show == 3}"><div style="float:left;width:45px;" id="show=3" class="lie biankuang_blue_ding menu_button_2">应用</div></s:if>
						<s:else><div style="float:left;width:45px;" id="show=3" class="lie biankuang_gray menu_button_2">应用</div></s:else>
						<s:if test="%{show == 4}"><div style="float:left;width:45px;" id="show=4" class="lie biankuang_blue_ding menu_button_2">数据</div></s:if>
						<s:else><div style="float:left;width:45px;" id="show=4" class="lie biankuang_gray menu_button_2">数据</div></s:else>
					</div>
				</div>
			</div>
		</div>
		</div>
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
		<script type="text/javascript" src="${applicationScope.staticPath}skin/js/view.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".view").live('click', function(){
				var type = $(this).attr("id");
				window.location.href="baobiao_index.action?"+type;
			})
		});
	</script>
</body>
</html>