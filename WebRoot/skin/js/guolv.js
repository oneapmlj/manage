var fuze = 0;
var father = 0; 
var lable_list = 1;
$(document).ready(function() {
	$(".go_from").live('click', function(){
		$(".go_guolv_ding").addClass("go_guolv");
		$(".go_guolv").removeClass("go_guolv_ding");
		$(".go_guolv").removeClass("biankuang_blue_ding");
		$(".go_guolv").addClass("biankuang_gray");
		
		$(".go_from").addClass("go_from_ding");
		$(".go_from_ding").removeClass("go_from");
		$(".go_from_ding").removeClass("biankuang_gray");
		$(".go_from_ding").addClass("biankuang_blue_ding");
		
		$(".go_baobiao_ding").addClass("go_baobiao");
		$(".go_baobiao").removeClass("go_baobiao_ding");
		$(".go_baobiao").removeClass("biankuang_blue_ding");
		$(".go_baobiao").addClass("biankuang_gray");
		
		$(".guolv").addClass("hidden");
		$(".from").removeClass("hidden");
		$(".baobiao").addClass("hidden");
		
		$(".duandian_list").removeClass("hidden");
		$(".duandian_list_baobiao").addClass("hidden");
		$(".duandian_result_msg").html("");
		$.ajax({
			dataType:'json',
			url:'lable_lable.action',
			data:{lableId:0}
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
				}
			}else{
				alert(da.msg)
			}
		});
	});
	$(".go_guolv").live('click', function(){
		$(".go_guolv").addClass("go_guolv_ding");
		$(".go_guolv_ding").removeClass("go_guolv");
		$(".go_guolv_ding").removeClass("biankuang_gray");
		$(".go_guolv_ding").addClass("biankuang_blue_ding");
		
		$(".go_from_ding").addClass("go_from");
		$(".go_from").removeClass("go_from_ding");
		$(".go_from").removeClass("biankuang_blue_ding");
		$(".go_from").addClass("biankuang_gray");
		
		$(".go_baobiao_ding").addClass("go_baobiao");
		$(".go_baobiao").removeClass("go_baobiao_ding");
		$(".go_baobiao").removeClass("biankuang_blue_ding");
		$(".go_baobiao").addClass("biankuang_gray");
		
		$(".guolv").removeClass("hidden");
		$(".from").addClass("hidden");
		$(".baobiao").addClass("hidden");
		$(".duandian_list").removeClass("hidden");
		$(".duandian_list_baobiao").addClass("hidden");
		$(".duandian_result_msg").html("");
	});
	$(".go_baobiao").live('click', function(){
		$(".go_baobiao").addClass("go_baobiao_ding");
		$(".go_baobiao_ding").removeClass("go_baobiao");
		$(".go_baobiao_ding").removeClass("biankuang_gray");
		$(".go_baobiao_ding").addClass("biankuang_blue_ding");
		
		$(".go_guolv_ding").addClass("go_guolv");
		$(".go_guolv").removeClass("go_guolv_ding");
		$(".go_guolv").removeClass("biankuang_blue_ding");
		$(".go_guolv").addClass("biankuang_gray");
		
		$(".go_from_ding").addClass("go_from");
		$(".go_from").removeClass("go_from_ding");
		$(".go_from").removeClass("biankuang_blue_ding");
		$(".go_from").addClass("biankuang_gray");
		
		$(".guolv").addClass("hidden");
		$(".from").addClass("hidden");
		$(".baobiao").removeClass("hidden");
		$(".duandian_list").addClass("hidden");
		$(".duandian_list_baobiao").removeClass("hidden");
		$(".duandian_result_msg").html("");
	});
	$(".from_chaxun").live('click', function(){
		if(father <= 0){
			alert("至少选一个来源分类");
			return;
		}
		$(".from_chaxun").html("查询中");
		$("#duandian_result").html("");
		$(".duandian_result_msg").html("");
		$(".from_chaxun").val("查询中");
		$(".from_chaxun").addClass("from_chaxun_ding");
		$(".from_chaxun_ding").removeClass("from_chaxun");
		$.ajax({
			dataType:'json',
			url:'duandian_chaxun.action',
			data:{fatherId:father}
		}).done(function(da){
			if(da.status == 1){
				var html = "";
				for(var i=0;i<da.groups.length;i++){
					html += "<tr id='"+da.groups[i].groupId+"'>" 
					+"<td></td>"
					+"<td>"+da.groups[i].groupId+"</td>"
					+"<td>"+da.groups[i].name+"</td>";
				if(da.groups[i].project != null){
					html += "<td>"+da.groups[i].project+"</td>";
				}else{
					html += "<td>"+da.groups[i].company+"</td>";
				}
				html += "<td>"+da.groups[i].language+"</td>"
						+"<td>"+da.groups[i].comming+"</td>";
				if(da.groups[i].tag != null){
					html += "<td>"+da.groups[i].tag.province+"</td>"
								+"<td>"+da.groups[i].tag.rongzi+"</td>"
								+"<td>"+da.groups[i].tag.category+"</td>"
								+"<td>"+da.groups[i].tag.person+"</td>"
								+"<td>"+da.groups[i].tag.fuwuqi+"</td>"
				}else{
					html += "<td>未知</td><td>未知</td><td>未知</td><td>未知</td><td>未知</td>";
				}
					html += "<td>"+da.groups[i].saleName+"</td>"
							+"<td>"+da.groups[i].preSaleName+"</td>"
							+"<td>"+da.groups[i].supportName+"</td>"
							+"<td><input val1='"+da.groups[i].groupId+"' class='check_view'  type='image' src='http://manage.oneapm.com/skin/images/icn_view_users.png' title='查看' /></td>"
							+"<td></td>"
							+"</tr>";
				}
				$("#duandian_result").html(html);
				$(".duandian_result_msg").html("总数："+da.infos.length);
				$(".from_chaxun_ding").val("查询");
				$(".from_chaxun_ding").addClass("from_chaxun");
				$(".from_chaxun").removeClass("from_chaxun_ding");
			}else{
				alert(da.msg);
				$(".from_chaxun_ding").html("查询");
				$(".from_chaxun_ding").addClass("from_chaxun");
				$(".from_chaxun").removeClass("from_chaxun_ding");
			}
		});
	});
	$(".baobiao_chaxun").live("click", function(){
		var yuan = $("#duandian_baobiao_yuan").val();
		var yuan_start = $("#duandian_baobiao_yuan_start").val();
		var yuan_end = $("#duandian_baobiao_yuan_end").val();
		var agent = $("#duandian_baobiao_yuyan").val();
		var leixing = $("#duandian_baobiao_leixing").val();
		var zuobiao = $("#duandian_baobiao_zuobiao").val();
		var zuobiao_zidingyi = $("#duandian_baobiao_zuobiao_zidingyi").val();
		var zuobiao_start = $("#duandian_baobiao_zuobiao_start").val();
		var zuobiao_end = $("#duandian_baobiao_zuobiao_end").val();
		var yuan_type = $("#yuan_liucun_type").val();
		$(".baobiao_chaxun").val("查询中");
		$(".baobiao_chaxun").addClass("baobiao_chaxun_ing");
		$(".baobiao_chaxun_ing").removeClass("baobiao_chaxun");
		if(yuan == 0 && leixing == 1){
			alert("不能查看今日的留存，请更改查询类型或者数据源");
			return;
		}
		$.ajax({
			dataType:'json',
			url:'duandian_chaxun_baobiao.action',
			data:{yuan:yuan,yuanStart:yuan_start,yuanEnd:yuan_end,agent:agent,leixing:leixing,zuobiao:zuobiao,zuobiaoZidingyi:
				zuobiao_zidingyi,zuobiaoStart:zuobiao_start,zuobiaoEnd:zuobiao_end,yuanType:yuan_type}
		}).done(function(da){
			$(".baobiao_chaxun_ing").val("查询");
			$(".baobiao_chaxun_ing").addClass("baobiao_chaxun");
			$(".baobiao_chaxun").removeClass("baobiao_chaxun_ing");
			if(da.status == '1'){
				//转化
				if(leixing == 0){
					var baobiao = da.baobiao;
					var html = "<div style='width:1200px;float:left;margin-top:5px;'><div style='float:left;width:1140px;'>";
					if(baobiao.zuobiao == 0){
						html += "<div style='float:left;width:100px;' class='border_all'>"
							+"<div style='width:100px;height:28px;text-align:center;font-size:14px;line-height:28px;'></div>"
							+"<div class='border_top' style='width:100px;height:28px;text-align:center;font-size:14px;line-height:28px;'>时间</div>"
							+"<div class='border_top' style='width:100px;height:41px;text-align:center;font-size:14px;line-height:20px;'>"
							+"<div class='left' style='float:left;width:50px;height:41px;line-height:40px;'>下载</div>"	
							+"<div class='border_left' style='float:left;width:49px;height:20px;line-height:20px;'>区间</div>"	
							+"<div class='border_left border_top' style='float:left;width:49px;height:20px;line-height:20px;'>总计</div>"	
							+"</div>"
							+"<div class='border_top' style='width:100px;height:41px;text-align:center;font-size:14px;line-height:20px;'>"
							+"<div class='left' style='float:left;width:50px;height:41px;line-height:40px;'>应用</div>"	
							+"<div class='border_left' style='float:left;width:49px;height:20px;line-height:20px;'>区间</div>"	
							+"<div class='border_left border_top' style='float:left;width:49px;height:20px;line-height:20px;'>总计</div>"	
							+"</div>"
							+"</div>"
							+"<div style='float:left;width:140px;' class='border_right_all'>"
							+"<div style='width:140px;height:28px;text-align:center;font-size:14px;line-height:28px;'>源数据</div>"
							+"<div class='border_top' style='width:140px;height:14px;text-align:center;font-size:12px;line-height:14px;'>"+baobiao.datas[0].time_start+"</div>"
							+"<div style='width:140px;height:14px;text-align:center;font-size:12px;line-height:14px;'>"+baobiao.datas[0].time_end+"</div>"
							+"<div class='border_top' style='width:140px;height:83px;text-align:center;font-size:12px;line-height:83px;'>"
							+"<div class='left' style='width:59px;height:83px;text-align:center;font-size:14px;line-height:83px;'>"+baobiao.base+"</div>"
							+"<div class='border_left left' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+baobiao.datas[0].download_qujian+"</div>"
							+"<div class='border_left border_top left' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+baobiao.datas[0].download_zong+"</div>"
							+"<div class='border_left border_top left' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+baobiao.datas[0].app_qujian+"</div>"
							+"<div class='border_left border_top left' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+baobiao.datas[0].app_zong+"</div>"
							+"</div>"
							+"</div>"
							+"<div style='float:left;width:80px;' class='border_right_all'>"
							+"<div style='width:80px;height:28px;text-align:center;font-size:14px;line-height:28px;'>1</div>"
							+"<div class='border_top' style='width:80px;height:14px;text-align:center;font-size:12px;line-height:14px;'>"+baobiao.datas[1].time_start+"</div>"
							+"<div style='width:80px;height:14px;text-align:center;font-size:12px;line-height:14px;'>"+baobiao.datas[1].time_end+"</div>"
							+"<div class='border_top' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+baobiao.datas[1].download_qujian+"</div>"
							+"<div class='border_top' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+baobiao.datas[1].download_zong+"</div>"
							+"<div class='border_top' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+baobiao.datas[1].app_qujian+"</div>"
							+"<div class='border_top' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+baobiao.datas[1].app_zong+"</div>"
							+"</div>";
					}else{
						html += "<div style='float:left;width:100px;' class='border_all'>"
							+"<div style='width:100px;height:28px;text-align:center;font-size:14px;line-height:28px;'></div>"
							+"<div class='border_top' style='width:100px;height:28px;text-align:center;font-size:14px;line-height:28px;'>时间</div>"
							+"<div class='border_top' style='width:100px;height:41px;text-align:center;font-size:14px;line-height:20px;'>"
							+"<div class='left' style='float:left;width:50px;height:41px;line-height:40px;'>下载</div>"	
							+"<div class='border_left' style='float:left;width:49px;height:20px;line-height:20px;'>区间</div>"	
							+"<div class='border_left border_top' style='float:left;width:49px;height:20px;line-height:20px;'>总计</div>"	
							+"</div>"
							+"<div class='border_top' style='width:100px;height:41px;text-align:center;font-size:14px;line-height:20px;'>"
							+"<div class='left' style='float:left;width:50px;height:41px;line-height:40px;'>应用</div>"	
							+"<div class='border_left' style='float:left;width:49px;height:20px;line-height:20px;'>区间</div>"	
							+"<div class='border_left border_top' style='float:left;width:49px;height:20px;line-height:20px;'>总计</div>"	
							+"</div>"
							+"</div>"
							+"<div style='float:left;width:140px;' class='border_right_all'>"
							+"<div style='width:140px;height:28px;text-align:center;font-size:14px;line-height:28px;'>源数据</div>"
							+"<div class='border_top' style='width:140px;height:14px;text-align:center;font-size:12px;line-height:14px;'>"+baobiao.datas[0].time_start+"</div>"
							+"<div style='width:140px;height:14px;text-align:center;font-size:12px;line-height:14px;'>"+baobiao.datas[0].time_end+"</div>"
							+"<div class='border_top' style='width:140px;height:83px;text-align:center;font-size:12px;line-height:83px;'>"
							+"<div class='left' style='width:59px;height:83px;text-align:center;font-size:14px;line-height:83px;'>"+baobiao.base+"</div>"
							+"<div class='border_left left' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+baobiao.datas[0].download_qujian+"</div>"
							+"<div class='border_left border_top left' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+baobiao.datas[0].download_zong+"</div>"
							+"<div class='border_left border_top left' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+baobiao.datas[0].app_qujian+"</div>"
							+"<div class='border_left border_top left' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+baobiao.datas[0].app_zong+"</div>"
							+"</div>"
							+"</div>";
						if(baobiao.datas.length > 1){
							var datas = baobiao.datas;
							for(var i=1;i<datas.length;i++){
								html += "<div style='float:left;width:80px;' class='border_right_all'>"
									+"<div style='width:80px;height:28px;text-align:center;font-size:14px;line-height:28px;'>"+i+"</div>"
									+"<div class='border_top' style='width:80px;height:14px;text-align:center;font-size:12px;line-height:14px;'>"+datas[i].time_start+"</div>"
									+"<div style='width:80px;height:14px;text-align:center;font-size:12px;line-height:14px;'>"+datas[i].time_end+"</div>"
									+"<div class='border_top' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+datas[i].download_qujian+"</div>"
									+"<div class='border_top' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+datas[i].download_zong+"</div>"
									+"<div class='border_top' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+datas[i].app_qujian+"</div>"
									+"<div class='border_top' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+datas[i].app_zong+"</div>"
									+"</div>";
							}
						}
					}
					html = html+"</div><div style='float:left;width:60px;height:148px;line-height:188px;text-align:center;'>" +
					"<input type='button' value='移除' class='hand baobiao_result_remove'/>" +
					"<input type='button' start='"+baobiao.datas[0].time_start+"' end='"+baobiao.datas[0].time_end+"' value='差值' class='hand baobiao_result_chazhi'/></div></div>";
					var html1 = $(".duandian_list_baobiao").html();
					$(".duandian_list_baobiao").html(html+html1);
				}
				//留存
				if(da.leixing == 1){
					var baobiao = da.baobiao;
					var html = "<div style='width:1200px;float:left;margin-top:5px;'><div style='float:left;width:1140px;'>";
					if(baobiao.zuobiao == 0){
						html += "<div style='float:left;width:60px;' class='border_all'>"
							+"<div style='width:60px;height:28px;text-align:center;font-size:14px;line-height:28px;'></div>"
							+"<div class='border_top' style='width:60px;height:28px;text-align:center;font-size:14px;line-height:28px;'>时间</div>"
							+"<div class='border_top' style='width:60px;height:20px;text-align:center;line-height:20px;'>持续</div>"	
							+"<div class='border_top' style='width:60px;height:20px;text-align:center;line-height:20px;'>连续</div>"	
							+"<div class='border_top' style='width:60px;height:20px;text-align:center;line-height:20px;'>存在</div>"	
							+"</div>"
							+"<div style='float:left;width:140px;' class='border_right_all'>"
							+"<div style='width:140px;height:28px;text-align:center;font-size:14px;line-height:28px;'>源数据</div>"
							+"<div class='border_top' style='width:140px;height:14px;text-align:center;font-size:12px;line-height:14px;'>"+baobiao.datas[0].time_start+"</div>"
							+"<div style='width:140px;height:14px;text-align:center;font-size:12px;line-height:14px;'>"+baobiao.datas[0].time_end+"</div>"
							+"<div class='border_top' style='width:140px;height:62px;text-align:center;font-size:12px;line-height:62px;'>"
							+"<div class='left' style='width:59px;height:62px;text-align:center;font-size:14px;line-height:62px;'>"+baobiao.base+"</div>"
							+"<div class='border_left left' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>---</div>"
							+"<div class='border_left border_top left' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>---</div>"
							+"<div class='border_left border_top left' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+baobiao.datas[0].cunzai+"</div>"
							+"</div>"
							+"</div>"
							+"<div style='float:left;width:80px;' class='border_right_all'>"
							+"<div style='width:80px;height:28px;text-align:center;font-size:14px;line-height:28px;'>1</div>"
							+"<div class='border_top' style='width:80px;height:14px;text-align:center;font-size:12px;line-height:14px;'>"+baobiao.datas[1].time_start+"</div>"
							+"<div style='width:80px;height:14px;text-align:center;font-size:12px;line-height:14px;'>"+baobiao.datas[1].time_end+"</div>"
							+"<div class='border_top' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+baobiao.datas[1].chixu+"</div>"
							+"<div class='border_top' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+baobiao.datas[1].lianxu+"</div>"
							+"<div class='border_top' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+baobiao.datas[1].cunzai+"</div>"
							+"</div>";
					}else{
						html += "<div style='float:left;width:60px;' class='border_all'>"
							+"<div style='width:60px;height:28px;text-align:center;font-size:14px;line-height:28px;'></div>"
							+"<div class='border_top' style='width:60px;height:28px;text-align:center;font-size:14px;line-height:28px;'>时间</div>"
							+"<div class='border_top' style='width:60px;height:20px;text-align:center;line-height:20px;'>持续</div>"	
							+"<div class='border_top' style='width:60px;height:20px;text-align:center;line-height:20px;'>连续</div>"	
							+"<div class='border_top' style='width:60px;height:20px;text-align:center;line-height:20px;'>存在</div>"	
							+"</div>"
							+"<div style='float:left;width:140px;' class='border_right_all'>"
							+"<div style='width:140px;height:28px;text-align:center;font-size:14px;line-height:28px;'>源数据</div>"
							+"<div class='border_top' style='width:140px;height:14px;text-align:center;font-size:12px;line-height:14px;'>"+baobiao.datas[0].time_start+"</div>"
							+"<div style='width:140px;height:14px;text-align:center;font-size:12px;line-height:14px;'>"+baobiao.datas[0].time_end+"</div>"
							+"<div class='border_top' style='width:140px;height:62px;text-align:center;font-size:12px;line-height:62px;'>"
							+"<div class='left' style='width:59px;height:62px;text-align:center;font-size:14px;line-height:62px;'>"+baobiao.base+"</div>"
							+"<div class='border_left left' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>---</div>"
							+"<div class='border_left border_top left' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>---</div>"
							+"<div class='border_left border_top left' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+baobiao.datas[0].cunzai+"</div>"
							+"</div>"
							+"</div>"
						if(baobiao.datas.length > 1){
							var datas = baobiao.datas;
							for(var i=1;i<datas.length;i++){
								html += "<div style='float:left;width:80px;' class='border_right_all'>"
									+"<div style='width:80px;height:28px;text-align:center;font-size:14px;line-height:28px;'>"+i+"</div>"
									+"<div class='border_top' style='width:80px;height:14px;text-align:center;font-size:12px;line-height:14px;'>"+datas[i].time_start+"</div>"
									+"<div style='width:80px;height:14px;text-align:center;font-size:12px;line-height:14px;'>"+datas[i].time_end+"</div>"
									+"<div class='border_top' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+datas[i].chixu+"</div>"
									+"<div class='border_top' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+datas[i].lianxu+"</div>"
									+"<div class='border_top' style='width:80px;height:20px;text-align:center;font-size:14px;line-height:20px;'>"+datas[i].cunzai+"</div>"
									+"</div>";
							}
						}
					}
					html = html+"</div><div style='float:left;width:60px;height:148px;line-height:188px;text-align:center;'>" +
					"<input type='button' value='移除' class='hand baobiao_result_remove'/>"
					+"<input type='button' start='"+baobiao.datas[0].time_start+"' end='"+baobiao.datas[0].time_end+"' value='差值' class='hand baobiao_result_chazhi_liucun'/>"
					+"</div></div>";
					var html1 = $(".duandian_list_baobiao").html();
					$(".duandian_list_baobiao").html(html+html1);
				}
			}else{
				alert(da.msg);
			}
			
		});
	});
	$(".baobiao_result_remove").live("click", function(){
		$(this).parent().parent().remove();
	});
	$(".baobiao_result_chazhi").live("click", function(){
		var start = $(this).attr('start');
		var end = $(this).attr('end');
		$.ajax({
			dataType:'json',
			url:'duandian_chaxun_baobiao_view.action',
			data:{yuanStart:start,yuanEnd:end}
		}).done(function(da){
			if(da.status == 1){
				var html = "";
				for(var i=0;i<da.groups.length;i++){
					html += "<tr id='"+da.groups[i].groupId+"'>" 
					+"<td></td>"
					+"<td>"+da.groups[i].groupId+"</td>"
					+"<td>"+da.groups[i].name+"</td>";
				if(da.groups[i].project != null){
					html += "<td>"+da.groups[i].project+"</td>";
				}else{
					html += "<td>"+da.groups[i].company+"</td>";
				}
				html += "<td>"+da.groups[i].language+"</td>"
						+"<td>"+da.groups[i].comming+"</td>";
				if(da.groups[i].tag != null){
					html += "<td>"+da.groups[i].tag.province+"</td>"
								+"<td>"+da.groups[i].tag.rongzi+"</td>"
								+"<td>"+da.groups[i].tag.category+"</td>"
								+"<td>"+da.groups[i].tag.person+"</td>"
								+"<td>"+da.groups[i].tag.fuwuqi+"</td>"
				}else{
					html += "<td>未知</td><td>未知</td><td>未知</td><td>未知</td><td>未知</td>";
				}
					html += "<td>"+da.groups[i].saleName+"</td>"
							+"<td>"+da.groups[i].preSaleName+"</td>"
							+"<td>"+da.groups[i].supportName+"</td>"
							+"<td><input val1='"+da.groups[i].groupId+"' class='check_view'  type='image' src='http://manage.oneapm.com/skin/images/icn_view_users.png' title='查看' /></td>"
							+"<td></td>"
							+"</tr>";
				}
				$("#duandian_list_baobiao_chazhi").html(html);
				$(".duandian_result_msg").html("总数："+da.infos.length);
				$(".duandian_list_baobiao").addClass("hidden");
				$(".duandian_list_baobiao_chazhi").removeClass("hidden");
				$(".back_to_baobiao").removeClass("hidden");
			}else{
				alert(da.msg);
			}
		});
	});
	$("#back_to_baobiao").live("click", function(){
		$(".back_to_baobiao").addClass("hidden");
		$("#duandian_list_baobiao_chazhi").html("");
		$(".duandian_result_msg").html("");
		$(".duandian_list_baobiao_chazhi").addClass("hidden");
		$(".duandian_list_baobiao").removeClass("hidden");
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
				}else{
					alert(da.msg)
				}
			});
		}else{
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
	
	$("#duandian_baobiao_yuan").live("change", function(){
		var id = $(this).val();
		if(id== 3){
			$(".duandian_baobiao_yuan").removeClass("hidden");
		}else{
			if(id == 0){
				var leixing = $("#duandian_baobiao_leixing").val();
				if(leixing == 1){
					alert("无法查看今天的留存数据");
					$("#duandian_baobiao_yuan").val(1);
					return;
				}
			}
			$(".duandian_baobiao_yuan").addClass("hidden");
		}
	});
	$("#duandian_baobiao_zuobiao").live("change", function(){
		var id= $(this).val();
		if(id==4){
			$(".duandian_baobiao_zuobiao_zidingyi").removeClass("hidden");
			$(".duandian_baobiao_zuobiao").addClass("hidden");
		}else{
			if(id==5){
				$(".duandian_baobiao_zuobiao_zidingyi").addClass("hidden");
				$(".duandian_baobiao_zuobiao").removeClass("hidden");
			}else{
				$(".duandian_baobiao_zuobiao_zidingyi").addClass("hidden");
				$(".duandian_baobiao_zuobiao").addClass("hidden");
			}
		}
	});
	$("#duandian_baobiao_leixing").live("change", function(){
		var id= $(this).val();
		if(id==1){
			var yuan = $("#duandian_baobiao_yuan").val();
			if(yuan == 0){
				alert("无法查看今天的留存数据！！");
				$("#duandian_baobiao_leixing").val(0);
				return;
			}
			$(".duandian_baobiao_yuyan").removeClass("hidden");
			$(".yuan_liucun_type").removeClass("hidden");
		}else{
			if(id == 0){
				$(".duandian_baobiao_yuyan").addClass("hidden");
				$(".yuan_liucun_type").addClass("hidden");
			}else{
				$(".duandian_baobiao_yuyan").addClass("hidden");
				$(".yuan_liucun_type").addClass("hidden");
			}
		}
	});
	$("#duandian_group_1").live('click', function(){
		var id = $("#duandian_group_1").val();
		if(id > 0){
			$.ajax({
				dataType:'json',
				url:'group_group.action',
				data:{groupId:id}
			}).done(function(da){
				if(da.status == 1){
					if(da.groups.length > 0){
						var html = "<option value='0'>全部</option>";
						for(var i=0;i<da.groups.length;i++){
							html += "<option value='"+da.groups[i].id+"'>"+da.groups[i].name+"</option>";
						}
						$("#duandian_group_2").html(html);
						$(".duandian_group_2").removeClass("hidden");
					}else{
						$("#duandian_group_2").html("<option value='0'>全部</option>");
						$(".duandian_group_2").addClass("hidden");
					}
				}else{
					alert(alert(da.msg));
				}
			});
		}else{
			$("#duandian_group_2").html("<option value='0'>全部</option>");
			$(".duandian_group_2").addClass("hidden");
		}
	});	
	$("#duandian_zidingyishijian_start_login").live("click", function(){
		laydate({
		    elem: '#duandian_zidingyishijian_start_login',
		    max: laydate.now(), //+1代表明天，+2代表后天，以此类推
		    format: 'YYYY-MM-DD',
		});
	});
	$("#duandian_zidingyishijian_end_login").live("click", function(){
		laydate({
		    elem: '#duandian_zidingyishijian_end_login',
		    max: laydate.now(), //+1代表明天，+2代表后天，以此类推
		    format: 'YYYY-MM-DD',
		});
	});
	$("#duandian_baobiao_yuan_start").live("click", function(){
		laydate({
		    elem: '#duandian_baobiao_yuan_start',
		    max: laydate.now(), //+1代表明天，+2代表后天，以此类推
		    format: 'YYYY-MM-DD',
		});
	});
	$("#duandian_baobiao_yuan_end").live("click", function(){
		laydate({
		    elem: '#duandian_baobiao_yuan_end',
		    max: laydate.now(), //+1代表明天，+2代表后天，以此类推
		    format: 'YYYY-MM-DD',
		});
	});
	$("#duandian_baobiao_zuobiao_start").live("click", function(){
		laydate({
		    elem: '#duandian_baobiao_zuobiao_start',
		    max: laydate.now(), //+1代表明天，+2代表后天，以此类推
		    format: 'YYYY-MM-DD',
		});
	});
	$("#duandian_baobiao_zuobiao_end").live("click", function(){
		laydate({
		    elem: '#duandian_baobiao_zuobiao_end',
		    max: laydate.now(), //+1代表明天，+2代表后天，以此类推
		    format: 'YYYY-MM-DD',
		});
	});
	$("#duandian_zidingyishijian_start").live("click", function(){
		laydate({
		    elem: '#duandian_zidingyishijian_start',
		    max: laydate.now(), //+1代表明天，+2代表后天，以此类推
		    format: 'YYYY-MM-DD',
		});
	});
	$("#duandian_zidingyishijian_end").live("click", function(){
		laydate({
		    elem: '#duandian_zidingyishijian_end',
		    format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
		    max: laydate.now(), //+1代表明天，+2代表后天，以此类推
		    festival: true, //显示节日
		});
	});
	$("#duandian_zidingyishijian_start_activ").live("click", function(){
		laydate({
		    elem: '#duandian_zidingyishijian_start_activ',
		    max: laydate.now(), //+1代表明天，+2代表后天，以此类推
		    format: 'YYYY-MM-DD',
		});
	});
	$("#duandian_zidingyishijian_end_activ").live("click", function(){
		laydate({
		    elem: '#duandian_zidingyishijian_end_activ',
		    format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
		    max: laydate.now(), //+1代表明天，+2代表后天，以此类推
		    festival: true, //显示节日
		});
	});
	$("#duandian_guanbi").live('click', function(){
		if(guanbi == 0){
			guanbi = 1;
			$(this).removeClass("biankuang_gray_ding");
			$(this).addClass("biankuang_blue_ding");
		}else{
			guanbi = 0;
			$(this).removeClass("biankuang_blue_ding");
			$(this).addClass("biankuang_gray_ding");
		}
	});
	
	$("#duandian_fuze").live('click', function(){
		if(fuze == 0){
			fuze = 1;
			$(this).removeClass("biankuang_gray_ding");
			$(this).addClass("biankuang_blue_ding");
		}else{
			fuze = 0;
			$(this).removeClass("biankuang_blue_ding");
			$(this).addClass("biankuang_gray_ding");
		}
	});
	
	$("#duandian_jinri").live('click', function(){
		if(jinri == 0){
			jinri = 1;
			$(this).removeClass("biankuang_gray_ding");
			$(this).addClass("biankuang_blue_ding");
		}else{
			jinri = 0;
			$(this).removeClass("biankuang_blue_ding");
			$(this).addClass("biankuang_gray_ding");
		}
	})
	
	$("#duandian_agent").live('change', function(){
		var agent = $("#duandian_agent").val();
			if(agent == 9){
				var html = "<option value='0'>全部</option>";
				html += "<option value='100'>独立部署</option>";
				html += "<option value='101'>AI注入</option>";
				html += "<option value='1'>java注入</option>";
				html += "<option value='2'>php注入</option>";
				html += "<option value='3'>nodejs注入</option>";
				html += "<option value='4'>python注入</option>";
				html += "<option value='5'>dotnet注入</option>";
				html += "<option value='6'>ruby注入</option>";
				$("#duandian_banben").html(html);
				$(".duandian_banben").removeClass("hidden");
			}else{
				$.ajax({
					dataType:'json',
					url:'duandian_versions.action',
					data:{agent:agent}
				}).done(function(da){
					if(da.status == 1){
						var html = "<option value='null'>全部</option>";
						for(var i=0;i<da.versions.length;i++){
							html += "<option value='"+da.versions[i]+"'>"+da.versions[i]+"</option>";
						}
						$("#duandian_banben").html(html);
						$(".duandian_banben").removeClass("hidden");
					}else{
						alert(alert(da.msg));
					}
				});
			}
	});
	$("#duandian_data").live("change", function(){
		var id = $(this).val();
		if(id == 6){
			$("#duandian_zidingyishijian_start").val("");
			$("#duandian_zidingyishijian_end").val("");
			$(".duandian_zidingyishijian").removeClass("hidden");
		}else{
			$("#duandian_zidingyishijian").val("");
			$(".duandian_zidingyishijian").addClass("hidden");
		}
	});
	$("#duandian_data_activ").live("change", function(){
		var id = $(this).val();
		if(id == 6){
			$("#duandian_zidingyishijian_start_activ").val("");
			$("#duandian_zidingyishijian_end_activ").val("");
			$(".duandian_zidingyishijian_activ").removeClass("hidden");
		}else{
			$("#duandian_zidingyishijian_activ").val("");
			$(".duandian_zidingyishijian_activ").addClass("hidden");
		}
		if(id > 0){
			$(".duandian_data_duli").removeClass("hidden");
		}else{
			$(".duandian_data_duli").addClass("hidden");
		}
	});
	$("#duandian_data_else").live("change", function(){
		var id = $(this).val();
		if(id > 0 && id <3){
			$(".duandian_data_else_select").removeClass("hidden");
		}else{
			$(".duandian_data_else_select").addClass("hidden");
			$(".duandian_data_else").addClass("hidden");
			$("#duandian_data_else_select").val("0");
		}
	});
	$("#duandian_data_else_select").live("change", function(){
		var id = $(this).val();
		if(id ==6){
			$("#duandian_data_else_start").val("");
			$("#duandian_data_else_end").val("");
			$(".duandian_data_else").removeClass("hidden");
		}else{
			$(".duandian_data_else").addClass("hidden");
		}
	});
	$("#duandian_chaxun").live('click', function(){
		$("#duandian_chaxun").html("查询中");
		$("#duandian_result").html("");
		$(".duandian_result_msg").html("");
		var paixu = $("#paixu").val();
		var agent = $("#duandian_agent").val();
		var banben = $("#duandian_banben").val();
		var groupId1 = $("#duandian_group_1").val();
		var groupId2 = $("#duandian_group_2").val();
		var caozuo = $("#duandian_data_else").val();
		var caozuo_time = $("#duandian_data_else_select").val();
		var caozuo_start = $("#duandian_data_else_start").val();
		var caozuo_end = $("#duandian_data_else_end").val();
		
		var data = $("#duandian_data_activ").val();
		var data_start = $("#duandian_zidingyishijian_start_activ").val();
		var data_end = $("#duandian_zidingyishijian_end_activ").val();
		var nodata = $("#duandian_data").val();
		var nodata_start = $("#duandian_zidingyishijian_start").val();
		var nodata_end = $("#duandian_zidingyishijian_end").val();
		var duli = $("#duandian_data_duli").val();
		var login = $("#duandian_login").val();
		var login_start = $("#duandian_zidingyishijian_start_login").val();
		var login_end = $("#duandian_zidingyishijian_end_login").val();
		
		$.ajax({
			dataType:'json',
			url:'duandian_chaxun.action',
			data:{agent:agent,banben:banben,fuze:fuze,groupId1:groupId1,groupId2:groupId2,paixu:paixu,caozuo:caozuo,data:data,dataStart:data_start,
				dataEnd:data_end,nodata:nodata,nodataStart:nodata_start,nodataEnd:nodata_end,duli:duli,login:login,loginStart:login_start,loginEnd:login_end,
				caozuoTime:caozuo_time,caozuoStart:caozuo_start,caozuoEnd:caozuo_end}
		}).done(function(da){
			if(da.status == 1){
				var html = "";
				for(var i=0;i<da.groups.length;i++){
					html += "<tr id='"+da.groups[i].groupId+"'>" 
					+"<td></td>"
					+"<td>"+da.groups[i].groupId+"</td>"
				if(da.groups[i].project != null){
					html += "<td>"+da.groups[i].project+"</td>";
				}else{
					html += "<td>"+da.groups[i].company+"</td>";
				}
				html += "<td>"+da.groups[i].language+"</td>"
						+"<td>"+da.groups[i].comming+"</td>";
				if(da.groups[i].tag != null){
					html += "<td>"+da.groups[i].tag.province+"</td>"
								+"<td>"+da.groups[i].tag.rongzi+"</td>"
								+"<td>"+da.groups[i].tag.category+"</td>"
								+"<td>"+da.groups[i].tag.person+"</td>"
								+"<td>"+da.groups[i].tag.fuwuqi+"</td>"
				}else{
					html += "<td>未知</td><td>未知</td><td>未知</td><td>未知</td><td>未知</td>";
				}
					html += "<td>"+da.groups[i].saleName+"</td>"
							+"<td>"+da.groups[i].preSaleName+"</td>"
							+"<td>"+da.groups[i].supportName+"</td>"
							+"<td><input val1='"+da.groups[i].groupId+"' class='check_view'  type='image' src='http://manage.oneapm.com/skin/images/icn_view_users.png' title='查看' /></td>"
							+"<td></td>"
							+"</tr>";
				}
				$("#duandian_result").html(html);
				$(".duandian_result_msg").html("总数："+da.infos.length);
				$("#duandian_chaxun").html("查询");
			}else{
				alert(da.msg);
				$("#duandian_chaxun").html("查询");
			}
		});
		
	});
});
