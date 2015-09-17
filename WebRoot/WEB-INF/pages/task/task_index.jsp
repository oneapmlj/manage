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
			<div class="header">
				<div style="width:100px;line-height:30px;margin-left:30px;float:left;font-size:16px;">任务</div>
				<div style="width:700px;float:left;">
					<div style="float:left;width:80px;font-size:16px;line-height:30px;"  class="">排序方式：</div>
					<s:if test="%{type <= 1}"><div style="float:left;width:45px;" id="type=1" class="task_view biankuang_blue_ding menu_button_2">类型</div></s:if>
					<s:else><div style="float:left;width:45px;" id="type=1" class="task_view biankuang_gray menu_button_2">类型</div></s:else>
					<%-- <s:if test="%{type == 2}"><div style="float:left;width:85px;" id="type=2" class="task_view biankuang_blue_ding menu_button_2">登录时间</div></s:if>
					<s:else><div style="float:left;width:85px;" id="type=2" class="task_view biankuang_gray menu_button_2">登录时间</div></s:else>
					<s:if test="%{type == 3}"><div style="float:left;width:85px;" id="type=3" class="task_view biankuang_blue_ding menu_button_2">注册时间</div></s:if>
					<s:else><div style="float:left;width:85px;" id="type=3" class="task_view biankuang_gray menu_button_2">注册时间</div></s:else> --%>
					<s:if test="%{type == 4}"><div style="float:left;width:85px;" id="type=4" class="task_view biankuang_blue_ding menu_button_2">任务时间</div></s:if>
					<s:else><div style="float:left;width:85px;" id="type=4" class="task_view biankuang_gray menu_button_2">任务时间</div></s:else>
				</div>
			</div>
		</div> 
		<div style="width:100%;">
			<div style="width:100%;">
				<div style="width:1200px;margin-left:auto;margin-right:auto;">
					<div style="width:1200px;height:40px;line-height:40px;border: 1px solid #DDD;" >
						<div style="margin-left:10px;float:left;width:200px;">公司</div>
						<div style="float:left;width:120px;">类型</div>
						<div style="float:left;width:140px;">任务推送时间</div>
						<div style="float:left;width:140px;">来源</div>
						<div style="float:left;width:40px;">记录</div>
						<div style="float:left;width:300px;text-align: left;" >操作</div>
						<div style="float:left;width:100px;text-align: left;" >时限</div>
					</div>
					<s:iterator value="tasks">
						<div id="task_${push.id }" style="<s:if test='%{push.point}'>color:red;</s:if>width:1200px;height:40px;line-height:40px;border: 1px solid #DDD;<s:if test="%{warming == 1}">color:red;</s:if> " taskId=${push.id } groupId=${userGroups.groupId }>
							<div style="margin-left:10px;float:left;width:200px;">${userGroups.groupName }</div>
							<div style="float:left;width:120px;">
								<s:if test="%{push.type == 1}">未激活</s:if>
								<s:if test="%{push.type == 2}">欢迎使用</s:if>
								<s:if test="%{push.type == 3}">注册未下载</s:if>
								<s:if test="%{push.type == 4}">注册未登录</s:if>
								<s:if test="%{push.type == 5}">下载无应用</s:if>
								<s:if test="%{push.type == 6}">长时间未登录</s:if>
								<s:if test="%{push.type == 7}">长时间无数据</s:if>
								<s:if test="%{push.type == 8}">${push.name }</s:if>
								<s:if test="%{push.type == 9}">再联系</s:if>
								<s:if test="%{push.type == 10}">销售商务</s:if>
								<s:if test="%{push.type == 11}">技术支持</s:if>
								<s:if test="%{push.type == 12}">最新应用</s:if>
							</div>
							<div style="float:left;width:140px;">${push.putTime }</div>
							<div style="float:left;width:140px;">
								<s:if test="%{push.from == 0}">系统推送</s:if>
								<s:if test="%{push.from == 1}">转交(${push.fromName })</s:if>
								<s:if test="%{push.from == 2}">生成(${push.fromName })</s:if>
								<s:if test="%{push.from == 3}">回收(${push.fromName })</s:if>
							</div>
							<div style="float:left;width:40px;">${push.number }</div>
							<div style="float:left;width:300px;margin-top:10px;">
								<div style="float:left;width:55px;" class="pass_${push.id }"><input class="recommend_pass hand" type="button" value="转交"/></div>
								<div style="float:left;width:55px;" class="put_${push.id }"><input class="recommend_put hand" type="button" value="推迟"/></div>
								<div style="float:left;width:55px;"><input class="recommend_view hand" type="button" value="查看"/></div>
								<div style="float:left;width:55px;"><input class="recommend_remove hand" type="button" value="忽略"/></div>
								<div style="float:left;width:55px;"><input class="recommend_delete hand" type="button" value="关闭"/></div>
								<%-- <s:if test="%{push.type < 8}">
									<div style="float:left;width:70px;"><input class="recommend_mail hand" type="button" value="发邮件"/></div>
									<div style="float:left;width:90px;"><input class="recommend_preview hand" type="button" value="邮件预览"/></div>
								</s:if> --%>
							</div>
							<div style="float:left;width:100px;">
								<s:if test="%{warming == 1}">${hour }小时</s:if>
							</div>
						</div>
					</s:iterator>
				</div>
			</div>
		</div>
		</div>
	</section>
	<div class="mail_add hidden" style="z-index:1002;">
		<input type="text" class="hidden" id="taskId"/>
	</div>
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
			$(".task_view").live('click', function(){
				var id = $(this).attr("id");
				location.href="task_index.action?"+id;
			});
			$(".recommend_deal").live('click', function(){
				var taskId = $(this).parent().parent().parent().attr("taskId");
				$.ajax({
					dataType:'json',
					url:'mark_add.action',
					data:{id:taskId}
				}).done(function(da){
					if(da.status == 1){
						
					}
				});
			});
			$(".recommend_pass").live('click', function(){
				var taskId = $(this).parent().parent().parent().attr("taskId");
				$("#taskId").val(taskId);
				$.ajax({
					dataType:'json',
					url:'account_task_pass.action',
					data:{}
				}).done(function(da){
					if(da.status == 1){
						var html = "<div style='z-index:1002;position:absolute;width:80px;background-color:#F8F8F8;margin:-20px 0 0 55px' class='task_pass_list'>" +
								"<div style='color:gray;border: 1px solid #9CA1B0;' class='pass_off boder hand'>&nbsp;取消</div>";
						var admins = da.admins;
						for(var i=0;i<admins.length;i++){
							html+="<div style='border: 1px solid #9CA1B0;' class='task_pass_one boder hand' val1='"+admins[i].id+"'>&nbsp;"+admins[i].name+"</div>";
						}
						html+="</div>";
						var taskId = $("#taskId").val();
						$(".pass_"+taskId).append(html);
						$(".background").removeClass("hidden");
					}else{
						alert(da.msg);
					}
				})
			});
			$(".pass_off").live('click', function(){
				$(".task_pass_list").remove();
				$("#taskId").val("");
				$(".background").addClass("hidden");
			});
			$(".task_pass_one").live('click', function(){
				var taskId = $("#taskId").val();
				var adminId = $(this).attr("val1");
				$.ajax({
					dataType:'json',
					url:'task_pass.action',
					data:{id:taskId,passId:adminId}
				}).done(function(da){
					if(da.status == 1){
						$("#task_"+da.id).remove();
						$(".task_pass_list").remove();
						$("#taskId").val("");
						$(".background").addClass("hidden");
					}else{
						alert(da.msg);
					}
				}); 
			});
			$(".recommend_put").live('click', function(){
				var taskId = $(this).parent().parent().parent().attr("taskId");
				$("#taskId").val(taskId);
				var html = "<div style='z-index:1002;position:absolute;width:250px;background-color:#F8F8F8;margin:-20px 0 0 55px;height:40px;line-hieght:40px;'  class='task_put_list'>" 
									+"<div style='float:left;width:120px'>"
									+"<select style='margin:10px 0 0 5px;width:110px;' id='task_put_select'>"
									+"<option value='6'>6小时</option>"
									+"<option value='18'>18小时</option>"
									+"<option value='24'>1天</option>"
									+"<option value='48'>2天</option>"
									+"<option value='72'>3天</option>"
									+"<option value='96'>4天</option>"
									+"</select>"
									+"</div>"
									+"<div style='margin:5px 0 0 0;float:left;width:50px;height:25px;border: 1px solid #9CA1B0;' class='put_push boder hand'>&nbsp;推迟</div>"
									+"<div style='margin:5px 0 0 6px;float:left;width:50px;height:25px;color:gray;border: 1px solid #9CA1B0;' class='put_off boder hand'>&nbsp;取消</div>"
									+"</div>";
				var taskId = $("#taskId").val();
				$(".put_"+taskId).append(html);
				$(".background").removeClass("hidden");
			});
			$(".put_off").live('click', function(){
				$(".task_put_list").remove();
				$("#taskId").val("");
				$(".background").addClass("hidden");
			});
			$(".put_push").live('click', function(){
				var taskId = $("#taskId").val();
				var putTime = $("#task_put_select").val();
				$.ajax({
					dataType:'json',
					url:'task_put.action',
					data:{id:taskId,putTime:putTime}
				}).done(function(da){
					if(da.status == 1){
						$("#task_"+da.id).remove();
						$("#taskId").val("");
						$(".task_put_list").remove();
						$(".background").addClass("hidden");
					}else{
						alert(da.msg);
					}
				});
			});
			$(".recommend_view").live('click', function(){
				var infoId = $(this).parent().parent().parent().attr("infoId");
				var groupId = $(this).parent().parent().parent().attr("groupId");
				window.open("user_group_view.action?id="+groupId);
			});
			$(".recommend_mail").live('click', function(){
				var taskId = $(this).parent().parent().parent().attr("taskId");
				$("#taskId").val(taskId);
				$.ajax({
					dataType:'json',
					url:'account_grade.action',
					data:{type:100}
				}).done(function(da){
					if(da.status == 1){
						var taskId = $("#taskId").val();
						$.ajax({
							dataType:'json',
							url:'mail_sendPush.action',
							data:{id:taskId}
						}).done(function(da){
							if(da.status == 1){
								var taskId = $("#taskId").val();
								$("#task_"+taskId).remove();
								$("#taskId").val("");
							}else{
								alert(da.msg);
							}
						});
					}else{
						alert(da.msg);
					}
				});
			});
			$(".recommend_remove").live('click', function(){
				$(this).parent().parent().parent().remove();
			});
			$(".recommend_delete").live('click', function(){
				var taskId = $(this).parent().parent().parent().attr("taskId");
				var groupId = $(this).parent().parent().parent().attr("groupId");
				$.ajax({
					dataType:'json',
					url:'task_remove.action',
					data:{id:taskId}
				}).done(function(da){
					if(da.status == 1){
						$("#task_"+da.id).remove();
					}else{
						alert(da.msg);
					}
				});
			});
			$(".recommend_preview").live('click', function(){
				var taskId = $(this).parent().parent().parent().attr("taskId");
				$("#taskId").val(taskId);
				$.ajax({
					dataType:'json',
					url:'account_grade.action',
					data:{type:100}
				}).done(function(da){
					if(da.status == 1){
						var taskId = $("#taskId").val();
						$("#taskId").val("");
						window.open("mail_previewPush.action?id="+taskId);
					}else{
						alert(da.msg);
					}
				});
			});
		});
	</script>
</body>
</html>