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
			<div style="width:1200px;margin-left: auto;margin-right: auto;min-height:500px;">
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
	</script>
</body>
</html>