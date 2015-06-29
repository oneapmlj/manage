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
	<h3 class="tabs_involved">添加帐号</h3>
	</header> 
			<div style="width:100%;min-height:500px;">
				<div style="padding:30px;margin-left:40px;">
					<div style="width:200px;height:20px;font-size:14px;line-height:20px;">
						<div style="width:60px;float:left;">分组:</div>
						<div style="width:140px;float:left;">
							<select onchange="account_add_group_change(this.value)" id="account_fenzu">
								<option value="1">销售成员</option>
								<option value="2">运营成员</option>
								<!-- <option value="3">售前成员</option> -->
								<option value="4">销售管理</option>
								<option value="5">运营管理</option>
								<option value="6">售前管理</option>
								<option value="7">高级管理</option>
							</select>
						</div>
					</div>
					<div style="width:200px;height:20px;font-size:14px;line-height:20px;">
						<div style="width:60px;float:left;">ID:</div>
						<div style="width:140px;float:left;"><input type="text" id="account_id"/></div>
					</div>
					<div style="width:200px;height:20px;font-size:14px;line-height:20px;">
						<div style="width:60px;float:left;">姓名:</div>
						<div style="width:140px;float:left;"><input type="text" id="account_name"/></div>
					</div>
					<div style="width:200px;height:20px;font-size:14px;line-height:20px;">
						<div style="width:60px;float:left;">邮箱:</div>
						<div style="width:140px;float:left;"><input type="text" id="account_email"/></div>
					</div>
					<div style="width:200px;height:40px;font-size:14px;line-height:20px;">
						<div style="width:60px;float:left;">权限:</div>
						<div style="width:140px;float:left;">
							<s:iterator value="quanxians">
								<div><input type="checkbox" name="quanxian" value="${quanxian }"/>${name }</div>
							</s:iterator>
							<!-- <div><input type="checkbox" name="account_card_add"/>添加名片&nbsp;&nbsp;</div>
							<div><input type="checkbox" name="account_call_add"/>添加电话记录&nbsp;&nbsp;</div>
							<div><input type="checkbox" name="account_info_add"/>添加用户&nbsp;&nbsp;</div>
							<div><input type="checkbox" name="account_gongdan_view"/>工单操作&nbsp;&nbsp;</div>
							<div><input type="checkbox" name="account_assign_view"/>分配&nbsp;&nbsp;</div>
							<div><input type="checkbox" name="account_all"/>全部权限&nbsp;&nbsp;</div> -->
						</div>
					</div>
					<div style="width:200px;height:20px;font-size:14px;line-height:20px;">
						<input class="hand" type="button" id="add_account" value="提交"/>
					</div>
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
		var account_add_group_change = function(group){
			$.ajax({
				dataType:'json',
				url:'account_ID.action',
				data:{group:group}
			}).done(function(da){
				if(da.status == 1){
					$("#account_id").val(da.id);
				}
			});
		};
		$(document).ready(function(){
			$("#add_account").live('click', function(){
				var quanxians = document.all("quanxian");
				var quanxian = "";
				for(i=0;i<quanxians.length;i++){
					if(quanxians[i].checked){
						quanxian += $(quanxians[i]).val();
					}else{
					}
				}
				var name = $("#account_name").val();
				var email = $("#account_email").val();
				var id = $("#account_id").val();
				var group = $("#account_fenzu").val();
				if(name == null){
					alert("姓名不能为空");
					return;
				}
				if(email == null){
					alert("邮箱不能为空");
					return;
				}
				if(id == null){
					alert("ID不能为空");
					return;
				}
				$.ajax({
					dataType:'json',
					url:'account_insert.action',
					data:{quanxian:quanxian, name:name,email:email,id:id,group:group}
				}).done(function(da){
					if(da.status == 1){
						alert("添加成功");
					}else{
						alert(da.msg);
					}
				});
			});
		});
	</script>
</body>
</html>