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
			<div  style="float:left;width:150px;"><h3 class="">${lang }(${size })</h3></div>
			<div style="width:700px;float:left;">
				<s:iterator value="LANGUAGE">
					<s:if test="%{id > 0}">
						<s:if test="%{language == id}"><div style="float:left;width:60px;" id="language=${id}" class="language_list biankuang_blue_ding menu_button_2">${name}</div></s:if>
						<s:else><div style="float:left;width:60px;" id="language=${id}" class="language_list biankuang_gray menu_button_2">${name }</div></s:else>
					</s:if>
				</s:iterator>
			</div>
		</div> 
		<div style="width:100%;min-width:1200px;">
			<div style="min-width:1200px;width:100%;margin-left: auto;margin-right: auto;">
				<table class="tablesorter" cellspacing="0"> 
						<thead> 
							<tr> 
								<th style="width:5%;"></th> 
					  			<th style="width:7%;">ID</th> 
					  			<th style="width:10%;">Name</th>
					  			<th style="width:14%;">Email</th>
					  			<th style="width:12%;">Company</th>
					  			<th style="width:12%;">Phone</th>
					  			<th style="width:12%;">CreateTime</th>
					  			<th style="width:12%;">LoginTime</th>
					  			<th style="width:10%">Edit</th>
							</tr> 
						</thead> 
						
						<tbody> 
							<s:if test="%{infos != null && infos.size > 0}">
								<s:iterator value="infos">
									<tr class="hand"> 
										<td></td>
										<td>${id}</td> 
										<td>${name}</td> 
										<td>${email}</td> 
										<td>${company}</td>
										<td>${phone}</td>
										<td>${createTime}</td>
										<td>${loginTime}</td> 
										<td>
											<input val1="${id}" class="check_gongdan"  type="image" src="${applicationScope.staticPath }skin/images/icn_view_users.png" title="工单" />
											<input val1="${id}" class="check_view"  type="image" src="${applicationScope.staticPath }skin/images/icn_view_users.png" title="查看" />
											<input class="check_remove" type="image" src="${applicationScope.staticPath}skin/images/icn_logout.png" title="忽略" />
										</td> 
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
			$(".language_list").live('click', function(){
				var id = $(this).attr('id');
				window.location.href="info_language.action?"+id; 
			});
		});
	</script>
</body>
</html>