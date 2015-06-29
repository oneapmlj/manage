<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<s:include value="/WEB-INF/pages/public/manage_header.jsp"/>
	<section id="main" class="column">
			<div class="hang"><div class="page"></div></div>
		<div class="public_main width_3_quarter_customer">
			<div class="header">
				<div style="float:left">
					<h3 class="tabs_involved">
						<s:iterator value="menu">
							<s:if test="%{id == menuId}">${name}&nbsp;&nbsp;本页${report.length}条&nbsp;&nbsp;总共${report.total}条</s:if>
						</s:iterator>
					</h3>
				</div>
				<input type="text" id="index_id" class="hidden" value="${id}"/>
				<input type="text" id="index_language" class="hidden" value="${language}"/>
				<s:if test="%{id == 1 || id == 12}">
					<div style="width:700px;float:left;">
						<s:if test="%{language <= 0}"><div style="float:left;width:45px;" id="language=1" class="index_language biankuang_blue_ding menu_button_2">全部</div></s:if>
						<s:else><div style="float:left;width:45px;" id="language=0" class="index_language biankuang_gray menu_button_2">全部</div></s:else>
						<s:iterator value="LANGUAGE">
							<s:if test="%{id > 0}">
								<s:if test="%{language == id}"><div style="float:left;width:50px;" id="language=1" class="index_language biankuang_blue_ding menu_button_2">${name }</div></s:if>
								<s:else><div style="float:left;width:50px;" id="language=${id }" class="index_language biankuang_gray menu_button_2">${name }</div></s:else>
							</s:if>
						</s:iterator>
					</div>
				</s:if>
			</div>
				<s:include value="/WEB-INF/pages/index/index_1.jsp"/>
				<%-- <s:if test="%{id == 1}"><s:include value="/WEB-INF/pages/index/index_1.jsp"/></s:if>
				<s:if test="%{id == 2}"><s:include value="/WEB-INF/pages/index/index_2.jsp"/></s:if>
				<s:if test="%{id == 3}"><s:include value="/WEB-INF/pages/index/index_3.jsp"/></s:if>
				<s:if test="%{id == 4}"><s:include value="/WEB-INF/pages/index/index_4.jsp"/></s:if>
				<s:if test="%{id == 5}"><s:include value="/WEB-INF/pages/index/index_5.jsp"/></s:if>
				<s:if test="%{id == 6}"><s:include value="/WEB-INF/pages/index/index_6.jsp"/></s:if>
				<s:if test="%{id == 7}"><s:include value="/WEB-INF/pages/index/index_7.jsp"/></s:if>
				<s:if test="%{id == 8}"><s:include value="/WEB-INF/pages/index/index_8.jsp"/></s:if>
				<s:if test="%{id == 9}"><s:include value="/WEB-INF/pages/index/index_9.jsp"/></s:if>
				<s:if test="%{id == 10}"><s:include value="/WEB-INF/pages/index/index_10.jsp"/></s:if>
				<s:if test="%{id == 11}"><s:include value="/WEB-INF/pages/index/index_11.jsp"/></s:if>
				<s:if test="%{id == 12}"><s:include value="/WEB-INF/pages/index/index_12.jsp"/></s:if>
				<s:if test="%{id == 13}"><s:include value="/WEB-INF/pages/index/index_13.jsp"/></s:if>
				<s:if test="%{id == 14}"><s:include value="/WEB-INF/pages/index/index_14.jsp"/></s:if>
				<s:if test="%{id == 15}"><s:include value="/WEB-INF/pages/index/index_15.jsp"/></s:if>
				<s:if test="%{id == 16}"><s:include value="/WEB-INF/pages/index/index_16.jsp"/></s:if> --%>
		</div>
		<%-- <div style="margin-left:50px;height:30px;float:left;width:95%;line-height:30px;">
			全选<input type="checkbox" class="checkAll" name="checkAll"/>
			<s:if test="%{MAIL_MODE != null}">
				&nbsp;邮件模板：
				<select  class="mode" style="cursor: pointer;">
					<s:iterator value="MAIL_MODE">
						<option value="${id}">${name}</option>
					</s:iterator>
				</select>
			</s:if>
			&nbsp;间隔天数<select class="date" style="cursor: pointer;">
				<option value="0">全部</option>
				<option value="1">1天</option>
				<option value="2">2天</option>
				<option value="3">3天</option>
				<option value="7">1周</option>
				<option value="14">2周</option>
				<option value="30">1月</option>
				<option value="60">2月</option>
			</select>
			<input type="button" style="cursor: pointer;" id="click_sendMail" value="发送邮件"/>
		</div> --%>
		<div class="hang"><div class="page"></div></div>
		<div class="clear"></div>
		<div class="spacer"></div>
		<div class="hidden totalPage">${page.totalPage}</div>
		<div class="hidden nowPage">${page.nowPage}</div>
		<div class="hidden nowId">${id}</div>
		<div class="footer_content" style="width:100%;height:140px;">
			<div style="background:#B8B8B8;padding:30px;font-size:16px;line-height:25px;text-align: center">
				OneAPM
			</div>
		</div>
	</section>
	
	<script src="${applicationScope.staticPath}skin/js/hideshow.js" type="text/javascript"></script>
	<script src="${applicationScope.staticPath}skin/js/jquery.tablesorter.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/jquery.equalHeight.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/view.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/index.js"></script>
</body>
</html>