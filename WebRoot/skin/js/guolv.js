//var guanbi = 0;
var jinri = 0;
var fuze = 0;
var date_id = "";

$(document).ready(function() {
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
		if(id == 9){
			$("#duandian_zidingyishijian").val("");
			$(".duandian_zidingyishijian").removeClass("hidden");
		}else{
			$("#duandian_zidingyishijian").val("");
			$(".duandian_zidingyishijian").addClass("hidden");
		}
	});
	$("#duandian_data_activ").live("change", function(){
		var id = $(this).val();
		if(id == 9){
			$("#duandian_zidingyishijian_activ").val("");
			$(".duandian_zidingyishijian_activ").removeClass("hidden");
		}else{
			$("#duandian_zidingyishijian_activ").val("");
			$(".duandian_zidingyishijian_activ").addClass("hidden");
		}
	});
	$("#duandian_chaxun").live('click', function(){
		$("#duandian_chaxun").html("查询中");
		$("#duandian_result").html("");
		$(".duandian_result_msg").html("");
		var agent = $("#duandian_agent").val();
		var data = $("#duandian_data").val();
		var groupId1 = $("#duandian_group_1").val();
		var groupId2 = $("#duandian_group_2").val();
		var paixu = $("#paixu").val();
//		var login = $("#duandian_login").val();
		var banben = $("#duandian_banben").val();
		var time = $("#duandian_zidingyishijian").val();
		if(data == 0 && agent == 0 && fuze == 0){
			alert("至少选择中条件");
			return;
		}
		$.ajax({
			dataType:'json',
			url:'duandian_chaxun.action',
			data:{agent:agent,data:data,jinri:jinri,banben:banben,time:time,fuze:fuze,groupId1:groupId1,groupId2:groupId2,paixu:paixu}
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
				$("#duandian_chaxun").val("查询");
			}
		});
		
	});
});
