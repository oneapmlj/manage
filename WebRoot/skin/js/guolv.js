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
				for(var i=0;i<da.infos.length;i++){
					html += "<tr>" 
								+"<td></td>"
								+"<td>"+da.infos[i].userId+"</td>"
								+"<td>"+da.infos[i].name+"</td>"
								+"<td>"+da.infos[i].company+"</td>"
								+"<td>"+da.infos[i].project+"</td>"
								+"<td>"+da.infos[i].language+"</td>"
								+"<td>"+da.infos[i].comming+"</td>"
								+"<td>"+da.infos[i].saleName+"</td>"
								+"<td>"+da.infos[i].preSaleName+"</td>"
								+"<td>"+da.infos[i].supportName+"</td>"
								+"<td><input val1='"+da.infos[i].id+"' class='check_view'  type='image' src='http://manage.oneapm.com/skin/images/icn_view_users.png' title='查看' /></td>"
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
		/*if(agent == 7 || agent == 8){*/
			$(".duandian_banben").removeClass("hidden");
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
				}else{
					alert(alert(da.msg));
				}
			});
//		}
	/*else{
			$(".duandian_banben").addClass("hidden");
			$("#duandian_banben").html("");
		}*/
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
				for(var i=0;i<da.infos.length;i++){
					html += "<tr>" 
								+"<td></td>"
								+"<td>"+da.infos[i].userId+"</td>"
								+"<td>"+da.infos[i].name+"</td>"
								+"<td>"+da.infos[i].company+"</td>"
								+"<td>"+da.infos[i].project+"</td>"
								+"<td>"+da.infos[i].language+"</td>"
								+"<td>"+da.infos[i].comming+"</td>"
								+"<td>"+da.infos[i].saleName+"</td>"
								+"<td>"+da.infos[i].preSaleName+"</td>"
								+"<td>"+da.infos[i].supportName+"</td>"
								+"<td><input val1='"+da.infos[i].id+"' class='check_view'  type='image' src='http://manage.oneapm.com/skin/images/icn_view_users.png' title='查看' /></td>"
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
