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
				  			<!-- <th style="width:70px;">总数</th> -->
				  			<th style="width:70px;">注册</th>
				  			<th style="width:65px;">登录</th>
				  			<th style="width:65px;">下载</th>
				  			<th style="width:65px;">应用</th>
				  			<th style="width:65px;">数据</th>
				  			<th style="width:65px;">数应</th>     
				  			
						</tr> 
					</thead> 
					<tbody class="tbody"> 
						<s:if test="%{index != null && index.tongjis != null && index.tongjis.size >0}">
							<s:iterator value="index.tongjis">
								<tr style="font-size:16px;" id="${id}"> 
						 			<td>${data_time}</td>
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