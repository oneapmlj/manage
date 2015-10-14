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
		<div style="float:left"><h3 class="tabs_involved">消息历史</h3></div>
	</div> 
		<div style="width:100%;">
		<div style="width:1100px;float:left;line-height:30px;font-size:14px;margin-left:250px;">
			<div style="color:#4F94CD;height:30px;width:1100px;float:left;margin-left:250px;">
				<div style="float:left;width:350px;">
					 当前第(<span  id="message_page_now">${pageNow} </span>)页 
					| 总共(<span  id="message_page_total">${pageTotal}</span>)页
					| 每页显示<select id="page_size" >
								<option value="30">30</option>
								<option value="50">50</option>						
							</select>条
				</div>
				<div class="hand page_first" style="float:left;width:50px;">首页</div>
				<s:if test="%{pageNow > 1}">
					<div class="hand page_before" style="float:left;width:60px;">上一页</div>
				</s:if>
				<s:else>
					<div class="hidden hand page_before" style="float:left;width:60px;">上一页</div>
				</s:else>
				<s:if test="%{pageNow < pageTotal}">
					<div class="hand page_next" style="float:left;width:60px;">下一页</div>
				</s:if>
				<s:else>
					<div class="hidden hand page_next" style="float:left;width:60px;">下一页</div>
				</s:else>
				<div class="hand page_last" style="float:left;width:50px;">末页</div>
			</div>
		</div>
			<div id="msg" style="width:1200px;margin-left: auto;margin-right: auto;min-height:500px;">
				<s:if test="%{vos != null}">
					<s:iterator value="vos">
						<div id="list_message_${message.id }" groupId = "${message.groupId} "
							style="line-height:40px;font-size:14px;margin-left:200px;width:800px;float:left;<s:if test='%{message.status==0}'>color:red;</s:if><s:else>color:gray;</s:else>">
							<div style="float:left;width:150px;font-size:12px;">${message.createTime }</div>
							<div style="float:left;width:400px;"><span style="font-size:16px;">${fromName } </span>
								<s:if test="%{message.type==7 || message.type==8}">提醒你关注</s:if>
								<s:if test="%{message.type==11 || message.type==9 || message.type==10}">将用户</s:if>
								<s:if test="%{message.type==15}">在用户</s:if>
								<s:if test="%{message.type==16 || message.type==17 || message.type==18}">收回了你负责的用户</s:if>
								<s:if test="%{message.type==19 || message.type==20}">更改了</s:if>
								<s:if test="%{message.type==21}">在用户</s:if>
								<span style="font-size:16px;">  ${company } </span>
								<s:if test="%{message.type==7 || message.type==8}">的使用状态</s:if>
								<s:if test="%{message.type==11 || message.type==9 || message.type==10}">分配给你</s:if>
								<s:if test="%{message.type==15}">添加了记录</s:if>
								<s:if test="%{message.type==16 || message.type==17 || message.type==18}">收回了你负责的用户</s:if>
								<s:if test="%{message.type==19}">的定位</s:if>
								<s:if test="%{message.type==20}">的进度</s:if>
								<s:if test="%{message.type==21}">中@了你</s:if>
							</div>
							<div style="float:left;width:150px;">
							<s:if test='%{message.groupId!=null}'>
								<div class="hand  message_view_check_chakan" style="width:60px;float:left;">查看</div>
								</s:if>
								<s:if test='%{message.status==0}'>
									<div class="hand message_view_queding" style="width:60px;float:left;">确定</div>
								</s:if>
							</div>
						</div>
					</s:iterator>
				</s:if>
			</div>
		</div>
	</div>
	</section>

	<script src="${applicationScope.staticPath}skin/js/hideshow.js" type="text/javascript"></script>
	<script src="${applicationScope.staticPath}skin/js/jquery.tablesorter.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/jquery.equalHeight.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/index.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/view.js"></script>
	<script type="text/javascript">
	
		$(document).ready(function(){
				$(".list_mark_d").live('click', function(){
					var id = $(this).attr("id");
					$.ajax({
						dataType:'json',
						url:'mark_update.action',
						data:{id:id,status:1}
					}).done(function(da){
						if(da.status == 1){
							$("#list_mark_"+da.mark.id).remove();
						}
					});
				});
		});
		$("#page_size").live('change', function(){
			var pageNow = $("#message_page_now").html();
			pageShow(pageNow);
			
		});
		function pageShow(j){
			var pageSize = $("#page_size").val();
			var pageNow = j;
			var pageNext = pageNow*1-1;
			$.ajax({
				dataType:'json',
				url:'message_page.action',
				data:{pageSize:pageSize,page:pageNext}
			}).done(function(da){
				if(da.status==1){
					$("#msg").empty();
					var data1 = da.vos;
					var html = "";
					for(var i = 0 ; i < data1.length; i++){
						var data = data1[i].message;
						if(data.status==0){
						html+="<div id='list_message_"+data.id+"' style='line-height:40px;font-size:14px;margin-left:200px;width:800px;float:left;color:red;' groupId="+data1[i].groupId+">";
						}else{
						html+="<div id='list_message_"+data.id+"' style='line-height:40px;font-size:14px;margin-left:200px;width:800px;float:left;color:gray;' groupId="+data1[i].groupId+">";
						}
						html+="<div style='float:left;width:150px;font-size:12px;'>"+data.createTime+"</div>";
						html+="<div style='float:left;width:400px;'><span style='font-size:16px;'>"+data1[i].fromName+" </span>";
						if(data.type==7||data.type==8){
							html+="提醒你关注";
						}
						if(data.type==11||data.type==9||data.type==10){
							html+="将用户";
						}
						if(data.type==15){
							html+="在用户";
						}
						if(data.type==16||data.type==17||data.type==18){
							html+="收回了你负责的用户";
						}
						if(data.type==19||data.type==20){
							html+="更改了";
						}
						if(data.type==21){
							html+="在用户";
						}
						html+="<span style='font-size:16px;'>"+data1[i].company+" </span>";
						if(data.type==7||data.type==8){
							html+="的使用状态</div>";
						}
						if(data.type==11||data.type==9||data.type==10){
							html+="分配给你</div>";
						}
						if(data.type==15){
							html+="添加记录</div>";
						}
						if(data.type==16||data.type==17||data.type==18){
							html+="收回了你负责的用户</div>";
						}
						if(data.type==19){
							html+="的定位</div>";
						}
						if(data.type==20){
							html+="的进度</div>";
						}
						if(data.type==21){
							html+="中@了你</div>";
						}
						html+="<div style='float:left;width:150px;'>";
						if(data1[i].groupId!="null"&&data1[i].groupId!=null){
							html+="<div class='hand  message_view_check_chakan' style='width:60px;float:left;'>查看</div>";
						}
						if(data.status!=0){
							html+="<div class='hand message_view_queding' style='width:60px;float:left;'>确定</div></div>";
						} else{
							html+="</div>"
						}
						html+="</div>";
					} 
					$("#msg").html(html);
					$("#message_page_total").text(da.pageTotal);
				}
			});
		}
		$(".page_next").live('click', function(){
			var pageNow = $("#message_page_now").text()*1+1;
			pageShow(pageNow);
			var pageTotal=$("#message_page_total").text();
			
			$("#message_page_now").text(pageNow);
			if(pageNow > 1){
				$(".page_before").removeClass("hidden");
			}else{
				$(".page_before").addClass("hidden");
			}
			if(pageNow < pageTotal){
				$(".page_next").removeClass("hidden");
			}else{
				$(".page_next").addClass("hidden");
			}
		});
		$(".page_before").live('click', function(){
			var pageNow = $("#message_page_now").text()*1-1;
			pageShow(pageNow);
			var pageTotal=$("#message_page_total").text();
			
			$("#message_page_now").text(pageNow);
			if(pageNow > 1){
				$(".page_before").removeClass("hidden");
			}else{
				$(".page_before").addClass("hidden");
			}
			if(pageNow < pageTotal){
				$(".page_next").removeClass("hidden");
			}else{
				$(".page_next").addClass("hidden");
			}
		});
		$(".page_first").live('click', function(){
			var pageNow = 1;
			pageShow(pageNow);
			var pageTotal=$("#message_page_total").text();
			
			$("#message_page_now").text(pageNow);
			$(".page_before").addClass("hidden");
			if(pageNow < pageTotal){
				$(".page_next").removeClass("hidden");
			}else{
				$(".page_next").addClass("hidden");
			}
		});
		$(".page_last").live('click', function(){
			var pageTotal=$("#message_page_total").text();
			pageShow(pageTotal);
			$("#message_page_now").text(pageTotal);
			if(pageTotal > 1){
				$(".page_before").removeClass("hidden");
			}else{
				$(".page_before").addClass("hidden");
			}
			
				$(".page_next").addClass("hidden");
			
		});
		
	</script>
</body>
</html>