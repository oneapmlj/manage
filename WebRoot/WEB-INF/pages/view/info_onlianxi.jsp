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
		<div class="header">
			<h3 class="tabs_involved">正在联系</h3>
		</div> 
		<div style="width:100%;">
			<div style="width:100%;">
				<table class="tablesorter" cellspacing="0">
					<thead> 
						<tr> 
				  			<th style="width:300px;">公司</th> 
				  			<th style="width:200px;">开始时间</th>
				  			<th style="width:80px;">持续时间(分钟)</th>
						</tr> 
					</thead> 
					<tbody class="tbody"> 
						<s:if test="%{userGroupsList != null && userGroupsList.size >0}">
							<s:iterator value="userGroupsList">
								<tr style="font-size:16px;"> 
						 			<td><a href="user_group_view.action?id=${groupId }" target="_blank">${groupName}</a></td>
						 			<td>${zhengzailianxi.startTime}</td>
						 			<td>${zhengzailianxi.stay}</td>
								</tr> 
							</s:iterator>
						</s:if>
					</tbody> 
				</table>
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
</body>
</html>