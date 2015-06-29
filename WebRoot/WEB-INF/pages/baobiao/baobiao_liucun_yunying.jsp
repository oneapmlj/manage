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
				<h3 class="tabs_involved">${name }</h3>
			</div>
			<div style="width:900px;float:left;">
				<div style="float:left;width:45px;"  class="liucun view biankuang_blue_ding menu_button_2">留存</div>
				<div style="float:left;width:45px;"  class="zhuanhua view biankuang_gray menu_button_2">转化</div>
				<input class="hidden" type="text" value="0" id="baobiao_type"/>
				<input class="hidden" type="text" value="0" id="baobiao_hang"/>
				<input class="hidden" type="text" value="0" id="baobiao_percent"/>
				<input class="hidden" type="text" value="zongxiang" id="baobiao_fangxiang"/>
				<div style="float:left;width:45px;"  class="quse view biankuang_gray menu_button_2">去色</div>
				<div style="float:left;margin-left:5px;margin-top:7px;">
					<select class="percent_value" style="width:60px;float:left;">
						<option value="10">10%</option>
						<option value="20">20%</option>
						<option value="30">30%</option>
						<option value="40">40%</option>
						<option value="50">50%</option>
						<option value="60">60%</option>
						<option value="70">70%</option>
						<option value="80">80%</option>
						<option value="90">90%</option>
					</select>
					<input type="button" value="百分比上" class="hand percent_up" style="margin-top:-1px;margin-left:3px;width:80px;float:left;"/>
					<input type="button" value="百分比下" class="hand percent_down" style="margin-top:-1px;margin-left:3px;width:80px;float:left;"/>
				</div>
				<div style="float:left;margin-left:5px;margin-top:7px;">
					<select class="agent_value" style="width:80px;float:left;">
						<option value="0">全部</option>
						<option value="1">java</option>
						<option value="2">php</option>
						<option value="3">nodejs</option>
						<option value="4">python</option>
						<option value="5">dotnet</option>
						<option value="6">ruby</option>
						<option value="7">android</option>
						<option value="8">ios</option>
					</select>
				</div>
				<div style="float:left;" id="baobiao_sign_type"></div>
			</div>
		</div> 
		<div style="width:100%;">
			<div style="width:100%;min-width:1300px;" id="content">
				

			</div>
		</div>
	
		</div>
	</section>

	<script src="${applicationScope.staticPath}skin/js/hideshow.js" type="text/javascript"></script>
	<script src="${applicationScope.staticPath}skin/js/jquery.tablesorter.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/jquery.equalHeight.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/index.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/view.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/liucun.js"></script>
	<script type="text/javascript">
		download();
	</script>
</body>
</html>