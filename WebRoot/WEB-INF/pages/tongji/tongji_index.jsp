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
			<h3 class="tabs_involved">统计信息</h3>
		</div> 
		<div style="width:100%;">
			<div style="width:100%;">
				<table class="tablesorter" cellspacing="0">
					<thead> 
						<tr> 
				  			<th style="width:120px;">日期</th> 
				  			<th style="width:70px;">总数</th>
				  			<th style="width:70px;">注册</th>
				  			<th style="width:65px;">登录</th>
				  			<th style="width:65px;">下载</th>
				  			<th style="width:65px;">应用</th>
				  			<th style="width:65px;">数据</th>
				  			<th style="width:65px;">数应</th>     
				  			
				  			<th style="width:65px;">企业</th>
				  			<th style="width:65px;">开发者</th>
				  			<th style="width:65px;">未定义</th>
				  			<!-- <th style="width:65px;">关闭</th> -->
				  			
				  			<!-- <th style="width:65px;">交流</th>
				  			<th style="width:65px;">测试</th> -->
				  			<!-- <th style="width:65px;">采购</th>
				  			<th style="width:65px;">完成</th>
				  			<th style="width:65px;">成单</th>
				  			<th style="width:65px;">输单</th> -->
				  			<!-- <th style="width:80px;">活跃状态</th>
				  			<th style="width:80px;">中间状态</th>
				  			<th style="width:80px;">休眠状态</th>
				  			<th style="width:80px;">隔离状态</th> -->
						</tr> 
					</thead> 
					<tbody class="tbody"> 
						<s:if test="%{index != null && index.tongjis != null && index.tongjis.size >0}">
							<s:iterator value="index.tongjis">
								<tr style="font-size:16px;" id="${id}"> 
						 			<td>${data_time}</td>
									<s:if test="%{totalUp == 1}">
							 			<td style="color:red;">${total}</td>
									</s:if>
									<s:else>
										<s:if test="%{totalUp == 3}">
											<td style="color:blue;">${total}</td>
										</s:if>
										<s:else>
											<td>${total}</td>
										</s:else>
									</s:else>
									
									<s:if test="%{signUp == 1}">
							 			<td style="color:red;"  class="hand view" id="${id}_2">${sign}</td>
									</s:if>
									<s:else>
										<s:if test="%{signUp == 3}">
											<td style="color:blue;"  class="hand view" id="${id}_2">${sign}</td>
										</s:if>
										<s:else>
											<td  class="hand view" id="${id}_2">${sign}</td>
										</s:else>
									</s:else>
									<s:if test="%{loginUp == 1}">
							 			<td style="color:red;"  class="hand view" id="${id}_3">${login}</td>
									</s:if>
									<s:else>
										<s:if test="%{loginUp == 3}">
											<td style="color:blue;"  class="hand view" id="${id}_3">${login}</td>
										</s:if>
										<s:else>
											<td  class="hand view" id="${id}_3">${login}</td>
										</s:else>
									</s:else>
									<s:if test="%{downloadUp == 1}">
							 			<td style="color:red;"  class="hand view" id="${id}_4">${download}</td>
									</s:if>
									<s:else>
										<s:if test="%{downloadUp == 3}">
											<td style="color:blue;"  class="hand view" id="${id}_4">${download}</td>
										</s:if>
										<s:else>
											<td  class="hand view" id="${id}_4">${download}</td>
										</s:else>
									</s:else>
									<s:if test="%{appUp == 1}">
							 			<td style="color:red;"  class="hand view" id="${id}_5">${app}</td>
									</s:if>
									<s:else>
										<s:if test="%{appUp == 3}">
											<td style="color:blue;"  class="hand view" id="${id}_5">${app}</td>
										</s:if>
										<s:else>
											<td  class="hand view" id="${id}_5">${app}</td>
										</s:else>
									</s:else>
									<s:if test="%{dataUp == 1}">
							 			<td style="color:red;"  class="hand view" id="${id}_6">${data}</td>
									</s:if>
									<s:else>
										<s:if test="%{dataUp == 3}">
											<td style="color:blue;"  class="hand view" id="${id}_6">${data}</td>
										</s:if>
										<s:else>
											<td  class="hand view" id="${id}_6">${data}</td>
										</s:else>
									</s:else>
									<s:if test="%{appDataUp == 1}">
							 			<td style="color:red;"  class="hand" id="${id}_17">${appData}</td>
									</s:if>
									<s:else>
										<s:if test="%{appDataUp == 3}">
											<td style="color:blue;"  class="hand" id="${id}_17">${appData}</td>
										</s:if>
										<s:else>
											<td  class="hand" id="${id}_17">${appData}</td>
										</s:else>
									</s:else>
									<s:if test="%{pointUp == 1}">
							 			<td style="color:red;"  class="hand view" id="${id}_7">${point}</td>
									</s:if>
									<s:else>
										<s:if test="%{pointUp == 3}">
											<td style="color:blue;"  class="hand view" id="${id}_7">${point}</td>
										</s:if>
										<s:else>
											<td  class="hand view" id="${id}_7">${point}</td>
										</s:else>
									</s:else>
									<s:if test="%{commonUp == 1}">
							 			<td style="color:red;"  class="hand view" id="${id}_8">${common}</td>
									</s:if>
									<s:else>
										<s:if test="%{commonUp == 3}">
											<td style="color:blue;"  class="hand view" id="${id}_8">${common}</td>
										</s:if>
										<s:else>
											<td  class="hand view" id="${id}_8">${common}</td>
										</s:else>
									</s:else>
									<s:if test="%{unbinUp == 1}">
							 			<td style="color:red;"  class="hand view" id="${id}_9">${unbin}</td>
									</s:if>
									<s:else>
										<s:if test="%{unbinUp == 3}">
											<td style="color:blue;"  class="hand view" id="${id}_9">${unbin}</td>
										</s:if>
										<s:else>
											<td  class="hand view" id="${id}_9">${unbin}</td>
										</s:else>
									</s:else>
									<%-- <s:if test="%{unuseUp == 1}">
							 			<td style="color:red;"  class="hand view" id="${id}_10">${unuse}</td>
									</s:if>
									<s:else>
										<s:if test="%{unuseUp == 3}">
											<td style="color:blue;"  class="hand view" id="${id}_10">${unuse}</td>
										</s:if>
										<s:else>
											<td  class="hand view" id="${id}_10">${unuse}</td>
										</s:else>
									</s:else>
									<s:if test="%{jiaoliuUp == 1}">
							 			<td style="color:red;"  class="hand view" id="${id}_11">${jiaoliu}</td>
									</s:if>
									<s:else>
										<s:if test="%{jiaoliuUp == 3}">
											<td style="color:blue;"  class="hand view" id="${id}_11">${jiaoliu}</td>
										</s:if>
										<s:else>
											<td  class="hand view" id="${id}_11">${jiaoliu}</td>
										</s:else>
									</s:else>
									<s:if test="%{ceshiUp == 1}">
							 			<td style="color:red;"  class="hand view" id="${id}_12">${ceshi}</td>
									</s:if>
									<s:else>
										<s:if test="%{ceshiUp == 3}">
											<td style="color:blue;"  class="hand view" id="${id}_12">${ceshi}</td>
										</s:if>
										<s:else>
											<td  class="hand view" id="${id}_12">${ceshi}</td>
										</s:else>
									</s:else>
									<s:if test="%{caigouUp == 1}">
							 			<td style="color:red;"  class="hand view" id="${id}_13">${caigou}</td>
									</s:if>
									<s:else>
										<s:if test="%{caigouUp == 3}">
											<td style="color:blue;"  class="hand view" id="${id}_13">${caigou}</td>
										</s:if>
										<s:else>
											<td  class="hand view" id="${id}_13">${caigou}</td>
										</s:else>
									</s:else>
									<s:if test="%{wanchengUp == 1}">
							 			<td style="color:red;"  class="hand view" id="${id}_14">${wancheng}</td>
									</s:if>
									<s:else>
										<s:if test="%{wanchengUp == 3}">
											<td style="color:blue;"  class="hand view" id="${id}_14">${wancheng}</td>
										</s:if>
										<s:else>
											<td  class="hand view" id="${id}_14">${wancheng}</td>
										</s:else>
									</s:else>
									
									<s:if test="%{wancheng_successUp == 1}">
							 			<td style="color:red;"  class="hand view" id="${id}_15">${wancheng_success}</td>
									</s:if>
									<s:else>
										<s:if test="%{wancheng_successUp == 3}">
											<td style="color:blue;"  class="hand view" id="${id}_15">${wancheng_success}</td>
										</s:if>
										<s:else>
											<td  class="hand view" id="${id}_15">${wancheng_success}</td>
										</s:else>
									</s:else>
									
									<s:if test="%{wancheng_failUp == 1}">
							 			<td style="color:red;"  class="hand view" id="${id}_16">${wancheng_fail}</td>
									</s:if>
									<s:else>
										<s:if test="%{wancheng_failUp == 3}">
											<td style="color:blue;"  class="hand view" id="${id}_16">${wancheng_fail}</td>
										</s:if>
										<s:else>
											<td  class="hand view" id="${id}_16">${wancheng_fail}</td>
										</s:else>
									</s:else> --%>
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
	<script type="text/javascript">
		$(document).ready(function(){
			$(".view").live('click', function(){
				var param = $(this).attr("id").split("_");
				var type = param[1];
				var id = param[0];
				window.location.href="tongji_view.action?type="+type+"&id="+id;
			})
		});
	</script>
</body>
</html>