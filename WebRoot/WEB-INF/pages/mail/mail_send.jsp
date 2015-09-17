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
				<h3 class="tabs_involved">发送邮件</h3>
			</div> 
			<div style="width:1200px;margin-left:auto;margin-right:auto;">
				<div style="float:left;width:250px;">
					<s:if test="%{MAIL_MODE != null}">
						<div style="width:220px;float:left;height:30px;font-size:14px;line-height:30px;">
							<div style="float:left;width:45px;">模板：</div>
							<select id="view_mail_mode" style="width:160px;float:left;"  class="hand" >
								<s:iterator value="MAIL_MODE">
									<option value="${id}">${name}</option>
								</s:iterator>
							</select>
						</div>
						<div style="width:220px;float:left;height:30px;font-size:14px;line-height:30px;">
							<div style="float:left;width:45px;">职位：</div>
							<select id="view_mail_zhiwei" style="width:160px;float:left;"  class="hand" >
								<option value="客户经理">客户经理</option>
								<option value="技术支持">技术支持</option>
							</select>
						</div>
						<div style="width:220px;float:left;height:40px;font-size:14px;line-height:40px;" class="person">
							<div style="float:left;width:45px;">姓名：</div>
							<input style="width:160px;float:left;height:16px;margin-top:10px;"  type="text" id="info_name" />
						</div>
						<div style="width:220px;float:left;height:40px;font-size:14px;line-height:40px;" class="person">
							<div style="float:left;width:45px;">注册：</div>
							<select id="view_mail_sign" class="hand" style="width:165px;float:left;height:25px;margin-top:10px;">
								<option value="0">隐藏</option>
								<option value="1">显示</option>
							</select>
							<input class="hidden"  type="text" id="view_mail_sign_value" value="${signTime }"/>
						</div>
						<div style="width:220px;float:left;height:40px;font-size:14px;line-height:40px;">
							<div style="float:left;width:45px;">时间：</div>
							<input style="width:160px;float:left;height:16px;margin-top:10px;"  type="text" id="view_mail_touch" value=""/>
						</div>
						<div style="width:220px;float:left;height:40px;font-size:14px;line-height:40px;">
							<div style="float:left;width:45px;">标题：</div>
							<input style="width:160px;float:left;height:16px;margin-top:10px;"  type="text" id="view_mail_title" value="${signTime }"/>
						</div>
						<div style="width:220px;float:left;height:40px;font-size:14px;line-height:40px;">
							<div style="float:left;width:45px;">标签：</div>
							<input style="width:160px;float:left;height:16px;margin-top:10px;"  type="text" id="view_mail_label" value="0"/>
						</div>
						<s:if test="%{admin.id == 99999999}">
							<div style="width:220px;float:left;height:40px;font-size:14px;line-height:40px;">
								<div style="float:left;width:45px;">目标：</div>
								<select id="view_mail_to" class="hand" style="width:165px;float:left;height:25px;margin-top:10px;">
									<option value="0">${info.email }</option>
									<option value="1">所有注册用户</option>
									<option value="2">所有白名单用户</option>
									<option value="3">MI用户</option>
									<option value="4">MI用户Android</option>
									<option value="5">MI用户IOS</option>
									<option value="6">java</option>
									<option value="7">php</option>
									<option value="8">dotnet</option>
									<option value="9">python</option>
									<option value="10">ruby</option>
									<option value="11">nodejs</option>
									<option value="12">browser</option>
									<option value="13">server</option>
									<option value="14">ci</option>
								</select>
							</div>
						</s:if>
						<s:else>
						<div style="width:220px;float:left;height:40px;font-size:14px;line-height:40px;">
						<div style="float:left;width:45px;">目标：</div>
						<select id="view_mail_to" class="hand" style="width:165px;float:left;height:25px;margin-top:10px;">
									<s:iterator value="userGroupList">
									<option value="0" id="${userId}">${email }</option>
									</s:iterator>
								</select>
							<!-- <div style="width:220px;float:left;height:40px;font-size:14px;line-height:40px;" class="hidden">
								<input type="text" id="view_mail_to" value="0"/>
							</div> -->
							</div>
						</s:else>
						<div style="width:220px;float:left;height:40px;font-size:14px;line-height:40px;">
							<div style="float:left;width:45px;">发件：</div>
							<input style="width:160px;float:left;height:16px;margin-top:10px;"  type="text" id="view_mail_from" value="${admin.email }"/>
						</div>
						<div style="width:220px;float:left;height:120px;font-size:14px;">
							<div style="float:left;width:200px;line-height:30px;">自定义内容：</div>
							<textarea style="width:175px;margin-left:30px;float:left;height:60px;"   id="zidingyi_val"></textarea>
						</div>
						<div style="width:220px;float:left;height:30px;font-size:14px;line-height:40px;">
							<input style="width:205px;float:left;" class="hand view_mail_send"  type="button" value="发送"/>
						</div>
						<div style="width:220px;float:left;height:30px;font-size:14px;line-height:40px;">发送列表</div>
						<div id="mails_liebiao">
							<s:if test="%{mails != null && mails.size > 0}">
								<s:iterator value="mails">
									<div class="view_p_content_220 biankuang_gray_ding mail_view hand" val="${id }">
										<div style="margin-left:5px;">操作者：${adminName }</div>
										<div style="margin-left:5px;">时间：${sendTime }</div>
										<div style="margin-left:5px;">模板名称：${modeName }
										</div>
									</div>
								</s:iterator>
							</s:if>
						</div>
					</s:if>
				</div>
				<div style="float:left;width:800px;height:30px;">
					<div id="yulan" class="menu_button biankuang_blue_ding" style="float:left;width:60px;">预览</div>
					<div id="html" class="menu_button biankuang_gray_ding" style="float:left;width:60px;">HTML</div>
					<div id="html_save" class="menu_button biankuang_gray_ding hidden" style="float:left;width:60px;">保存</div>
				</div>
				<div style="float:left;width:800px;" id="mail_content">
				</div>
				<div style="float:left;width:900px;" id="mail_content_edit" class="hidden">
					<textarea rows="30" style="width:900px;" id="mail_content_edit_val"></textarea>
				</div>
			</div>
		</div>
		<div class="hidden">
			<input type="text" id="admin_id" value="${admin.id }"/>
			<input type="text" id="infoId" value="${infoId }"/>
			<input type="text" id="admin_name" value="${admin.name }"/>
			<input type="text" id="admin_phone" value="${admin.phone }"/>
			<input type="text" id="admin_email" value="${admin.email }"/>
		</div>
	</section>

	<script src="${applicationScope.staticPath}skin/js/hideshow.js" type="text/javascript"></script>
	<script src="${applicationScope.staticPath}skin/js/jquery.tablesorter.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/jquery.equalHeight.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/index.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/view.js"></script>
	<script type="text/javascript">
		/* var touch = "<p>&nbsp;&nbsp;&nbsp;&nbsp;为了能进一步了解您的需求，更好的使用我们的产品，我将于$[touch_time]给您致电，如果时间不合适，请回复我您的方便时间。</p>"; */
		/* var sign = "<p>&nbsp;&nbsp;&nbsp;&nbsp;您在$[sign_time]注册成为我们的客户，但是并没有使用，是否在使用过程中遇到了困难。OneAPM部署无需改动代码，1分钟就可以完成</p>"; */
		var touch = "<tr><td style='font-size:16px;font-family:微软雅黑,黑体,arial;line-height:24px;padding-bottom: 15px;'>为了能进一步了解您的需求，更好的使用我们的产品，我将于<strong style='color:rgb(26, 167, 42)'>$[touch_time]</strong> 给您致电，如果时间不合适，请回复我您方便的时间。</td></tr>";
		var sign = "<tr><td style='font-size:16px;font-family:微软雅黑,黑体,arial;line-height:24px;padding-bottom: 15px;'>您在<strong style='color:rgb(26, 167, 42);'>$[sign_time]</strong> 注册成为我们的客户，但是并没有使用，是否在使用过程中遇到了困难？我们很关心您的使用体验，期待您的回复。</td></tr>";
		var zidingyi = "<tr><td style='font-size:16px;font-family:微软雅黑,黑体,arial;line-height:24px;padding-bottom: 15px;'>$[zidingyi]</td></tr>";
		function findNameById(){
			var id = $("#view_mail_to").find("option:selected").attr("id");
			$.ajax({
				dataType:'json',
				url:'mail_findNameById.action',
				data:{userId:id}
			}).done(function(da){
				if(da.status == 1){
					$("#info_name").val(da.name);
				}
			})
		}
		var init = function(){
			var mode = $("#view_mail_mode").val();
			$.ajax({
				dataType:'json',
				url:'mail_view.action',
				data:{mode:mode}
			}).done(function(da){
				if(da.status == 1){
					$("#view_mail_title").val(da.title);
					var zhiwei = $("#view_mail_zhiwei").val();
					var manager = $("#admin_name").val();
					var phone = $("#admin_phone").val();
					var email = $("#admin_email").val();
					var name = $("#info_name").val();
					var Content = da.content;
					Content = Content.replace("$[zhiwei]",zhiwei);
					Content = Content.replace("$[zhiwei]",zhiwei);
					
					Content = Content.replace("$[zhiwei]",zhiwei);
					Content = Content.replace("$[zhiwei]",zhiwei);
					Content = Content.replace("$[name]",name);
					Content = Content.replace("$[manager]",manager);
					Content = Content.replace("$[manager]",manager);
					Content = Content.replace("$[email]",email);
					Content = Content.replace("$[phone]",phone);
					var touch_time = $("#view_mail_touch").val();
					if(touch_time.trim() != ""){
						var Touch = touch.replace("$[touch_time]", touch_time);
						Content = Content.replace("$[touch]", Touch);
					}else{
						Content = Content.replace("$[touch]", ""); 
					}
					
					var sign_time = $("#view_mail_sign").val();
					if(sign_time == 1){
						var sing_time_value=$("#view_mail_sign_value").val();
						var Sign = sign.replace("$[sign_time]", sing_time_value);
						Content = Content.replace("$[sign]", Sign);
					}else{
						Content = Content.replace("$[sign]", ""); 
					}
					
					var zidingyi_content = $("#zidingyi_val").val();
					if(zidingyi_content.trim() != ""){
						var Zidingyi = zidingyi.replace("$[zidingyi]", zidingyi_content);
						Content = Content.replace("$[zidingyi]", Zidingyi);
					}else{
						Content = Content.replace("$[zidingyi]", ""); 
					}
					
					$("#mail_content").html(Content);
					$("#mail_content_edit_val").val(Content);
				}else{
					alert(da.msg);
				}
			});
		}
		init();
		$(document).ready(function() {
			findNameById();
			$("#biankuang_val").live("change",function(){
				var content = $("#biankuang_val").val();
				if(content != null && content.length > 0){
					$("#biankuang_content").html(content);
				}else{
					$("#biankuang_content").html("");
				}
			});
			$("#yulan").live("click", function(){
				$("#mail_content").removeClass("hidden");
				$("#mail_content_edit").addClass("hidden");
				$("#html_save").addClass("hidden");
				$("#yulan").removeClass("biankuang_gray_ding");
				$("#yulan").addClass("biankuang_blue_ding");
				$("#html").addClass("biankuang_gray_ding");
				$("#html").removeClass("biankuang_blue_ding");
				$("#mail_content_edit_val").val($("#mail_content").html());
			});
			$("#html").live("click", function(){
				$("#mail_content_edit_val").val($("#mail_content").html());
				$("#mail_content").addClass("hidden");
				$("#mail_content_edit").removeClass("hidden");	
				$("#html_save").removeClass("hidden");
				$("#yulan").addClass("biankuang_gray_ding");
				$("#yulan").removeClass("biankuang_blue_ding");
				$("#html").removeClass("biankuang_gray_ding");
				$("#html").addClass("biankuang_blue_ding");
			});
			$("#html_save").live("click", function(){
				$("#mail_content").html($("#mail_content_edit_val").val());
				$("#mail_content").removeClass("hidden");
				$("#mail_content_edit").addClass("hidden");
				$("#yulan").removeClass("biankuang_gray_ding");
				$("#yulan").addClass("biankuang_blue_ding");
				$("#html_save").addClass("hidden");
				$("#html").addClass("biankuang_gray_ding");
				$("#html").removeClass("biankuang_blue_ding");
			});
			$(".mail_view").live("click", function(){
				$(".view_mail_send").val("刷新重新发送");
				$(".view_mail_send").addClass("view_mail_send_no");
				$(".view_mail_send").removeClass("view_mail_send");
				var id = $(this).attr("val");
				$.ajax({
					dataType:'json',
					url:'mail_review.action',
					data:{id:id}
				}).done(function(da){
					if(da.status == 1){
						$("#mail_content").html(da.content);
					}else{
						alert(da.msg);
					}
				});
			});
			$(".view_mail_send").live("click", function(){
				var mode = $("#view_mail_mode").val();
				var infoId = $("#infoId").val();
				var content = $("#mail_content").html();
				var title = $("#view_mail_title").val();
				var to = $("#view_mail_to").val();
				var from = $("#view_mail_from").val();
				var lable = $("#view_mail_label").val();
				$(".view_mail_send").val("正在发送.....");
				$.ajax({
					type:"POST",
					dataType:'json',
					url:'mail_sendSingle.action',
					data:{mode:mode,infoId:infoId,mailContent:content,title:title,to:to,from:from,lable:lable},
					contentType:'application/x-www-form-urlencoded; charset=UTF-8'
				}).done(function(da){
					if(da.status == 1){
						$(".view_mail_send").val("发送成功");
						$(".view_mail_send").addClass("view_mail_send_no");
						$(".view_mail_send").removeClass("view_mail_send");
						var html = $("#mails_liebiao").html();
						var html2 = "<div class='view_p_content_220 biankuang_gray_ding mail_view hand' val='"+da.id+"'>"
									+"<div style='margin-left:5px;''>操作者："+da.adminName+"</div>"
									+"<div style='margin-left:5px;'>时间："+da.time+"</div>"
									+"<div style='margin-left:5px;'>模板名称："+da.description
									+"</div>"
									+"</div>";
						html = html2 + html;
						$("#mails_liebiao").html(html);
					}else{
						$(".view_mail_send").val("发送完成");
						$(".view_mail_send").addClass("view_mail_send_no");
						$(".view_mail_send").removeClass("view_mail_send");
						alert(da.msg);
					}
				});
			});
			$("#view_mail_to").live("change", function(){
				var to = $("#view_mail_to").val();
				findNameById();
				if(to > 0){
					$(".person").addClass("hidden");	
				}else{
					$(".person").removeClass("hidden");	
				}
			});
			$("#zidingyi_val").live("change", function(){
				init();
			});
			$("#view_mail_mode").live("change", function(){
				init();
			});
			$("#view_mail_zhiwei").live("change", function(){
				init();	
			});
			$("#info_name").live("change",function(){
				init();
			});
			$("#view_mail_touch").live("change", function(){
				init();
			});
			$("#view_mail_sign").live("change", function(){
				init();
			});
		});
	</script>
</body>
</html>