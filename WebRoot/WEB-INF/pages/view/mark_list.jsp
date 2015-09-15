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
		<div style="float:left"><h3 class="tabs_involved">标记信息</h3></div>
	</div> 
		<div style="width:100%;">
			<div style="width:1200px;margin-left: auto;margin-right: auto;min-height:500px;">
				<s:if test="%{marks != null}">
					<s:iterator value="marks">
						<div id="list_mark_${id }" style="line-height:30px;font-size:16px;width:100%;float:left;">
							<div style="float:left;width:60px;color:red;" id="${id }" class="list_mark_d biankuang_gray menu_button_2">取消标记</div>
							<div style="margin-left:10px;float:left;width:200px;"><a style="text-decoration : none;color:#000000;" href="user_group_view.action?id=${groupId}">${projectName }</a></div>
							<div style="width:200px;float:left;">${createTime}</div>
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
						url:'mark_close.action',
						data:{id:id}
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