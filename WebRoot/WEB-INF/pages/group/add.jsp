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
			<div style="float:left"><h3 class="tabs_involved">分组信息</h3></div>
		</div> 
			<div style="width:100%;">
				<div style="width:1200px;margin-left: auto;margin-right: auto;min-height:500px;">
					<div style="margin-top:30px;width:1000px;margin-left:auto;margin-right:auto;">
						<div style="float:left;" id="group_list">
							<div style="width:120px;float:left;" class="group_1" val="1">
								<select style="width:100px;" class="hand group_list group_list_1">
									<option value="0">分组</option>
									<s:if test="%{groups != null && groups.size > 0}">
										<s:iterator value="groups">
											<option value="${id }">${name }</option>
										</s:iterator>
									</s:if>
								</select>
							</div>
							<div style="width:120px;float:left;" class="hidden group_2" val="2">
							</div>
							<div style="width:120px;float:left;" class="hidden group_3" val="3">
							</div>
							<div style="width:120px;float:left;" class="hidden group_4" val="4">
							</div>
							<div style="width:120px;float:left;" class="hidden group_5" val="5">
							</div>
						</div>
						<div style="float:left; width:180px;">
							<div style="width:180px;font-size:16px;">
								分类：<input type="text" class="group_name" style="width:120px;"/>
							</div>
							<div style="width:180px;font-size:16px;">
								描述：<input class="group_description" type="text" style="width:120px;"/>
							</div>
							<div style="width:180px;">
								<input type="button" value="添加" style="margin-left:75px;width:100px;" class="hand group_add"/>
							</div>
						</div>
					</div>
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
		var father = 0; 
		$(document).ready(function(){
			$(".group_add").live("click", function(){
				var name = $(".group_name").val();
				var description = $(".group_description").val();
				$.ajax({
					dataType:'json',
					url:'group_insert.action',
					data:{father:father,name:name,description:description}
				}).done(function(da){
					if(da.status == 1){
						/* var html = $(".group_list_"+da.grade).html();
						html += "<option value='"+da.id+"'>"+da.name+"</option>";
						$(".group_list_"+da.grade).html(html); */
						aelrt("ok");
					}else{
						alert(da.msg)
					}
				});
			});
			$(".group_list").live("change", function(){
				var groupId = $(this).val();
				var val = Number($(this).parent().attr("val"));
				if(val >= 5){
					return;
				}
				for(var i=val+1;i<=5;i++){
					$(".group_"+i).html("");
					$(".group_"+i).addClass("hidden");
				}if(groupId <= 0){
					if(val == 1){
						father = 0;
					}else{
						var a = val - 1;
						father = $(".group_list_"+a).val();
					}
				}
				if(groupId > 0){
					father = groupId;
					$.ajax({
						dataType:'json',
						url:'group_group.action',
						data:{groupId:groupId}
					}).done(function(da){
						if(da.status == 1){
							if(da.groups.length > 0){
								var groups = da.groups;
								var html = "<select style='width:100px;' class='hand group_list group_list_"+groups[0].grade+"'>"
										+"<option value='0'>所有</option>";
								var i = 0;
								while(groups.length > i){
									html += "<option value='"+groups[i].id+"'>"+groups[i].name+"</option>";
									i++;
								}
								html += "</select>";
								$(".group_"+groups[0].grade).html(html);
								$(".group_"+groups[0].grade).removeClass("hidden");
							}
						}else{
							alert(da.msg)
						}
					});
				}
			});
		});
	</script>
</body>
</html>