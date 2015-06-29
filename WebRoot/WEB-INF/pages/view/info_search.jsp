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
	<script src="${applicationScope.staticPath}skin/js/timeAjax.js"></script>
</head>
<body>
	<s:include value="/WEB-INF/pages/public/header.jsp" />
	<section id="main" class="column"> 
		<div class="public_main width_3_quarter_customer">
		<div class="header">
			<h3 class="tabs_involved">搜索用户</h3>
		</div> 
			<div style="width:1200px;margin-left:auto;margin-right:auto;">
				<div style="width:1200px;float:left;">
					<div style="float:left;margin-top:20px;width:1200px;">
						<div style="hieght:30px;margin:5px 0 0 0;width:220px;float:left;">公司：<input style="width:170px;" class="hand" type="text" id="search_company"/></div>
						<div style="hieght:30px;margin:5px 0 0 0;width:220px;float:left;">姓名：<input style="width:170px;" class="hand" type="text" id="search_name"/></div>
						<div style="hieght:30px;margin:5px 0 0 0;width:220px;float:left;">邮箱：<input style="width:170px;" class="hand" type="text" id="search_email"/></div>
						<div style="hieght:30px;margin:5px 0 0 0;width:220px;float:left;">电话：<input style="width:170px;" class="hand" type="text" id="search_phone"/></div>
						<div style="hieght:30px;margin:5px 0 0 0;width:220px;float:left;">QQ：<input style="width:170px;" class="hand" type="text" id="search_qq"/></div>
						<%-- <div style="hieght:30px;margin:5px 0 0 0;width:315px;float:left; line-height:30px;">
							<div style="width:36px;float:left;height:30px;">标签:</div>
							<div style="width:279px;height:30px;float:left;">
								<select style="width:80px;" id="search_loudou">
									<s:iterator value="LOUDOU">
										<s:if test="%{info.tag.loudou == id}">
											<option  selected = "selected" value="${id}">${name}</option>
										</s:if>
										<s:else>
											<option value="${id}">${name}</option>
										</s:else>
									</s:iterator>
								</select>
								<select style="margin-left:10px;width:80px;" id="search_metric">
									<s:iterator value="METRIC">
										<s:if test="%{info.tag.metric == id}">
											<option  selected = "selected" value="${id}">${name}</option>
										</s:if>
										<s:else>
											<option value="${id}">${name}</option>
										</s:else>
									</s:iterator>
								</select>
								<select style="margin-left:10px;width:80px;" id="search_language">
									<s:iterator value="LANGUAGE">
										<option value="${id}">${name}</option>
									</s:iterator>
								</select>
							</div>
						</div> --%>
						<div style="hieght:30px;margin:5px 0 0 0;width:100px;float:left;line-height:30px;">
							<div style="height:30px;margin-left:30px;width:40px;float:left;">
								<input class="hand search_button" type="button" style="float:left;" value="搜索"/>
							</div>
						</div>
					</div>
				</div>
				<div style="float:left;width:1200px;height:30px;">
					<div class="search_result_msg" style="height:20px;line-hight:20px;font-size:16px;color:red;"></div>
				</div>
				<div id="search_out" style="width:1200px;min-height:400px;float:left;">
						<table class="tablesorter" cellspacing="0"> 
							<thead> 
								<tr> 
									<th style="width:7%;"></th>
						  			<th style="width:6%;">ID</th> 
						  			<th style="width:10%;">姓名</th>
						  			<th style="width:16%;">公司</th>
						  			<th style="width:18%;">项目</th>
						  			<th style="width:7%">Coming</th>
						  			<th style="width:6%;">销售</th>
						  			<th style="width:6%;">Support</th>
						  			<th style="width:6%;">PreSale</th>
						  			<th style="width:10%">Edit</th>
						  			<th style="width:8%"></th>
								</tr> 
							</thead> 
							<tbody id="search_result"> 
								<s:if test="%{infoSize>0}">
									<s:iterator value="infos">
										<s:if test="%{tag.metric == 1}">
											<tr style="color:red;">
											<td>${userId}</td> 
											<td>${name}</td> 
											<td>${email}</td> 
											<td>${company}</td>
											<td>
												<s:if test="%{info.comming == null}">未知</s:if>
												<s:else>${info.from }</s:else>
											</td>
											<td>${phone}</td>
											<td>${createTime}</td>
											<td>${loginTime}</td>
										</tr> 
										</s:if>
										<s:else>
											<tr>
											<td>${userId}</td> 
											<td>${name}</td> 
											<td>${email}</td> 
											<td>${company}</td>
											<td>
												<s:if test="%{info.comming == null}">未知</s:if>
												<s:else>${info.from }</s:else>
											</td>
											<td>${phone}</td>
											<td>${createTime}</td>
											<td>${loginTime}</td>
										</tr> 
										</s:else>
									</s:iterator>
									</s:if>
							</tbody> 
						</table>
				</div>
			</div>
	</div>
	<div class="clear"></div>
	<div class="spacer"></div>
	<div class="hidden totalPage">${totalPage}</div>
	<div class="hidden nowPage">${nowPage}</div>
	<div class="hidden nowId">${id}</div>
	</section>
	<script src="${applicationScope.staticPath}skin/js/hideshow.js" type="text/javascript"></script>
	<script src="${applicationScope.staticPath}skin/js/jquery.tablesorter.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/jquery.equalHeight.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/index.js"></script>
	<script type="text/javascript">
	$(function(){
		document.onkeydown = function(e){
		    var ev = document.all ? window.event : e;
		    if(ev.keyCode==13) {
			$(".search_button").click();
		     }
		}
		});
	</script>
</body>
</html>