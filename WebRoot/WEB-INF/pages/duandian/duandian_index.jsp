<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>OneApm</title>
	<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/layout.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/index.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/view.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/public.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/duandian.css" type="text/css" media="screen" />
	<script src="${applicationScope.staticPath}skin/js/jquery.min.js"></script>
	<script src="${applicationScope.staticPath}skin/js/guolv.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/timeselect/laydate.js"></script>
</head>
<body>
	<s:include value="/WEB-INF/pages/public/header.jsp" />
	<section id="main" class="column"> 
		<div class="public_main width_3_quarter_customer">
		<div class="header">
			<h3 class="tabs_involved">过滤用户</h3>
		</div> 
			<div style="width:1200px;margin-left:auto;margin-right:auto;font-size:14px;">
				<div style="width:1200px;float:left;height:30px;line-height:30px;margin-top:20px;">
					<div style="width:70px;float:left;height:30px;">
						<div style="float:left;width:60px;margin-top:5px;" class="go_guolv_ding menu_button biankuang_blue_ding hand">过滤</div>
					</div>
					<div style="width:70px;float:left;height:30px;">
						<div style="float:left;width:60px;margin-top:5px;" class="go_from menu_button biankuang_gray hand">来源</div>
					</div>
					<!-- <div style="width:120px;float:left;height:30px;">
						<div style="float:left;width:110px;margin-top:5px;" class="go_baobiao menu_button biankuang_gray hand">报表查询</div>
					</div> -->
					<div style="float:left;width:250px;margin-left:10px;height:30px;">
						排序方式:
						<select id="paixu" class="hand">
							<option value="0">默认排序</option>
							<option value="1">注册时间</option>
							<!-- <option value="2">UserId排序</option> -->
						</select>
					</div>
				</div>
				<div style="width:1200px;float:left;" class="guolv">
					<div style="width:1200px;float:left;height:30px;line-height:30px;">
						<div>
							<span style="color:red;">过滤时状态，数据，登录必选其一。独立用户只在有数据时有效</span>
						</div>
					</div>
					<div style="margin-top:10px;width:1200px;height:30px;line-height:30px;margin-left:auto;margin-right:auto;float:left;">
						<div style="width:130px;float:left;height:30px;">
							语言:
							<select class="hand" id="duandian_agent">
								<option value="0">全部</option>
								<option value="1">java</option>
								<option value="2">php</option>
								<option value="3">nodejs</option>
								<option value="4">python</option>
								<option value="5">dotnet</option>
								<option value="6">ruby</option>
								<option value="7">android</option>
								<option value="8">ios</option>
								<option value="9">browser</option>
								<option value="10">server</option>
								<option value="11">ci</option>
								<option value="12">plugin</option>
							</select>
						</div>
						<div class="hidden duandian_banben" style="width:150px;float:left;height:30px;">
							版本:
							<select class="hand" id="duandian_banben">
							</select>
						</div>
						<div style="width:140px;float:left;height:30px;margin-left:5px;">
							分类:
							<select id="duandian_group_1" class="hand">
								<option value="0">全部</option>
								<s:iterator value="groups">
									<option value="${id}">${name}</option>
								</s:iterator>
							</select>
						</div>
						<div style="width:160px;float:left;height:30px;" class="duandian_group_2 hidden">
							二级:
							<select id="duandian_group_2" class="hand">
								<option value='0'>全部</option>
							</select>
						</div>
						<div style="width:150px;float:left;height:30px;">
							状态:
							<select class="hand" id="duandian_data_else">
								<option value="0" selected="selected">全部</option>
								<option value="1">下载</option>
								<option value="2">添加应用</option>
								<option value="3">有过数据</option>
							</select>
						</div>
						<div style="width:150px;float:left;height:30px;" class="duandian_data_else_select hidden">
							时间:
							<select class="hand" id="duandian_data_else_select">
								<option value="0" selected="selected">全部</option>
								<option value="1">今日</option>
								<option value="2">昨日</option>
								<option value="3">最近一周</option>
								<option value="4">最近二周</option>
								<option value="5">最近一个月</option>
								<option value="6">自定义时间</option>
							</select>
						</div>
						<div class="hidden duandian_data_else" style="width:160px;float:left;height:30px;font-size:14px;">
							起始时间:
							<input type="text" id="duandian_data_else_start" value="" style="width:80px;"/>
						</div>
						<div class="hidden duandian_data_else" style="width:160px;float:left;height:30px;font-size:14px;">
							结束时间:
							<input type="text" id="duandian_data_else_end" value="" style="width:80px;"/>
						</div>
					</div>
					<div style="margin-top:10px;width:1200px;margin-left:auto;margin-right:auto;float:left;line-height:30px;">
						<div style="width:170px;float:left;height:30px;">
							数据:
							<select class="hand" id="duandian_data_activ">
								<option value="0" selected="selected">全部</option>
								<option value="1">今日</option>
								<option value="2">昨日</option>
								<option value="3">最近一周</option>
								<option value="4">最近二周</option>
								<option value="5">最近一个月</option>
								<option value="6">自定义时间</option>
							</select>
						</div>
						<div class="hidden duandian_zidingyishijian_activ" style="width:160px;float:left;height:30px;font-size:14px;">
							起始时间:
							<input type="text" id="duandian_zidingyishijian_start_activ" value="" style="width:80px;"/>
						</div>
						<div class="hidden duandian_zidingyishijian_activ" style="width:160px;float:left;height:30px;font-size:14px;">
							结束时间:
							<input type="text" id="duandian_zidingyishijian_end_activ" value="" style="width:80px;"/>
						</div>
						<div style="margin-left:20px;width:170px;float:left;height:30px;">
							无数据:
							<select class="hand" id="duandian_data">
								<option value="0" selected="selected">全部</option>
								<option value="1">今日</option>
								<option value="2">昨日</option>
								<option value="3">最近一周</option>
								<option value="4">最近二周</option>
								<option value="5">最近一个月</option>
								<option value="6">自定义时间</option>
							</select>
						</div>
						<div class="hidden duandian_zidingyishijian" style="width:160px;float:left;height:30px;font-size:14px;">
							起始时间:
							<input type="text" id="duandian_zidingyishijian_start" value="" style="width:80px;"/>
						</div>
						<div class="hidden duandian_zidingyishijian" style="width:160px;float:left;height:30px;font-size:14px;">
							结束时间:
							<input type="text" id="duandian_zidingyishijian_end" value="" style="width:80px;"/>
						</div>
						<div style="margin-left:20px;width:170px;float:left;height:30px;" class="hidden duandian_data_duli">
							独立用户:
							<select class="hand" id="duandian_data_duli">
								<option value="0" selected="selected">全部</option>
								<option value="1">java</option>
								<option value="2">php</option>
								<option value="3">nodejs</option>
								<option value="4">python</option>
								<option value="5">dotnet</option>
								<option value="6">ruby</option>
								<option value="7">android</option>
								<option value="8">ios</option>
								<option value="9">browser</option>
								<option value="10">server</option>
								<option value="11">AI</option>
								<option value="12">MI</option>
							</select>
						</div>
					</div>
					<div style="margin-top:10px;width:1200px;margin-left:auto;margin-right:auto;float:left;line-height:30px;">
						<div style="width:170px;float:left;height:30px;">
							登录:
							<select class="hand" id="duandian_login">
								<option value="0" selected="selected">全部</option>
								<option value="1">今日</option>
								<option value="2">昨日</option>
								<option value="3">最近一周</option>
								<option value="4">最近二周</option>
								<option value="5">最近一个月</option>
								<option value="6">自定义时间</option>
							</select>
						</div>
						<div class="hidden duandian_zidingyishijian_login" style="width:160px;float:left;height:30px;font-size:14px;">
							起始时间:
							<input type="text" id="duandian_zidingyishijian_start_login" value="" style="width:80px;"/>
						</div>
						<div class="hidden duandian_zidingyishijian_login" style="width:160px;float:left;height:30px;font-size:14px;">
							结束时间:
							<input type="text" id="duandian_zidingyishijian_end_login" value="" style="width:80px;"/>
						</div>
						<div style="width:75px;float:left;height:30px;">
							<div id="duandian_fuze" style="float:left;width:65px;height:20px;margin:5px 0 0 0;" class="menu_button biankuang_gray_ding hand">我负责的</div>
						</div>
						<div style="hieght:30px;width:75px;float:left;">
							<div id="duandian_chaxun" style="float:left;width:65px;height:20px;margin:5px 0 0 0;" class="menu_button biankuang_gray_ding hand">查询</div>
						</div>
						 <div style="hieght:30px;width:75px;float:left;">
							<div id="export_excel" style="float:left;width:65px;height:20px;margin:5px 0 0 0;" class="menu_button biankuang_gray_ding hand">导出excel</div>
						</div> 
					</div>
					<!-- <div style="float:left;width:1200px;height:30px;">
						<div class="duandian_result_msg" style="height:20px;line-hight:20px;font-size:16px;color:red;"></div>
					</div> -->
				</div>
				<div style="width:1200px;float:left;" class="from hidden">
					<div style="margin-top:20px;width:1200px;margin-left:auto;margin-right:auto;">
						<div style="width:120px;float:left;" class="lable_1" val="1"></div>
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
						<div style="width:100px;float:left;"><input class="from_chaxun hand" type="button" value="查询"/></div>
					</div>
					<div style="margin-top:5px;width:1200px;margin-left:auto;margin-right:auto;float:left;">
						<div style="float:left;height:30px;width:120px;">
							<select id="duandian_zidingyishijian_from">
								<option value="1">所有</option>
								<option value="2">自定义时间</option>
							</select>
						</div>
						<div class="hidden duandian_zidingyishijian_form" style="width:160px;float:left;height:30px;font-size:14px;">
							起始时间:
							<input type="text" id="duandian_zidingyishijian_start_form" value="" style="width:80px;"/>
						</div>
						<div class="hidden duandian_zidingyishijian_form" style="width:160px;float:left;height:30px;font-size:14px;">
							结束时间:
							<input type="text" id="duandian_zidingyishijian_end_form" value="" style="width:80px;"/>
						</div>
					</div>
					<!-- <div style="float:left;width:1200px;height:30px;">
						<div class="duandian_result_msg" style="height:20px;line-hight:20px;font-size:16px;color:red;"></div>
					</div> -->
				</div>
				<div style="width:1200px;float:left;" class="baobiao hidden">
					<div style="margin-top:10px;width:1200px;margin-left:auto;margin-right:auto;float:left;line-height:30px;">
						<div style="width:170px;float:left;height:30px;">
							源数据:
							<select class="hand" id="duandian_baobiao_yuan">
								<option value="0" selected="selected">今天</option>
								<option value="1">昨天</option>
								<option value="2">最近一周</option>
								<option value="3">自定义</option>
							</select>
						</div>
						<div class="hidden duandian_baobiao_yuan" style="width:160px;float:left;height:30px;font-size:14px;">
							起始时间:
							<input type="text" id="duandian_baobiao_yuan_start" value="" style="width:80px;"/>
						</div>
						<div class="hidden duandian_baobiao_yuan" style="width:160px;float:left;height:30px;font-size:14px;">
							结束时间:
							<input type="text" id="duandian_baobiao_yuan_end" value="" style="width:80px;"/>
						</div>
						<div style="width:170px;float:left;height:30px;" class="yuan_liucun_type hidden">
							源数据类型:
							<select class="hand" id="yuan_liucun_type">
								<option value="0" selected="selected">存在</option>
								<option value="1">连续</option>
							</select>
						</div>
					</div>
					<div style="margin-top:10px;width:1200px;margin-left:auto;margin-right:auto;float:left;line-height:30px;">
						<div style="width:170px;float:left;height:30px;">
							报表类型:
							<select class="hand" id="duandian_baobiao_leixing">
								<option value="0" selected="selected">转化</option>
								<option value="1">留存</option>
							</select>
						</div>
						<div style="width:170px;float:left;height:30px;" class="duandian_baobiao_yuyan hidden">
							语言:
							<select class="hand" id="duandian_baobiao_yuyan">
								<option value="0" selected="selected">全部</option>
								<option value="1">java</option>
								<option value="2">php</option>
								<option value="3">nodejs</option>
								<option value="4">python</option>
								<option value="5">dotnet</option>
								<option value="6">ruby</option>
								<option value="7">android</option>
								<option value="8">ios</option>
								<option value="9">browser</option>
								<option value="10">server</option>
								<option value="11">AI</option>
								<option value="12">MI</option>
							</select>
						</div>
					</div>
					<div style="margin-top:10px;width:1200px;margin-left:auto;margin-right:auto;float:left;line-height:30px;">
						<div style="width:160px;float:left;height:30px;">
							坐标:
							<select class="hand" id="duandian_baobiao_zuobiao">
								<option value="0" selected="selected">天</option>
								<option value="1">3天</option>
								<option value="2">周</option>
								<option value="3">月</option>
								<option value="4">自定义</option>
								<option value="5">自定义区间</option>
							</select>
						</div>
						<div class="hidden duandian_baobiao_zuobiao_zidingyi" style="width:210px;float:left;height:30px;">
							自定义坐标天数:
							<input style="width:60px;" class="hand" id="duandian_baobiao_zuobiao_zidingyi" value="0"/>天
						</div>
						<div class="hidden duandian_baobiao_zuobiao" style="width:160px;float:left;height:30px;font-size:14px;">
							起始时间:
							<input type="text" id="duandian_baobiao_zuobiao_start" value="" style="width:80px;"/>
						</div>
						<div class="hidden duandian_baobiao_zuobiao" style="width:160px;float:left;height:30px;font-size:14px;">
							结束时间:
							<input type="text" id="duandian_baobiao_zuobiao_end" value="" style="width:80px;"/>
						</div>
						<div style="width:100px;float:left;"><input class="hand baobiao_chaxun" type="button" value="查询"/></div>
					</div>
				</div>
				<div style="float:left;width:1200px;height:30px;">
					<div class="duandian_result_msg" style="height:20px;line-hight:20px;font-size:16px;color:red;"></div>
				</div>
				<div id="duandian_out" style="width:1200px;min-height:400px;float:left;">
					<table class="tablesorter duandian_list" cellspacing="0"> 
						<thead> 
							<tr> 
								<th style="width:1%"></th>
					  			<th style="width:6%;">ID</th> 
					  			<th style="width:14%;">公司</th>
					  			<th style="width:10%;">语言</th>
					  			<th style="width:7%">Coming</th>
					  			
					  			<th style="width:6%">区域</th>
					  			<th style="width:6%">融资</th>
					  			<th style="width:6%">分类</th>
					  			<th style="width:6%">规模</th>
					  			<th style="width:6%">平台</th>
					  			
					  			<th style="width:6%;">销售</th>
					  			<th style="width:6%;">售前</th>
					  			<th style="width:6%;">运营</th>
					  			<th style="width:6%">Edit</th>
					  			<th style="width:1%"></th>
							</tr> 
						</thead> 
						<tbody id="duandian_result" style="font-size:12px;"> 
						</tbody> 
					</table>
					<div style="width:1200px;height:200px;" class="hidden duandian_list_baobiao">
					</div>
					<div class="hidden back_to_baobiao" style="width:1200px;height:30px;line-height:30px;"><input type="button" value="返回" id="back_to_baobiao"/></div>
					<table class="tablesorter duandian_list_baobiao_chazhi hidden" cellspacing="0"> 
						<thead> 
							<tr> 
								<th style="width:1%"></th>
					  			<th style="width:6%;">ID</th> 
					  			<th style="width:14%;">公司</th>
					  			<th style="width:10%;">语言</th>
					  			<th style="width:7%">Coming</th>
					  			
					  			<th style="width:6%">区域</th>
					  			<th style="width:6%">融资</th>
					  			<th style="width:6%">分类</th>
					  			<th style="width:6%">规模</th>
					  			<th style="width:6%">平台</th>
					  			
					  			<th style="width:6%;">销售</th>
					  			<th style="width:6%;">售前</th>
					  			<th style="width:6%;">运营</th>
					  			<th style="width:6%">Edit</th>
					  			<th style="width:1%"></th>
							</tr> 
						</thead> 
						<tbody id="duandian_list_baobiao_chazhi" style="font-size:12px;"> 
						</tbody> 
					</table>
				</div>
			</div>
	</div>
	<div class="clear"></div>
	<div class="spacer"></div>
	<div class="hidden totalPage">${totalPage}</div>
	<div class="hidden nowPage">${nowPage}</div>
	<div class="hidden nowId">${id}</div>
	</section>

	<script src="${applicationScope.staticPath}skin/js/hideshow.js" type="text/javascript"></script>
	<script src="${applicationScope.staticPath}skin/js/jquery.tablesorter.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/jquery.equalHeight.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/index.js"></script>
</body>
<script>
$("#export_excel").click(function(){	
	 var arr=[];
	 $("#export_excel").html("导出中");
	jQuery("#duandian_result tr").each(function() {
		var id = $(this).attr("id");
		arr.push(id+"");
		});
	if(arr.length==0){
		alert("无可导数据，请查询后导出");
		 $("#export_excel").html("导出excel");
		return false;
	}
	var stringInt = "";
	for(var i=0;i<arr.length;i++){
	    stringInt = stringInt+arr[i]+",";//连接符其实可以换的
	}
	stringInt = stringInt.substring(0,stringInt.length-1);
	  $.ajax({
		 	type:"post",
			dataType:'json',
			url:'info_exportExcel.action',
			data:{"ids":stringInt}
		}).done(function(data){
				 $("#export_excel").html("导出excel");
		})  
})</script>
</html>