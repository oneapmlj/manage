<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>OneApm</title>

<link rel="stylesheet"
	href="${applicationScope.staticPath}skin/css/layout.css"
	type="text/css" media="screen" />
<link rel="stylesheet"
	href="${applicationScope.staticPath}skin/css/index.css" type="text/css"
	media="screen" />
<!--[if lt IE 9]>
	<link rel="stylesheet" href="css/ie.css" type="text/css" media="screen" />
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
<script src="${applicationScope.staticPath}skin/js/jquery.min.js"></script>
<script src="${applicationScope.staticPath}skin/js/timeAjax.js"></script>
</head>
<body>
	<s:include value="/WEB-INF/pages/public/header.jsp" />
	<section id="main" class="column"> <article
		class="module width_3_quarter_customer"> <header>
	<h3 class="tabs_involved">发送邮件</h3>
	</header> 
			<div style="width:100%">
				<div style="float:left;padding-left:80px;padding-top:20px;width:390px;">
					<div style="float:left">
						<input style="float:left;" class="check" id="mail_sign"  type="checkbox"  />
						<div style="float:left;" >
							<div style="float:left;font-size:14px;line-height:20px;">注册:</div>
							<div id="mail_sign_select" style="float:left;height:20px;">
							</div>
						</div>
					</div>
					<div style="float:left">
						<input style="float:left;" class="check" id="mail_login"  type="checkbox"  />
						<div style="float:left;" >
							<div style="float:left;font-size:14px;line-height:20px;">登录:</div>
							<div id="mail_login_select" style="float:left;height:20px;">
							</div>
						</div>
					</div>
					<div style="float:left">
						<input style="float:left;" class="check" id="mail_download"  type="checkbox"  />
						<div style="float:left;" >
							<div style="float:left;font-size:14px;line-height:20px;">下载:</div>
							<div id="mail_download_select" style="float:left;height:20px;">
							</div>
						</div>
					</div>
					<div style="float:left">
						<input style="float:left;" class="check" id="mail_add"  type="checkbox"  />
						<div style="float:left;" >
							<div style="float:left;font-size:14px;line-height:20px;">添加:</div>
							<div id="mail_add_select" style="float:left;height:20px;">
							</div>
						</div>
					</div>
				</div>
				<div style="float:left;padding:20px;width:390px;height:100px;">
					<input class="search_button" type="button" style="float:left;" value="搜索"/>
				</div>
				<div id="search_out" style="width:100%;">
					<table class="tablesorter" cellspacing="0"> 
						<thead> 
							<tr> 
								<th style="width:5%;"></th>
					  			<th style="width:7%;">ID</th> 
					  			<th style="width:8%;">Name</th>
					  			<th style="width:11%;">Email</th>
					  			<th style="width:10%;">Company</th>
					  			<th style="width:10%;">Phone</th>
					  			<th id="content_paixu" style="width:10%;">ContactTime</th>
					  			<th id="create_paixu" style="width:10%;">CreateTime</th>
					  			<th id="login_paixu" style="width:10%;">LoginTime</th>
					  			<th id="down_paixu" style="width:10%;">DownTime</th>
					  			<th id="add_paixu" style="width:10%;">AddTime</th>
							</tr> 
						</thead> 
						<tbody> 
							<s:if test="%{report == null || report.users == null || report.users.length <= 0}">
							</s:if>
							<s:else>
								<s:iterator value="report.users">
									<tr> 
							 			<td><input class="user_id" type="checkbox" name="code_Value" value="${userId}" /></td> 
										<td>${userId}</td> 
										<td>${name}</td> 
										<td>${email}</td> 
										<td>${company}</td>
										<td>${phone}</td>
										<td>${createTime}</td>
										<td>${loginTime}</td>
										<td>0000-00-00 00:00:00</td>
									</tr> 
								</s:iterator>
							</s:else>
						</tbody> 
					</table>
				</div>
			</div>
	</article>
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
	<script type="text/javascript">
		$(document).ready(function(){
				$("#add").click(function(){
					var title = $("#title").val();
					var id = $("#mode_id").val();
					var status = $("#status").val();
					var content = $("#content").val();
					$.ajax({
						dataType:'json',
						url:'mode_insert.action',
						data:{title:title,id:id,status:status,content:content}
					}).done(function(da){
						alert(da.status);
					});
				});
		});
	</script>
</body>
</html>