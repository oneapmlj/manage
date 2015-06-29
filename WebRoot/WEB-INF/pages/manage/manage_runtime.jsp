<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>OneApm</title>

<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/layout.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/index.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/public.css" type="text/css" media="screen" />
<script src="${applicationScope.staticPath}skin/js/jquery.min.js"></script>
<script src="${applicationScope.staticPath}skin/js/timeAjax.js"></script>
</head>
<body>
	<s:include value="/WEB-INF/pages/public/header.jsp" />
	<section id="main" class="column"> 
	<div class="public_main width_3_quarter_customer">
		<div class="header">
		<h3 class="tabs_involved">运行记录</h3>
		</div> 
			<div style="width:100%;min-height:500px;">
				<div style="width:400px;float:left;">
					<div>基本信息读取</div>
					<s:iterator value="init">
						<div>${time}</div>
					</s:iterator>
				</div>
				<div style="width:400px;float:left;">
					<div>信息读取</div>
					<s:iterator value="timetask">
						<div>${time}</div>
					</s:iterator>
				</div>
			</div>
	</div>
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
</body>
</html>