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
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/jquery.min.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/timeAjax.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/timeselect/laydate.js"></script>
</head>
<body>
	<s:include value="/WEB-INF/pages/public/header.jsp" />
	<section id="main" class="column"> 
		<div class="public_main width_3_quarter_customer">
			<div class="header">
				<div style="float:left"><h3 class="tabs_involved">PAY</h3></div>
				<div style="width:800px;float:left;">
					<div style="float:left;width:45px;" class="pay_add pay biankuang_blue_ding menu_button_2">新增</div>
					<div style="float:left;width:45px;" class="pay_past pay biankuang_gray menu_button_2">过期</div>
				</div>
			</div> 
			<div style="width:100%;">
				<div style="margin-left:auto;margin-right:auto;width:500px;margin-top:50px;height:400px;font-size:16px;">
					<div style="float:left;width:350px;margin:0 0 0 20px;">
						<div style="float:left;width:80px;">UserId:</div>
						<div style="float:left;width:200px;"><input id="pay_userid" style="width:180px;" type="text"/></div>
					</div>
					<div style="float:left;width:350px;margin:0 0 0 20px;">
						2015-04-05
					</div>
					<!-- <div style="float:left;width:350px;margin:0 0 0 20px;">
						<div style="float:left;width:80px;">开始时间:</div>
						<div style="float:left;width:200px;">
							<input id="pay_start" style="width:180px;" placeholder="请输入日期" type="text" class="" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/>
						</div>
					</div> -->
					<div style="float:left;width:350px;margin:0 0 0 20px;">
						<div style="float:left;width:80px;">到期时间:</div>
						<div style="float:left;width:200px;">
							<input id="pay_end" style="width:180px;" placeholder="请输入日期" type="text" class="" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/>
						</div>
					</div>
					<div style="float:left;width:350px;margin:0 0 0 20px;">
						<div style="float:left;width:80px;">类型:</div>
						<div style="float:left;width:200px;">
							<select id="pay_level">
								<option value="10" selected="selected">免费用户</option>
								<option value="20">免费过期</option>
								<option value="30">付费未过期</option>
								<option value="40">付费过期</option>
							</select>
						</div>
					</div>
					<div style="float:left;width:350px;margin:0 0 0 20px;">
						<div style="float:left;width:80px;">备注:</div>
						<div style="float:left;width:200px;">
							<input id="pay_mark" style="width:180px;" type="text"/>
						</div>
					</div>
					<div style="float:left;width:350px;margin:0 0 0 20px;">
						<div style="margin-left:220px;float:left;width:60px;">
							<input id="add_pay"  type="button" class="hand" value="提交"/>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/hideshow.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/jquery.tablesorter.min.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/jquery.equalHeight.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/index.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/view.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/timeselect.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".pay_add").live('click', function(){
				$(".pay").removeClass("biankuang_blue_ding");
				$(".pay").addClass("biankuang_gray");
				$(this).removeClass("biankuang_gray");
				$(this).addClass("biankuang_blue_ding");
			});
			$(".pay_past").live('click', function(){
				$(".pay").removeClass("biankuang_blue_ding");
				$(".pay").addClass("biankuang_gray");
				$(this).removeClass("biankuang_gray");
				$(this).addClass("biankuang_blue_ding");
			});
			/* $("#pay_userid").live('change',function(){
				var userId = $("#pay_userid").val();
				$.ajax({
					dataType:'json',
					url:'info_view_json.action',
					data:{userId:userId}
				}).done(function(da){
					if(da.status == 1){
						
					}else{
						alert(da.msg);
					}
				});
			}); */
			$("#add_pay").live('click', function(){
				var userId = $("#pay_userid").val();
				var end = $("#pay_end").val();
				var mark = $("#pay_mark").val();
				var level = $("#pay_level").val();
				$.ajax({
					dataType:'json',
					url:'payadd.action',
					data:{endTime:end,userId:userId,level:level}
				}).done(function(da){
					if(da.status == 1){
						alert("成功");
					}else{
						alert(da.msg);
					}
				});
			});
		});
	</script>
</body>
</html>