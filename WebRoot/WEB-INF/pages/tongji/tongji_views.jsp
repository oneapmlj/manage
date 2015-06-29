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
			<h3 class="tabs_involved">${index.name}(${index.date})</h3>
		</div> 
		<div style="width:100%;min-width:1200px;">
			<div style="min-width:1200px;width:100%;margin-left: auto;margin-right: auto;">
				<table class="tablesorter" cellspacing="0"> 
						<thead> 
							<tr> 
								<th style="width:3%;"></th>
					  			<th style="width:6%;">ID</th> 
					  			<th style="width:10%;">Name</th>
					  			<th style="width:14%;">Project</th>
					  			<th style="width:15%">loginTime</th>
					  			<th style="width:15%;">Language</th>
					  			<th style="width:7%;">Coming</th>
					  			<th style="width:6%;">Sale</th>
					  			<th style="width:6%;">Support</th>
					  			<th style="width:6%;">PreSale</th>
					  			<th style="width:10%">Edit</th>
					  			<th style="width:2%"></th>
							</tr> 
						</thead> 
						
						<tbody> 
							<s:if test="%{index.todayUp != null && index.todayUp.size > 0}">
								<s:iterator value="index.todayUp">
									<tr style="color:red;" class="hand"> 
										<td></td>
										<td>${userId}</td> 
										<td>${name}</td> 
										<td>${company}</td>
										<td>${loginTime}</td>
										<td>${language }</td>
										<td>${from}</td>
										<s:if test="%{saleName != null}">
											<td>${saleName }</td>
										</s:if>
										<s:else><td style="color:#00FF00;">无</td></s:else>
										<s:if test="%{supportName != null}">
											<td>${supportName }</td>
										</s:if>
										<s:else><td style="color:#00FF00;">无</td></s:else>
										<s:if test="%{preSaleName != null}">
											<td>${preSaleName }</td>
										</s:if>
										<s:else><td style="color:#00FF00;">无</td></s:else>
										<td>
											<s:if test="%{level >0}">
												<input val1="${id}" class="check_gongdan"  type="image" src="${applicationScope.staticPath }skin/images/icn_view_users.png" title="工单" />
												<input val1="${id}" class="check_view"  type="image" src="${applicationScope.staticPath }skin/images/icn_view_users.png" title="查看" />
											</s:if>
											<input class="check_remove" type="image" src="${applicationScope.staticPath}skin/images/icn_logout.png" title="忽略" />
										</td> 
										<td></td>
									</tr> 
								</s:iterator>
							</s:if>
							<s:if test="%{index.todayDown != null && index.todayDown.size > 0}">
								<s:iterator value="index.todayDown">
									<tr style="color:blue;" class="hand"> 
										<td></td>
										<td>${userId}</td> 
										<td>${name}</td> 
										<td>${company}</td>
										<td>${loginTime}</td>
										<td>${language }</td>
										<s:if test="%{saleName != null}">
											<td>${saleName }</td>
										</s:if>
										<s:else><td style="color:#00FF00;">无</td></s:else>
										<s:if test="%{supportName != null}">
											<td>${supportName }</td>
										</s:if>
										<s:else><td style="color:#00FF00;">无</td></s:else>
										<s:if test="%{preSaleName != null}">
											<td>${preSaleName }</td>
										</s:if>
										<s:else><td style="color:#00FF00;">无</td></s:else>
										<td>
											<s:if test="%{level >0}">
												<input val1="${id}" class="check_gongdan"  type="image" src="${applicationScope.staticPath }skin/images/icn_view_users.png" title="工单" />
												<input val1="${id}" class="check_view"  type="image" src="${applicationScope.staticPath }skin/images/icn_view_users.png" title="查看" />
											</s:if>
											<input class="check_remove" type="image" src="${applicationScope.staticPath}skin/images/icn_logout.png" title="忽略" />
										</td> 
										<td></td>
									</tr> 
								</s:iterator>
							</s:if>
							<s:if test="%{index.today != null && index.today.size > 0}">
								<s:iterator value="index.today">
									<tr class="hand"> 
										<td></td>
										<td>${userId}</td> 
										<td>${name}</td> 
										<td>${company}</td>
										<td>${loginTime}</td>
										<td>${language }</td>
										<s:if test="%{saleName != null}">
											<td>${saleName }</td>
										</s:if>
										<s:else><td style="color:#00FF00;">无</td></s:else>
										<s:if test="%{supportName != null}">
											<td>${supportName }</td>
										</s:if>
										<s:else><td style="color:#00FF00;">无</td></s:else>
										<s:if test="%{preSaleName != null}">
											<td>${preSaleName }</td>
										</s:if>
										<s:else><td style="color:#00FF00;">无</td></s:else>
										<td>
											<s:if test="%{level >0}">
												<input val1="${id}" class="check_gongdan"  type="image" src="${applicationScope.staticPath }skin/images/icn_view_users.png" title="工单" />
												<input val1="${id}" class="check_view"  type="image" src="${applicationScope.staticPath }skin/images/icn_view_users.png" title="查看" />
											</s:if>
											<input class="check_remove" type="image" src="${applicationScope.staticPath}skin/images/icn_logout.png" title="忽略" />
										</td> 
										<td></td>
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
				window.open("tongji_view.action?type="+type+"&id="+id);
			})
		});
	</script>
</body>
</html>