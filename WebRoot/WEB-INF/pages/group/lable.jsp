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
			<div style="float:left"><h3 class="tabs_involved">添加标签</h3></div>
		</div> 
			<div style="width:100%;">
				<div style="width:1200px;margin-left: auto;margin-right: auto;min-height:500px;">
					<div style="margin-top:30px;width:1100px;margin-left:auto;margin-right:auto;">
						<div style="width:100%;font-size:16px;line-height:30px;">
							<div style="width:700px;height:30px;">
								<input id="myText" style="width:500px;" value=""/>
								
							</div>
						</div>
						<div style="float:left;margin-top:10px;" id="lable_list">
							<div style="width:120px;float:left;" class="lable_1" val="1">
								<select style="width:100px;" class="hand lable_list lable_list_1">
									<option value="0">分组</option>
									<s:if test="%{lables != null && lables.size > 0}">
										<s:iterator value="lables">
											<option value="${id }">${name }</option>
										</s:iterator>
									</s:if>
								</select>
							</div>
							<div style="width:120px;float:left;" class="hidden lable_2" val="2">
							</div>
							<div style="width:120px;float:left;" class="hidden lable_3" val="3">
							</div>
							<div style="width:120px;float:left;" class="hidden lable_4" val="4">
							</div>
							<div style="width:120px;float:left;" class="hidden lable_5" val="5">
							</div>
							<div style="width:120px;float:left;" class="hidden lable_6" val="6">
							</div>
							<div style="width:120px;float:left;" class="hidden lable_7" val="7">
							</div>
						</div>
						<div style="float:left; width:220px;margin-top:20px;">
							<div style="float:left;width:220px;font-size:14px;line-height:30px;color:red;">2-20个英文字符或2-10个中文字符</div>
							<div style="float:left;width:200px;font-size:16px;">
								<div style="float:left;width:60px;">分类：</div>
								<div style="width:120px;float:left;"><input type="text" class="lable_name" style="width:120px;"/></div>
							</div>
							<div style="float:left;width:370px;font-size:16px;">
								<div style="float:left;width:60px;">描述：</div>
								<div style="width:120px;float:left;"><input type="text" class="lable_description" style="width:120px;"/></div>
							</div>
							<div style="float:left;width:220px;font-size:14px;line-height:30px;color:red;">仅限1-2个英文字符</div>
							<div style="float:left;width:370px;font-size:16px;">
								<div style="float:left;width:60px;">字符：</div>
								<div style="width:120px;float:left;"><input type="text" class="lable_key" style="width:120px;"/></div>
							</div>
							<div style="float:left;width:180px;">
								<input type="button" value="添加" style="margin-left:75px;width:100px;" class="hand lable_add"/>
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
		var lable_list = 1;
		$(document).ready(function(){
			$(".lable_add").live("click", function(){
				var name = $(".lable_name").val();
				var description = $(".lable_description").val();
				var key = $(".lable_key").val();
				$.ajax({
					dataType:'json',
					url:'lable_insert.action',
					data:{father:father,name:name,description:description,key:key}
				}).done(function(da){
					if(da.status == 1){
						var html = "";
						if(lable_list == da.grade){
							html += $(".lable_list_"+da.grade).html();
							html += "<option value='"+da.id+"'>"+da.name+"</option>";
							$(".lable_list_"+da.grade).html(html);
						}else{
							html += "<select style='width:100px;' class='hand lable_list lable_list_"+da.grade+"'>"
									+"<option value='0'>分组</option>";
							html += "<option value='"+da.id+"'>"+da.name+"</option>";
							lable_list = da.grade;
							html += "</select>";
							$(".lable_"+da.grade).removeClass("hidden");
							$(".lable_"+da.grade).html(html);
						}
					}else{
						alert(da.msg)
					}
				});
			});
			$(".lable_list").live("change", function(){
				var lableId = $(this).val();
				var val = Number($(this).parent().attr("val"));
				if(val > 7){
					return;
				}
				for(var i=val+1;i<=7;i++){
					$(".lable_"+i).html("");
					$(".lable_"+i).addClass("hidden");
					lable_list = val;
				}if(lableId <= 0){
					if(val == 1){
						father = 0;
					}else{
						var a = val - 1;
						father = $(".lable_list_"+a).val();
					}
				}
				if(lableId > 0){
					father = lableId;
					$.ajax({
						dataType:'json',
						url:'lable_lable.action',
						data:{lableId:lableId}
					}).done(function(da){
						if(da.status == 1){
							if(da.lables.length > 0){
								var lables = da.lables;
								var html = "<select style='width:100px;' class='hand lable_list lable_list_"+lables[0].grade+"'>"
										+"<option value='0'>所有</option>";
								var i = 0;
								while(lables.length > i){
									html += "<option value='"+lables[i].id+"'>"+lables[i].name+"</option>";
									i++;
								}
								html += "</select>";
								$(".lable_"+lables[0].grade).html(html);
								$(".lable_"+lables[0].grade).removeClass("hidden");
								lable_list = lables[0].grade;
							}
							if(da.from.length > 0){
								$("#myText").val(da.from);
							}else{
								$("#myText").val("");
							}
						}else{
							alert(da.msg)
						}
					});
				}else{
					$("#myText").val("");
					var val = $(this).parent().attr("val");
					if(val > 1){
						val --;
						var lableId = $(".lable_list_"+val).val();
						$.ajax({
							dataType:'json',
							url:'lable_single.action',
							data:{lableId:lableId}
						}).done(function(da){
							if(da.status == 1){
								$("#myText").val(da.lable.from);
							}else{
								alert(da.msg)
							}
						});
					}
				}
			});
		});
	</script>
</body>
</html>