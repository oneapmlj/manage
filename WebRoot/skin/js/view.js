var changeForm = function(value){
	if(value == 1){
		/*$(".call_add_time").remove();*/
		var html = "<div class='call_add_card' style='margin-left:10px;float:left;'>名片:"
							+"<select name='card' id='add_call_card' tabindex='1'>"
							+"	<option value='0'>---请选择---</option>"
							+"		<option value='1'>注册信息</option>"
							+"</select>"
							+"</div>";
		$(".call_add_select1").append(html);
	}
	if(value == 2){
		$(".call_add_card").remove();
		/*var html = "<div class='call_add_time' style='margin-left:10px;float:left;'>时间:";
		html += timeSelect("call_add_select_time");
		html += "div";
		$(".call_add_select1").append(html);*/
	}
};

var zhengzailianxi = function(){
	setInterval(function(){
		var infoId = $("#view_infoId").val();
		$.ajax({
			dataType:'json',
			url:'info_zhengzailianxi.action',
			data:{infoId:infoId}
		}).done(function(da){
			if(da.status == 1){
				var html = "<div style='color:red;float:left;' >"+da.admin_name+"正在联系.......</div>";
				if(da.quxiao == 1){
					html += "<div style='float:left;'><input  id='unzhengzailianxi'  class='hand' style='color:red;' type='button' value='联系完毕'/></div>";
					$(".phone_name_hidden").addClass("hidden");
					$(".phone_name").removeClass("hidden");
					$(".click_edit_phone_name").removeClass("hidden");
				}
				$("#lianxi").html(html);
			}
			if(da.status == 2){
				var html = "<input type='button' value='现在联系' class='hand' id='zhengzailianxi'/>";
				$("#lianxi").html(html);
			}
		});
	}, 3000);
}
zhengzailianxi(); 


function showAll(){
	$(".download_view").slideToggle(50);
}
$(document).ready(function() {
	$("#add_card_window_close").click(function(){
		$(".card_add").addClass("hidden");
		$(".keyboard_card_enter").val("1");
		$(".background").addClass("hidden");
	});
	
	$("#add_call_window_close").live("click",function(){
		$(".background").addClass("hidden");
		$(".call_add").addClass("hidden");
		$(".keyboard_call_enter").val("1"); 
	});
	
	$(".guanlian").live("click", function(){
		$("#guanlian_add").removeClass("hidden");
		$("#guanlian_add_value").removeClass("hidden");
		$(this).val("取消");
		$(this).addClass("guanlian_quxiao");
		$(this).removeClass("guanlian");
	});
	$(".guanlian_quxiao").live("click", function(){
		$(this).val("增加");
		$(this).addClass("guanlian");
		$(this).removeClass("guanlian_quxiao");
		$("#guanlian_add_value").val("");
		$("#guanlian_add").addClass("hidden");
		$("#guanlian_add_value").addClass("hidden");
	});

	function findMainAcount(){
		
		var groupId = $("#group_id").html();
		$.ajax({
			dataType:'json',
			url:'user_group_guanlian_view.action',
			data:{groupId:groupId}
		}).done(function(da){
			if(da.status == '1'){
				var html = $("#guanlian_value_"+da.user_id+"").html();
				
				if(da.user_id!=userId){
				
				html = "<div style='width:210px;float:left;line-height:20px;margin-top:5px;'  id='guanlian_value_"+da.user_id+"'>"
				+"<div style='float:left;width:80px;' class='guanlian_view menu_button hand  biankuang_gray'>"+da.user_id+"</div>"
				+"<div class='menu_button  hand  biankuang_blue_ding' style='float:left;width:80px;'>主帐号</div></div>";
				$("#guanlian").hide();
				$(".guanlian_change").hide();
				$(".guanlian_remove").hide();
				}else{
					html = "<div style='width:210px;float:left;line-height:20px;margin-top:5px;'  id='guanlian_value_"+da.user_id+"'>"
					+"<div style='float:left;width:80px;' class='guanlian_view menu_button hand  biankuang_gray'>当前</div>"
					+"<div class='menu_button  hand  biankuang_blue_ding' style='float:left;width:80px;'>主帐号</div></div>";
					
				}
				
				$("#guanlian_value_"+da.user_id+"").html(html);
			}
			
			
		});
	}
	findMainAcount();
	$("#guanlian_add").live("click", function(){
		var id = $("#guanlian_add_value").val();
		if(isNaN(id)){
			alert("请输入userId");
			$("#guanlian_add_value").val("");
			return;
		}
		var groupId = $("#group_id").html();
		$.ajax({
			dataType:'json',
			url:'user_group_guanlian_add.action',
			data:{groupId:groupId,guanlianId:id}
		}).done(function(da){
			if(da.status == '1'){
				$("#guanlian_add_value").val("");
				var html = $("#guanlian_value").html();
				if(html == null || html.trim() == ""){
					html ="<div style='width:210px;float:left;line-height:20px;margin-top:5px;' id='guanlian_value_"+userId+"'>"
								+"<div style='float:left;width:80px;' class='menu_button hand  biankuang_gray_ding' >当前</div>"
								+"<div class='menu_button  hand  biankuang_blue_ding' style='float:left;width:80px;'>主帐号</div>"
								+"</div>"
								+"<div style='width:210px;float:left;line-height:20px;margin-top:5px;'  id='guanlian_value_"+da.guanlian_id+"'>"
								+"<div style='float:left;width:80px;' class='guanlian_view menu_button hand  biankuang_gray'>"+da.guanlian_id+"</div>"
								+"<div class='guanlian_change menu_button  hand  biankuang_gray_ding' style='float:left;width:80px;'>定为主帐号</div>"
								+"<div class='guanlian_remove menu_button  hand  biankuang_gray_ding' style='float:left;width:20px;'>X</div>"
								+"</div>";
				}else{
					html += "<div style='width:210px;float:left;line-height:20px;margin-top:5px;'  id='guanlian_value_"+da.guanlian_id+"'>"
								+"<div style='float:left;width:80px;' class='guanlian_view menu_button hand  biankuang_gray'>"+da.guanlian_id+"</div>"
								+"<div class='guanlian_change menu_button  hand  biankuang_gray_ding' style='float:left;width:80px;'>定为主帐号</div>"
								+"<div class='guanlian_remove menu_button  hand  biankuang_gray_ding' style='float:left;width:20px;'>X</div>"
								+"</div>";
				}
				$("#guanlian_value").html(html);
			}else{
				alert(da.msg);
			}
		});
	});

	$(".guanlian_remove").live("click",function(){
		var id = $(this).prev().prev().html();
		var groupId = $("#group_id").html();
		var statu = confirm("确认删除吗?");
	        if(!statu){
	            return false;
	        }
		$.ajax({
			dataType:'json',
			url:'user_group_guanlian_remove.action',
			data:{groupId:groupId,guanlianId:id}
		}).done(function(da){
			var html = $("#guanlian_value").html();
			if(da.status == '1'){
				
				location.reload(); 
			}else{
				alert(da.msg);
			}
		});
		
		
	});
	$(".guanlian_change").live("click",function(){
		var id = $(this).prev().html();
		var groupId = $("#group_id").html();
		$.ajax({
			dataType:'json',
			url:'user_group_guanlian_change.action',
			data:{groupId:groupId,guanlianId:id}
		}).done(function(da){
			if(da.status == '1'){
			var html = $("#guanlian_value_"+da.guanlian_id+"").html();
				html = "<div style='width:210px;float:left;line-height:20px;margin-top:5px;'  id='guanlian_value_"+da.guanlian_id+"'>"
				+"<div style='float:left;width:80px;' class='guanlian_view menu_button hand  biankuang_gray'>"+da.guanlian_id+"</div>"
				+"<div class='menu_button  hand  biankuang_blue_ding' style='float:left;width:80px;'>主帐号</div></div>";
				$("#guanlian_value_"+da.guanlian_id+"").html(html);
			var rehtml = $("#guanlian_value_"+da.user_id+"").html();
			rehtml = "<div style='width:210px;float:left;line-height:20px;margin-top:5px;'  id='guanlian_value_"+da.user_id+"'>"
			+"<div style='float:left;width:80px;' class='guanlian_view menu_button hand  biankuang_gray'>当前</div>"
			+"</div>";
			
			$("#guanlian_value_"+da.user_id+"").html(rehtml);
			$("#guanlian").hide();
			$("#guanlian_add_value").hide();
			$("#guanlian_add").hide();
			$(".guanlian_change").hide();
			$(".guanlian_remove").hide();
			
			}else{
				alert(da.msg);
			}
			/*window.location.reload();*/	
		});
		
		
	});
	$(".guanlian_view").live("click", function(){
		var userId = $(this).html();
		
			/*window.open("/"+userId);*/
		window.location.href = "http://manage.oneapm.com/user_group_view.action?id="+userId+"";
		
	});
	$(".group_area_1").live('click', function(){
		$.ajax({
			dataType:'json',
			url:'group_group.action',
			data:{groupId:0}
		}).done(function(da){
			if(da.status == 1){
				var html ="<select class='group_save_1 hand' style='width:100px;'><option value='0'>请选择</option>";
				for(var i=0;i<da.groups.length;i++){
					html +="<option value='"+da.groups[i].id+"'>"+da.groups[i].name+"</option>";
				}
				html +="</select>";
				$(".group_edit_1").html(html);
			}else{
				alert(da.msg);
			}
		});
	});
	$(".group_save_1").live("change", function(){
		var id = $(".group_save_1").val();
		if(id <= 0){
			alert("请选择一种状态");
			return;
		}
		var infoId = $("#view_infoId").val();
		$.ajax({
			dataType:'json',
			url:'info_group.action',
			data:{groupId:id,infoId:infoId}
		}).done(function(da){
			if(da.status == 1){
				var html = "<input val='"+da.group.id+"' type='button' class='group_area_1 biankuang_white_ding hand' style='float:left;width:110px;height:23px;line-height:20px;' value='"+da.group.name+"'/>";
				$(".group_edit_1").html(html);
				$(".group_edit_2").html("<input type='button' class='group_area_2 biankuang_white_ding hand' style='float:left;width:110px;height:23px;line-height:20px;' value='无'/>");
			}else{
				alert(da.msg);
			}
		});
	});
	$(".group_area_2").live('click', function(){
		var father = $(".group_area_1").attr("val");
		if(father <= 0){
			alert("请先选择状态");
			return;
		}
		$.ajax({
			dataType:'json',
			url:'group_group.action',
			data:{groupId:father}
		}).done(function(da){
			if(da.status == 1){
				if(da.groups.length <= 0){
					alert("此状态没有分类");
					return;
				}
				var html ="<select class='group_save_2 hand' style='width:100px;'><option value='0'>请选择</option>";
				for(var i=0;i<da.groups.length;i++){
					html +="<option value='"+da.groups[i].id+"'>"+da.groups[i].name+"</option>";
				}
				html +="</select>";
				$(".group_edit_2").html(html);
			}else{
				alert(da.msg);
			}
		});
	});
	$(".group_save_2").live("change", function(){
		var id = $(".group_save_2").val();
		if(id <= 0){
			alert("请选择用户状态");
			return;
		}
		var infoId = $("#view_infoId").val();
		$.ajax({
			dataType:'json',
			url:'info_group.action',
			data:{groupId:id,infoId:infoId}
		}).done(function(da){
			if(da.status == 1){
				var html = "<input type='button' class='group_area_2 biankuang_white_ding hand' style='float:left;width:110px;height:23px;line-height:20px;' value='"+da.group.name+"'/>";
				$(".group_edit_2").html(html);
			}else{
				alert(da.msg);
			}
		});
	});
	$(".add_mark_view").live("click", function(){
		var infoId = $("#view_infoId").val();
		var groupId = $("#group_id").html();
		$.ajax({
			dataType:'json',
			url:'mark_add.action',
			data:{groupId:groupId}
		}).done(function(da){
			if(da.status == 1){
				$(".add_mark_view").css("width","100px");
				$(".add_mark_view").css("color","red");
				$(".add_mark_view").html("取消标记");
				$(".add_mark_view").addClass("mark_id_"+da.mark.id);
				$(".add_mark_view").addClass("remove_mark_view");
				$(".add_mark_view").removeClass("add_mark_view");
				$("add_mark_view_id").val(da.mark.id);
			}else{
				alert(da.msg);
			}
		});
	});
	$(".remove_mark_view").live("click", function(){
		var id = $(".add_mark_view_id").val();
		$.ajax({
			dataType:'json',
			url:'mark_close.action',
			data:{id:id}
		}).done(function(da){
			if(da.status == 1){
				$(".remove_mark_view").css("width","60px");
				$(".remove_mark_view").css("color","");
				$(".remove_mark_view").html("标记");
				$(".remove_mark_view").addClass("add_mark_view");
				$(".remove_mark_view").removeClass("remove_mark_view");
			}else{
				alert(da.msg);
			}
		});
	});
	$(".add_xiaoshouyi_view").live('click', function(){
		var id = $("#view_infoId").val();
		var groupId = $("#group_id").html();
		var xiaoshou = $(".add_xiaoshouyi_view_sale").val();
		$.ajax({
			dataType:'json',
			url:'user_group_xiaoshouyi.action',
			data:{groupId:groupId,xiaoshou:xiaoshou}
		}).done(function(da){
			if(da.status == 1){
				$(".add_xiaoshouyi_view").css("color","blue");
				$(".add_xiaoshouyi_view").removeClass("biankuang_gray");
				$(".add_xiaoshouyi_view").addClass("biankuang_gray_ding");
				$(".add_xiaoshouyi_view").html("已推送销售");
				$(".add_xiaoshouyi_view").removeClass("add_xiaoshouyi_view");
			}else{
				alert(da.msg);
			}
		});
	});
	$("#note_add_button").live("click",function(){
		$("#note_add_button").addClass("hidden");
		$("#note_add").removeClass("hidden");
	});
	$(".click_edit_project_name").live('click', function(){
		$(this).addClass("click_save_project_name");
		$(this).removeClass("click_edit_project_name");
		$(this).html("保存");
		var project = $(".project_name").html();
		$(".project_name").addClass("hidden");
		$(".edit_project_name").val(project);
		$(".edit_project_name").removeClass("hidden");
	});
	
	$("#zhengzailianxi").live("click", function(){
		var infoId = $("#view_infoId").val();
		$.ajax({
			dataType:'json',
			url:'info_lianxi.action',
			data:{infoId:infoId}
		}).done(function(da){
			if(da.status == 1){
				var html = "<div style='color:red;float:left;' >"+da.admin_name+"正在联系.......</div>"
						+"<div style='float:left;'><input  id='unzhengzailianxi'  class='hand' style='color:red;' type='button' value='联系完毕'/></div>";
				$("#lianxi").html(html);
				$(".phone_name_hidden").addClass("hidden");
				$(".phone_name").removeClass("hidden");
				$(".click_edit_phone_name").removeClass("hidden");
			}else{
				alert(da.msg);
			}
		});
	});
	
	$("#unzhengzailianxi").live("click", function(){
		var infoId = $("#view_infoId").val();
		$.ajax({
			dataType:'json',
			url:'info_unlianxi.action',
			data:{infoId:infoId}
		}).done(function(da){
			if(da.status == 1){
				var html = "<input type='button' value='现在联系' class='hand' id='zhengzailianxi'/>";
				$("#lianxi").html(html);
				$(".phone_name_hidden").removeClass("hidden");
				$(".phone_name").addClass("hidden");
				$(".click_edit_phone_name").addClass("hidden");
			}else{
				alert(da.msg);
			}
		});
	});
	
	$(".click_save_project_name").live('click', function(){
		var project = $(".edit_project_name").val();
		var groupId = $("#view_groupId").val();
		$.ajax({
			dataType:'json',
			url:'user_group_edit.action',
			data:{groupId:groupId,project:project}
		}).done(function(da){
			if(da.status == 1){
				$(".project_name").html(da.project);
				$(".edit_project_name").addClass("hidden");
				$(".project_name").removeClass("hidden");
				$(".click_save_project_name").addClass("click_edit_project_name");
				$(".click_save_project_name").removeClass("click_save_project_name");
				$(".click_edit_project_name").html("编辑");
			}else{
				alert(da.msg);
				$(".edit_project_name").addClass("hidden");
				$(".project_name").removeClass("hidden");
				$(".click_save_project_name").addClass("click_edit_project_name");
				$(".click_save_project_name").removeClass("click_save_project_name");
				$(".click_edit_project_name").html("编辑");
			}
		});
	});
	$(".click_edit_qq_name").live('click', function(){
		$(this).addClass("click_save_qq_name");
		$(this).removeClass("click_edit_qq_name");
		$(this).html("保存");
		var project = $(this).parent().find(".qq_name").html();
		$(this).parent().find(".qq_name").addClass("hidden");
		$(this).parent().find(".edit_qq_name").val(project);
		$(this).parent().find(".edit_qq_name").removeClass("hidden");
	});
	
	$(".click_edit_gender_name").live('click', function(){
		$(this).addClass("click_save_gender_name");
		$(this).removeClass("click_edit_gender_name");
		$(this).html("保存");
		var gender = $(this).parent().find(".gender_name").html();
		$(this).parent().find(".gender_name").addClass("hidden");
		var html ="<option value='0'";
		if(gender == "未知"){
			html += " selected='selected'>未知</option><option value='1'"
		}else{
			html += ">未知</option><option value='1'"
		}
		if(gender == "男"){
			html += " selected='selected'>男</option><option value='2'"
		}else{
			html += ">男</option><option value='2'"
		}
		if(gender == "女"){
			html += " selected='selected'>女</option>"
		}else{
			html += ">女</option>"
		}
		$(this).parent().find(".edit_gender_name").html(html);
		$(this).parent().find(".edit_gender_name").removeClass("hidden");
	});
	
	$(".click_save_gender_name").live('click', function(){
		var genderdiv = $(this).parent().find(".edit_gender_name");
		var genderspan = $(this).parent().find(".gender_name");
		var gender = $(this).parent().find(".edit_gender_name").val();
		$(this).parent().find(".click_save_gender_name").addClass("click_edit_gender_name");
		$(this).parent().find(".click_save_gender_name").removeClass("click_save_gender_name");
		$(this).parent().find(".click_edit_gender_name").html("编辑");
		var id = $(this).parent().parent().attr("val");
		$.ajax({
			dataType:'json',
			url:'info_edit.action',
			data:{id:id,gender:gender}
		}).done(function(da){
			if(da.status == 1){
				genderdiv.addClass("hidden");
				if(da.gender == '0'){
					genderspan.html("未知");
				}
				if(da.gender == '1'){
					genderspan.html("男");
				}
				if(da.gender == '2'){
					genderspan.html("女");
				}
				genderspan.removeClass("hidden");
			}else{
				alert(da.msg);
			}
		});
	});
	
	$(".click_edit_license_name").live('click', function(){
		$(this).addClass("click_save_license_name");
		$(this).removeClass("click_edit_license_name");
		$(this).html("保存");
		var license = $(".license_name").html();
		$(".license_name").addClass("hidden");
		$(".edit_license_name").val(license);
		$(".edit_license_name").removeClass("hidden");
		$(".edit_license_pay_level").removeClass("hidden");
	});
	
	$(".click_save_license_name").live('click', function(){
		var license = $(".edit_license_name").val();
		var pay_level = $(".edit_license_pay_level").val();
		var id = $("#view_groupId").val();
		$.ajax({
			dataType:'json',
			url:'user_group_edit.action',
			data:{groupId:id,license:license,pay_level:pay_level}
		}).done(function(da){
			if(da.status == 1){
				$(".license_name").html(da.expireTime);
				$(".license_name").removeClass("hidden");
				$(".edit_license_pay_level").addClass("hidden");
				$(".edit_license_name").addClass("hidden");
				$(".click_save_license_name").addClass("click_edit_license_name");
				$(".click_save_license_name").removeClass("click_save_license_name");
				$(".click_edit_license_name").html("编辑");
			}else{
				alert(da.msg);
				$(".license_name").removeClass("hidden");
				$(".edit_license_pay_level").addClass("hidden");
				$(".edit_license_name").addClass("hidden");
				$(".click_save_license_name").addClass("click_edit_license_name");
				$(".click_save_license_name").removeClass("click_save_license_name");
				$(".click_edit_license_name").html("编辑");
			}
		});
	});
	
	$(".click_save_qq_name").live('click', function(){
		var qqdiv = $(this).parent().find(".edit_qq_name");
		var qqspan = $(this).parent().find(".qq_name");
		var qq = $(this).parent().find(".edit_qq_name").val();
		$(this).parent().find(".click_save_qq_name").addClass("click_edit_qq_name");
		$(this).parent().find(".click_save_qq_name").removeClass("click_save_qq_name");
		$(this).parent().find(".click_edit_qq_name").html("编辑");
		var id = $(this).parent().parent().attr("val");
		$.ajax({
			dataType:'json',
			url:'info_edit.action',
			data:{id:id,qq:qq}
		}).done(function(da){
			if(da.status == 1){
				qqdiv.addClass("hidden");
				qqspan.html(da.qq);
				qqspan.removeClass("hidden");
			}else{
				alert(da.msg);
			}
		});
	});
	
	$(".click_edit_name_name").live('click', function(){  
		$(this).addClass("click_save_name_name");
		$(this).removeClass("click_edit_name_name");
		$(this).html("保存");
		var project = $(this).parent().find("span.name_name").html();
		$(this).parent().find(".name_name").addClass("hidden");
		$(this).parent().find(".edit_name_name").val(project);
		$(this).parent().find(".edit_name_name").removeClass("hidden");
	});
	
	$(".click_save_name_name").live('click', function(){
		var namediv = $(this).parent().find(".edit_name_name");
		var namespan = $(this).parent().find(".name_name");
		var name = $(this).parent().find(".edit_name_name").val();
		$(this).parent().find(".click_save_name_name").addClass("click_edit_name_name");
		$(this).parent().find(".click_save_name_name").removeClass("click_save_name_name");
		$(this).parent().find(".click_edit_name_name").html("编辑");
		var id = $(this).parent().parent().attr("val");
		$.ajax({
			dataType:'json',
			url:'info_edit.action',
			data:{id:id,name:name}
		}).done(function(da){
			if(da.status == 1){
				namediv.addClass("hidden");
				namespan.html(da.name);
				namespan.removeClass("hidden");
			}else{
				alert(da.msg);
			}
		});
	});
	
	$(".click_edit_phone_name").live('click', function(){
		$(this).addClass("click_save_phone_name");
		$(this).removeClass("click_edit_phone_name");
		$(this).html("保存");
		var project = $(this).parent().find("span.phone_name").html();
		$(this).parent().find(".phone_name").addClass("hidden");
		$(this).parent().find(".edit_phone_name").val(project);
		$(this).parent().find(".edit_phone_name").removeClass("hidden");
	});
	
	$(".click_save_phone_name").live('click', function(){
		var phonediv = $(this).parent().find(".edit_phone_name");
		var phonespan = $(this).parent().find(".phone_name");
		var phone = $(this).parent().find(".edit_phone_name").val();
		$(this).parent().find(".click_save_phone_name").addClass("click_edit_phone_name");
		$(this).parent().find(".click_save_phone_name").removeClass("click_save_phone_name");
		$(this).parent().find(".click_edit_phone_name").html("编辑");
		var id =  $(this).parent().parent().attr("val");
		$.ajax({
			dataType:'json',
			url:'info_edit.action',
			data:{id:id,phone:phone}
		}).done(function(da){
			if(da.status == 1){
				phonediv.addClass("hidden");
				phonespan.html(da.phone);
				phonespan.removeClass("hidden");
			}else{
				alert(da.msg);
			}
		});
	});
	
	$(".click_edit_email_name").live('click', function(){
		$(this).addClass("click_save_email_name");
		$(this).removeClass("click_edit_email_name");
		$(this).html("保存");
		var project = $(this).parent().find(".email_name").html();
		$(this).parent().find(".email_name").addClass("hidden");
		$(this).parent().find(".edit_email_name").val(project);
		$(this).parent().find(".edit_email_name").removeClass("hidden");
	});
	
	$(".click_save_email_name").live('click', function(){
		var emaildiv = $(this).parent().find(".edit_email_name");
		var emailspan = $(this).parent().find(".email_name");
		var email = $(this).parent().find(".edit_email_name").val();
		if(email != null && email.trim().length > 1){
			var search_str = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
			if(!search_str.test(email)){
				alert("邮箱格式不对！")
				return;
			}
		}
		$(this).parent().find(".click_save_email_name").addClass("click_edit_email_name");
		$(this).parent().find(".click_save_email_name").removeClass("click_save_email_name");
		$(this).parent().find(".click_edit_email_name").html("编辑");
		var id = $(this).parent().parent().attr("val");
		$.ajax({
			dataType:'json',
			url:'info_edit.action',
			data:{id:id,email:email}
		}).done(function(da){
			if(da.status == 1){
				emaildiv.addClass("hidden");
				emailspan.html(da.email);
				emailspan.removeClass("hidden");
			}else{
				alert(da.msg);
			}
		});
	});
	
	$(".account_mails").live("click", function(){
		$(".menu_button_2").removeClass("biankuang_blue_ding");
		$(".menu_button_2").addClass("biankuang_gray");
		$(this).removeClass("biankuang_gray");
		$(this).addClass("biankuang_blue_ding");
		$(".tab").addClass("hidden");
		$("#tab_2").removeClass("hidden");
	});
	$(".account_info").live("click", function(){
		$(".menu_button_2").removeClass("biankuang_blue_ding");
		$(".menu_button_2").addClass("biankuang_gray");
		$(this).removeClass("biankuang_gray");
		$(this).addClass("biankuang_blue_ding");
		$(".tab").addClass("hidden");
		$("#tab_3").removeClass("hidden");
	});
	$(".view_mark").live('click', function(){
		var infoId = $("#view_infoId").val();
		$.ajax({
			dataType:'json',
			url:'mark_add.action',
			data:{infoId:infoId}
		}).done(function(da){
			if(da.status == 1){
				var html = "<div style='float:left;width:60px;color:red;' id='"+da.mark.id+"' class='view_mark_d biankuang_gray menu_button_2'>取消标记</div>";
				$("#view_mark").html(html);
			}
		});
	});
	$(".view_mark_d").live('click', function(){
		var id = $(this).attr("id");
		$.ajax({
			dataType:'json',
			url:'mark_update.action',
			data:{id:id,status:1}
		}).done(function(da){
			if(da.status == 1){
				var html = "<div style='float:left;width:50px;' class='view_mark biankuang_gray menu_button_2'>标记</div>";
				$("#view_mark").html(html);
			}
		});
	});
	$(".view_edit_tag_metric").live('click', function(){
		var ID = $(this).attr('id');
		var ids = ID.split("_");
		var id = ids[4];
		if(id == '4'){
			alert("只能在记录中关闭");
			return;
		}
		var infoId = $("#view_infoId").val();
		var groupId = $("#group_id").html();
		$.ajax({
			dataType:'json',
			url:'tag_metric.action',
			data:{groupId:groupId,metric:id}
		}).done(function(da){
			if(da.status == 1){
				$(".view_edit_tag_metric").removeClass("biankuang_blue_ding");
				$(".view_edit_tag_metric").addClass("biankuang_white_ding");
				$("#view_edit_tag_metric_"+da.metric).removeClass("biankuang_white_ding");
				$("#view_edit_tag_metric_"+da.metric).addClass("biankuang_blue_ding");
			}else{
				alert(da.msg);
			}
		});
	});
	$(".view_edit_tag_loudou").live('click', function(){
		var group = $("#admin_group").val();
		if(group != 1){
			alert("只有销售负责人才能进行此操作");
			return;
		}
		var infoId = $("#view_infoId").val();
		$.ajax({
			dataType:'json',
			url:'tag_loudou.action',
			data:{infoId:infoId}
		}).done(function(da){
			if(da.status == 1){
				$("#view_edit_tag_loudou").html(da.loudou);
				if(da.next != null){
					$("#view_edit_tag_loudou_next").val("下一级段:"+da.next);
				}else{
					$("#view_edit_tag_loudou_next").remove();
				}
			}else{
				alert(da.msg);
			}
		});
	});
	$(".view_edit_tag_language").live('click', function(){
		var ID = $(this).attr('id');
		var ids = ID.split("_");
		var id = ids[4];
		var infoId = $("#view_infoId").val();
		$.ajax({
			dataType:'json',
			url:'tag_language.action',
			data:{infoId:infoId,language:id}
		}).done(function(da){
			if(da.status == 1){
				if(da.add == 0){
					$("#view_edit_tag_language_"+da.language).removeClass('biankuang_blue_ding');
					$("#view_edit_tag_language_"+da.language).addClass('biankuang_white_ding');
				}else{
					$("#view_edit_tag_language_"+da.language).removeClass('biankuang_white_ding');
					$("#view_edit_tag_language_"+da.language).addClass('biankuang_blue_ding');
				}
			}
		});
	});
	$("#view_info_remind_sale").live('click', function(){
		var id = $("#view_infoId").val();
		var groupId = $("#group_id").html();
		$.ajax({
			dataType:'json',
			url:'user_group_change.action',
			data:{groupId:groupId,type:7}
		}).done(function(da){
			if(da.status == 1){
				
			}else{
				alert(da.msg);
			}
			$("#view_info_remind_sale").remove();
		});
	});
	$("#view_info_delete_sale").live('click', function(){
		var groupId = $("#group_id").html();
		$.ajax({
			dataType:'json',
			url:'user_group_delete.action',
			data:{groupId:groupId,type:1}
		}).done(function(da){
			if(da.status == 1){
				location.reload(true);
			}else{
				alert(da.msg);
			}
		});
	});
	$("#view_info_delete_presale").live('click', function(){
		var groupId = $("#group_id").html();
		$.ajax({
			dataType:'json',
			url:'user_group_delete.action',
			data:{groupId:groupId,type:3}
		}).done(function(da){
			if(da.status == 1){
				location.reload(true);
			}else{
				alert(da.msg);
			}
		});
	});
	$("#view_info_delete_support").live('click', function(){
		var groupId = $("#group_id").html();
		$.ajax({
			dataType:'json',
			url:'user_group_delete.action',
			data:{groupId:groupId,type:2}
		}).done(function(da){
			if(da.status == 1){
				location.reload(true);
			}else{
				alert(da.msg);
			}
		});
	});
	$(".view_tip_d").live('click', function(){
		var id = $("#view_infoId").val();
		$.ajax({
			dataType:'json',
			url:'account_list.action',
			data:{id:id,group:0}
		}).done(function(da){
			if(da.status == 1){
				var html = "<div style='position:absolute;width:80px;background-color:#F8F8F8;margin:0 0 0 160px' class='tip_list'>" +
						"<div style='color:gray;border: 1px solid #9CA1B0;' class='tip_off boder hand'>&nbsp;取消</div>";
				var admins = da.admins;
				for(var i=0;i<admins.length;i++){
					html+="<div style='border: 1px solid #9CA1B0;' class='tip_one boder hand' val1='"+admins[i].id+"'>&nbsp;"+admins[i].name+"</div>";
				}
				html+="</div>";
				$("#title_info").append(html);
			}else{
				alert(da.msg);
			}
		})
	});
	$(".tip_off").live('click', function(){
		$(".tip_list").remove();
	});
	$("#view_info_remind_support").live('click', function(){
		var id = $("#view_infoId").val();
		var groupId = $("#group_id").html();
		$.ajax({
			dataType:'json',
			url:'user_group_change.action',
			data:{groupId:groupId,type:8}
		}).done(function(da){
			if(da.status == 1){
				
			}else{
				alert(da.msg);
			}
			$("#view_info_remind_support").remove();
		});
	});
	$("#view_info_back_sale").live('click', function(){
		var id = $("#view_infoId").val();
		var groupId = $("#group_id").html();
		$.ajax({
			dataType:'json',
			url:'user_group_back.action',
			data:{groupId:groupId,type:1}
		}).done(function(da){
			if(da.status == 1){
				location.reload(true);
			}else{
				alert(da.msg);
			}
		});
	});
	$("#view_info_back_support").live('click', function(){
		var id = $("#view_infoId").val();
		var groupId = $("#group_id").html();
		$.ajax({
			dataType:'json',
			url:'user_group_back.action',
			data:{groupId:groupId,type:2}
		}).done(function(da){
			if(da.status == 1){
				location.reload(true);
			}else{
				alert(da.msg);
			}
		});
	});
	$("#view_info_back_presale").live('click', function(){
		var id = $("#view_infoId").val();
		var groupId = $("#group_id").html();
		$.ajax({
			dataType:'json',
			url:'user_group_back.action',
			data:{groupId:groupId,type:3}
		}).done(function(da){
			if(da.status == 1){
				location.reload(true);
			}else{
				alert(da.msg);
			}
		});
	});
	$("#view_info_assign_sale").live('click', function(){
		var id = $("#view_infoId").val();
		$.ajax({
			dataType:'json',
			url:'account_list.action',
			data:{id:id,group:1}
		}).done(function(da){
			if(da.status == 1){
				var html = "<div style='z-index:1002;position:absolute;width:80px;background-color:#F8F8F8;margin:27px 0 0 70px;' class='sale_assign_list'>" +
						"<div style='color:gray;border: 1px solid #9CA1B0;' class='assign_off boder hand'>&nbsp;取消</div>";
				var admins = da.admins;
				for(var i=0;i<admins.length;i++){
					html+="<div style='border: 1px solid #9CA1B0;' class='sale_assign_one boder hand' val1='"+admins[i].id+"'>&nbsp;"+admins[i].name+"</div>";
				}
				html+="</div>";
				$(".sale_assign").append(html);
				$(".background").removeClass("hidden");
			}else{
				alert(da.msg);
			}
		})
	});
	$(".sale_assign_one").live('click', function(){
		var adminId = $(this).attr('val1');
		var id = $("#view_infoId").val();
		var groupId = $("#group_id").html();
		$.ajax({
			dataType:'json',
			url:'assign_sale.action',
			data:{adminId:adminId,groupId:groupId}
		}).done(function(da){
			if(da.status == 1){
				location.reload(true);
			}
			else{
				alert(da.msg);
			}
		});
	});
	$(".tip_one").live('click', function(){
		var adminId = $(this).attr('val1');
		var id = $("#view_infoId").val();
		$.ajax({
			dataType:'json',
			url:'message_tip.action',
			data:{to:adminId,infoId:id}
		}).done(function(da){
			if(da.status == 1){
				location.reload(true);
			}
		});
	});
	$("#view_info_assign_presale").live('click', function(){
		var id = $("#view_infoId").val();
		$.ajax({
			dataType:'json',
			url:'account_list.action',
			data:{id:id,group:2}
		}).done(function(da){
			if(da.status == 1){
				var html = "<div style='z-index:1002;position:absolute;width:80px;background-color:#F8F8F8;margin:27px 0 0 70px;' class='presale_assign_list'>" +
						"<div style='color:gray;border: 1px solid #9CA1B0;' class='assign_off boder hand'>&nbsp;取消</div>";
				var admins = da.admins;
				for(var i=0;i<admins.length;i++){
					html+="<div style='border: 1px solid #9CA1B0;' class='presale_assign_one boder hand' val1='"+admins[i].id+"'>&nbsp;"+admins[i].name+"</div>";
				}
				html+="</div>";
				$(".presale_assign").append(html);
				$(".background").removeClass("hidden");
			}else{
				alert(da.msg);
			}
		})
	});
	$(".presale_assign_one").live('click', function(){
		var adminId = $(this).attr('val1');
		var groupId = $("#group_id").html();
		var id = $("#view_infoId").val();
		$.ajax({
			dataType:'json',
			url:'assign_presale.action',
			data:{adminId:adminId,groupId:groupId}
		}).done(function(da){
			if(da.status == 1){
				location.reload(true);
			}
		});
	});

	$("#view_info_assign_support").live('click', function(){
		var id = $("#view_infoId").val();
		$.ajax({
			dataType:'json',
			url:'account_list.action',
			data:{id:id,group:2}
		}).done(function(da){
			if(da.status == 1){
				var html = "<div style='z-index:1002;position:absolute;width:80px;background-color:#F8F8F8;margin:27px 0 0 70px' class='support_assign_list'>" +
						"<div style='color:gray;border: 1px solid #9CA1B0;' class='assign_off boder hand'>&nbsp;取消</div>";
				var admins = da.admins;
				for(var i=0;i<admins.length;i++){
					html+="<div style='border: 1px solid #9CA1B0;' class='support_assign_one boder hand' val1='"+admins[i].id+"'>&nbsp;"+admins[i].name+"</div>";
				}
				html+="</div>";
				$(".support_assign").append(html);
				$(".background").removeClass("hidden");
			}else{
				alert(da.msg);
			}
		})
	});
	$(".support_assign_one").live('click', function(){
		var adminId = $(this).attr('val1');
		var groupId = $("#group_id").html();
		var id = $("#view_infoId").val();
		$.ajax({
			dataType:'json',
			url:'assign_support.action',
			data:{adminId:adminId,groupId:groupId}
		}).done(function(da){
			if(da.status == 1){
				location.reload(true);
			}
		});
	});
	$(".assign_off").live('click', function(){
		$(this).parent().remove();
		$(".background").addClass("hidden");
	});
	$("#view_edit_tag_click").live('click', function(){
		$(this).children().val("保存");
		$(this).attr("id","view_edit_tag_save");
		$("#view_edit_tag_person").addClass("hidden");
		$("#view_edit_tag_person_val").removeClass("hidden");
		$("#view_edit_tag_province").addClass("hidden");
		$("#view_edit_tag_province_val").removeClass("hidden");
//		$("#view_edit_tag_pv").addClass("hidden");
//		$("#view_edit_tag_pv_val").removeClass("hidden");
//		$("#view_edit_tag_uv").addClass("hidden");
//		$("#view_edit_tag_uv_val").removeClass("hidden");
		$("#view_edit_tag_rongzi").addClass("hidden");
		$("#view_edit_tag_rongzi_val").removeClass("hidden");
		$("#view_edit_tag_description").addClass("hidden");
		$("#view_edit_tag_description_val").removeClass("hidden");
		$("#view_edit_tag_category").addClass("hidden");
		$("#view_edit_tag_category_val").removeClass("hidden");
		$("#view_edit_tag_fuwuqi").addClass("hidden");
		$("#view_edit_tag_fuwuqi_val").removeClass("hidden");
		$("#view_edit_tag_from").addClass("hidden");
		$("#view_edit_tag_from_val").removeClass("hidden");
	});
	$("#view_edit_tag_save").live('click', function(){
			var id = $("#view_tag_id").val();
			var person = $("#view_edit_tag_person_val").val();
			var province = $("#view_edit_tag_province_val").val();
//			var pv = $("#view_edit_tag_pv_val").val();
//			var uv = $("#view_edit_tag_uv_val").val();
			var rongzi = $("#view_edit_tag_rongzi_val").val();
			var description = $("#view_edit_tag_description_val").val();
			var category = $("#view_edit_tag_category_val").val();
			var fuwuqi = $("#view_edit_tag_fuwuqi_val").val();
			var from = $("#view_edit_tag_from_val").val();
			description=description.replace(/\n|\r\n/g,"<br>");
			$.ajax({
				dataType:'json',
				url:'tag_update.action',
				data:{id:id,person:person,province:province,rongzi:rongzi,description:description,category:category,from:from,fuwuqi:fuwuqi}
			}).done(function(da){
				if(da.status == "1"){
					location.reload(); 
				}else{
					alert(da.msg);
				}
			});
	});
	
	$(".biankuang_gray").live('mouseover', function(){
		$(this).removeClass("biankuang_gray");
		$(this).addClass("biankuang_blue");
	});
	$(".biankuang_blue").live('mouseleave', function(){
		$(this).removeClass("biankuang_blue");
		$(this).addClass("biankuang_gray");
	});
	$(".add_card").click(function(){
		$.ajax({
			dataType:'json',
			url:'account_grade.action',
			data:{type:102}
		}).done(function(da){
			if(da.status == 1){
				$(".background").removeClass("hidden");
				$(".card_add").removeClass("hidden");
				$("#add_card_name").focus();
				$(".keyboard_card_enter").val("0");
			}else{
				alert(da.msg);
			}
		});
	});
	$(".add_call").click(function(){
		totango.track("add_call_click", "add_call"); 
		$.ajax({
			dataType:'json',
			url:'account_grade.action',
			data:{type:103}
		}).done(function(da){
			if(da.status == 1){
				$.ajax({
					dataType:'json',
					url:'note_types.action',
					data:{father:0}
				}).done(function(da){
					if(da.status == 1){
						$("#selects").html("");
						var notes = da.notes;
						if(notes != null && notes.length > 0){
							var html = "<div style='margin:5px 0 0 10px;float:left;'>"
												+"	<select class='hand type_change' style='width:100px;' id='add_record_type_"+note+"'>"
												+"<option value='0'>请选择</option>";
							var html2 = "<option value='0'>父选项</option>";
							for(var i=0;i<notes.length;i++){
								html += "<option value='"+notes[i].id+"'>"+notes[i].name+"</option>";
								html2 += "<option value='"+notes[i].id+"'>"+notes[i].name+"</option>";
							}							
							html +="</select>	</div>";
							$("#notetype_add_chiled_father").html(html2);
							$("#selects").append(html);
						}
					}else{
						alert(da.msg);
					}
				});
				$(".call_add").removeClass("hidden");
				$(".background").removeClass("hidden");
				$("#add_call_name").focus();
				$(".keyboard_call_enter").val("0");
			}else{
				alert(da.msg);
			}
		});
	});
	
	$(".add_mail").click( function(){
		var infoId = $("#view_infoId").val();
		var groupId = $("#group_id").html();
		$.ajax({
			dataType:'json',
			url:'account_grade.action',
			data:{type:100}
		}).done(function(da){
			if(da.status == 1){
				window.open("mail_send.action?groupId="+groupId);
			}else{
				alert(da.msg);
			}
		});
	});
	
	$("#view_send_mail").click(function(){
		$.ajax({
			dataType:'json',
			url:'account_grade.action',
			data:{type:100}
		}).done(function(da){
			if(da.status == 1){
				var id = $("#view_infoId").val();
				var mode = $("#view_mail_mode").val();
				$.ajax({
					dataType:'json',
					url:'mail_sendSingle.action',
					data:{mode:mode,infoId:id}
				}).done(function(da){
					if(da.status == 1){
						$("#add_mail_window_close").click();
						var html = "<div class='view_p_content biankuang_gray hand'>"
										+"<div style='margin-left:5px;width:170px;float:left;'>操作者："+da.adminName+" </div>"
										+"<div style='margin-left:5px;width:180px;float:left;'>时间："+da.time +"</div>"
										+"<div style='margin-top:5px;margin-left:5px;width:180px;float:left;'>模板名称："
										+"<a style='color:blue;' href='mail_preview.action?infoId="+id+"&mode="
										+da.mode+"&adminId="+da.adminId+"' target='_blank'>"+da.description+"</a>"
									+"</div>"
									+"</div>";
						var h = $("#mails").html();
						if(h.indexOf("span") < 0){
							html += h;
						}
						$("#mails").html(html);
						$(".background").addClass("hidden");
					}else{
						alert(da.msg);
						$(".background").addClass("hidden");
					}
				});
			}else{
				alert(da.msg);
			}
		});
	});
	$(".edit_card").live('click',function(){
		var cardId = $(this).parent().attr("cardId");
		$(".card_name_"+cardId).addClass("hidden");
		$(".card_name_edit_"+cardId).val($(".card_name_"+cardId).html());
		$(".card_name_edit_"+cardId).removeClass("hidden");
		$(".card_branch_"+cardId).addClass("hidden");
		$(".card_branch_edit_"+cardId).val($(".card_branch_"+cardId).html());
		$(".card_branch_edit_"+cardId).removeClass("hidden");
		$(".card_phone_"+cardId).addClass("hidden");
		$(".card_phone_edit_"+cardId).val($(".card_phone_"+cardId).html());
		$(".card_phone_edit_"+cardId).removeClass("hidden");
		$(".card_qq_"+cardId).addClass("hidden");
		$(".card_qq_edit_"+cardId).val($(".card_qq_"+cardId).html());
		$(".card_qq_edit_"+cardId).removeClass("hidden");
		$(".card_gender_"+cardId).addClass("hidden");
		$(".card_gender_edit_"+cardId).val($(".card_gender_"+cardId).attr("gender"));
		$(".card_gender_edit_"+cardId).removeClass("hidden");
		$(".card_email_"+cardId).addClass("hidden");
		$(".card_email_edit_"+cardId).val($(".card_email_"+cardId).html());
		$(".card_email_edit_"+cardId).removeClass("hidden");
		$(".card_position_"+cardId).addClass("hidden");
		$(".card_position_edit_"+cardId).val($(".card_position_"+cardId).html());
		$(".card_position_edit_"+cardId).removeClass("hidden");
		$(this).removeClass("edit_card");
		$(this).addClass("edit_card_save");
		$(this).html("保存");
	});
	$(".edit_card_save").live('click', function(){
		var cardId = $(this).parent().attr("cardId");
		var name = $(".card_name_edit_"+cardId).val();
		var branch = $(".card_branch_edit_"+cardId).val();
		var phone = $(".card_phone_edit_"+cardId).val();
		var qq = $(".card_qq_edit_"+cardId).val();
		var gender = $(".card_gender_edit_"+cardId).val();
		var email = $(".card_email_edit_"+cardId).val();
		var position = $(".card_position_edit_"+cardId).val();
		if(email != null && email.trim().length > 1){
			var search_str = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
			if(!search_str.test(email)){
				alert("邮箱格式错误！")
				return;
			}
		}
		$.ajax({
			dataType:'json',
			url:'info_edit_card.action',
			data:{cardId:cardId,name:name,branch:branch,phone:phone,qq:qq,gender:gender,email:email,position:position}
		}).done(function(da){
			if(da.status == 1){
				$(".edit_card_"+da.id).addClass("edit_card");
				$(".edit_card_"+da.id).html("编辑");
				$(".edit_card_"+da.id).removeClass("edit_card_save");
				
				$(".card_name_"+da.id).removeClass("hidden");
				$(".card_name_"+da.id).html(da.name);
				$(".card_name_edit_"+da.id).addClass("hidden");
				
				$(".card_branch_"+da.id).removeClass("hidden");
				$(".card_branch_"+da.id).html(da.branch);
				$(".card_branch_edit_"+da.id).addClass("hidden");
				
				$(".card_phone_"+da.id).removeClass("hidden");
				$(".card_phone_"+da.id).html(da.phone);
				$(".card_phone_edit_"+da.id).addClass("hidden");
				$(".card_qq_"+da.id).removeClass("hidden");
				$(".card_qq_"+da.id).html(da.qq);
				$(".card_qq_edit_"+da.id).addClass("hidden");
				$(".card_gender_"+da.id).removeClass("hidden");
				if(da.gender == 1){
					$(".card_gender_"+da.id).html("男");
				}else{
					if(da.gender == 2){
						$(".card_gender_"+da.id).html("女");
					}else{
						$(".card_gender_"+da.id).html("未选择");
					}
				}
				$(".card_gender_edit_"+da.id).addClass("hidden");
				$(".card_email_"+da.id).removeClass("hidden");
				$(".card_email_"+da.id).html(da.email);
				$(".card_email_edit_"+da.id).addClass("hidden");
				$(".card_position_"+da.id).removeClass("hidden");
				$(".card_position_"+da.id).html(da.position);
				$(".card_position_edit_"+da.id).addClass("hidden");
			}else{
				alert(da.msg);
			}
		});
	});
	
	$("#view_preview_mail").live('click', function(){
		var id = $(this).attr("val1");
		var mode = $("#view_mail_mode").val();
		window.open("mail_preview.action?infoId="+id+"&mode="+mode);
	});
	
	$("#add_mail_window_close").live('click', function(){
		$(".background").addClass("hidden");
		$(".mail_add").addClass("hidden");
	});
	
	$("#add_card_tijiao").click(function(){
		var phone = $("#add_card_phone").val();
		var email = $("#add_card_email").val();
		var name = $("#add_card_name").val();
		var gender = $("#add_card_gender").val();
		var branch = $("#add_card_branch").val();
		var position = $("#add_card_position").val();
		var qq = $("#add_card_qq").val();
		var infoId = $("#view_infoId").val();
		if((phone == null || phone.trim().length <= 0) && (email == null || email.trim().length <= 0) && (qq == null || qq.trim().length <= 0)){
			alert("至少有一种联系方式！");
			return;
		}
		var o = {infoId:infoId,phone:phone, email:email, name:name, gender:gender, branch:branch,position:position,qq:qq};
		$.ajax({
			dataType:'json',
			url:'info_add_card.action',
			data:o
		}).done(function(da){
			if(da.status == "1"){
				$("#add_card_window_close").click();
				var html = "<div class='view_p_content biankuang_gray_ding hand'  cardId='"+da.card.id+"'>"
								+"<div style='margin-left:10px;width:40px;margin-left:370px;position:absolute;color:blue;' class='hand edit_card edit_card_"+da.card.id+"'>编辑</div>";
				html += "<div style='margin-left:10px;width:165px;float:left;'><div style='width:30px;float:left;'>名片:</div>"+da.card.id+"</div>"
							+"<div style='margin-left:10px;width:165px;float:left;'>"
							+"<div style='width:30px;float:left;'>姓名:</div>"
							+"<div class='left card_name_"+da.card.id+"'>"+da.card.name+"</div>"
							+"<input style='width:130px;margin-top:3px' class='left card_name_edit_"+da.card.id+" hidden' type='text'/>"
							+"</div>"
							+"<div style='margin-left:10px;width:165px;float:left;'>"
							+"<div style='width:30px;float:left;'>部门:</div>"
							+"<div class='left card_branch_"+da.card.id+"'>"+da.card.branch+"</div>"
							+"<input style='width:130px;margin-top:3px' class='left card_branch_edit_"+da.card.id+" hidden' type='text'/>"
							+"</div>"
							+"<div style='margin-left:10px;width:165px;float:left;'>"
							+"<div style='width:30px;float:left;'>电话:</div>"
							+"<div class='left card_phone_"+da.card.id+"'>"+da.card.phone+"</div>"
							+"<input style='width:130px;margin-top:3px' class='left card_phone_edit_"+da.card.id+" hidden' type='text'/>"
							+"</div>"
							+"<div style='margin-left:10px;width:165px;float:left;'>"
							+"<div style='width:30px;float:left;'>QQ:</div>"
							+"<div class='left card_qq_"+da.card.id+"'>"+da.card.qq+"</div>"
							+"<input style='width:130px;margin-top:3px' class='left card_qq_edit_"+da.card.id+" hidden' type='text'/>"
							+"</div>"
							+"<div style='margin-left:10px;width:165px;float:left;'>"
							+"<div style='width:30px;float:left;'>性别:</div>"
							+"<div class='left card_gender_"+da.card.id+"' gender="+da.card.gender+">";
				if(da.card.gender == 1){
					html += "男";
				}else{
					if(da.card.gender == 2){
						html += "女";
					}else{
						html += "未选择";
					}
				}
				html +="</div>"
							+"<select style='margin-top:3px;' class='left hidden card_gender_edit_"+da.card.id+"'>"
							+"<option value='0'>不选择</option>"
							+"<option value='1'>男</option>"
							+"<option value='2'>女</option>"
							+"</select>"
							+"</div>"
							+"<div style='margin-left:10px;width:165px;float:left;'>"
							+"<div style='width:30px;float:left;'>职位:</div>"
							+"<div class='left card_position_"+da.card.id+"'>"+da.card.position+"</div>"
							+"<input style='width:130px;margin-top:3px' class='left card_position_edit_"+da.card.id+" hidden' type='text'/>"
							+"</div>"
							+"<div style='margin-left:10px;width:165px;float:left;'><div style='width:30px;float:left;'>来源:</div><div style='color:blue;float:left;'>"+da.card.fromName+"</div></div>"
							+"<div style='margin-left:10px;width:300px;float:left;'>"
							+"<div style='width:30px;float:left;'>邮箱:</div>"
							+"<div class='left card_email_"+da.card.id+"'>"+da.card.email+"</div>"
							+"<input style='width:260px;margin-top:3px' class='left card_email_edit_"+da.card.id+" hidden' type='text'/>"
							+"</div>"
				html +=  "</div>";
				var H = html + $("#cards").html();
				$("#cards").html(H);
				var html2 = "<option value='"+da.card.id+"'>名片："+da.card.id+"</option>";
				$("#add_call_card").append(html2);
				$("#add_card_phone").val(null);
				$("#add_card_email").val(null);
				$("#add_card_name").val(null);
				$("#add_card_gender").val(0);
				$("#add_card_branch").val(null);
				$("#add_card_position").val(null);
				$("#add_card_qq").val(null);
			}else{
				alert(da.msg)
			}
		});
	});
	$(".app_data_view").live("click", function(){
		var appId = $(this).parent().parent().attr('val');
		var agent = $(this).parent().attr("val1");
		var agentId = $(this).parent().attr("val2");
		$(this).addClass("app_data_close");
		$(this).removeClass("app_data_view");
		$.ajax({
			dataType:'json',
			url:'info_appMap.action',
			data:{appId:appId,agent:agent,agentId:agentId}
		}).done(function(da){
			if(da.status == 1){
				var array = da.datas;
				var html = "";
				for(var i=0;i<array.length;i++){
					html += "<div>"+array[i].time+"</div>";
				}
				$(".app_data_view_content_"+da.appId+"_"+da.agent+"_"+da.agentId).html(html);
			}else{
				alert(da.msg);
			}
		});
	});
	$(".app_data_close").live("click", function(){
		var appId = $(this).parent().parent().attr('val');
		var agent = $(this).parent().attr("val1");
		var agentId = $(this).parent().attr("val2");
		$(this).addClass("app_data_view");
		$(this).removeClass("app_data_close");
		$(".app_data_view_content_"+appId+"_"+agent+"_"+agentId).html("");
	});
	$(".task_remove").live("click", function(){
		var ids = $(this).parent().attr('id');
		var id = ids.split("_");
		$.ajax({
			dataType:'json',
			url:'task_remove.action',
			data:{id:id[2]}
		}).done(function(da){
			if(da.status == 1){
				$("#task_view_"+da.id).parent().remove();
			}else{
				alert(da.msg);
			}
		});
	});
	
	/*$(".view_p_button").mouseover(function(){
		$(this).addClass("color_blue");
	});
	$(".view_p_button").mouseleave(function(){
		$(this).removeClass("color_blue");
	});*/
	$("#add_call_tijiao").click(function(){
		var type = $("#add_record_type_"+note).val();
		if(type <= 0){
			alert("请选择类型");
			return;
		}
		var cardId = $("#add_call_card").val();
		var mark = $("#add_call_mark").val();
		mark=mark.replace(/\n|\r\n/g,"<br>");
		/*var qq = $("#call_add_qq").val();
		var gongdan = $("#call_add_gongdan").val();
		
		var infoId = $("#view_infoId").val();
		var name = $("#call_add_name").val();
		var phone = $("#call_add_phone").val();
		var email = $("#call_add_email").val();
		var gender = $("#call_add_gender").val();
		
		var position = $("#call_add_position").val();
		var branch = $("#call_add_branch").val();*/
		var call_add_time = $("#call_add_time").val();
		var groupId = $("#group_id").html();
		var call_add_point = document.getElementById("call_add_point").checked;
		/*var o = {cardId:cardId, mark:mark,infoId:infoId,recordType:type,qq:qq,gongdan:gongdan,name:name,
				phone:phone,email:email,gender:gender,add_time:call_add_time,branch:branch,position:position,add_call_point:call_add_point};*/
		var u = {cardId:cardId, groupId:groupId, add_call_point:call_add_point, mark:mark ,recordType:type, add_time:call_add_time};
		$.ajax({
			dataType:'json',
			url:'user_group_add_call.action',
			data:u
		}).done(function(da){
			if(da.status == "1"){
				$("#add_call_window_close").click();
				var html = "<div class='view_p_content biankuang_gray_ding'>"
						+"<div style='margin-left:5px;width:175px;float:left;'>操作:"+da.call.adminName+"</div>"
						+"<div style='margin-left:5px;width:180px;float:left;'>时间:"+da.call.callTime+"</div>";
				if(da.call.cardId > 1){
					html += "<div style='margin-left:5px;width:175px;float:left;'>名片:"+da.call.cardId +"</div>";
				}else{
					if(da.call.cardId == 1){
						html += "<div style='margin-left:5px;width:175px;float:left;'>名片:注册</div>";
					}else{
						html += "<div style='margin-left:5px;width:175px;float:left;'>名片:无</div>";
					}
				}
				if(da.call.typeName != null){
					html += "<div style=margin-left:5px;width:180px;float:left;>类型:"+da.call.typeName+"</div>";
				}else{
					html += "<div style=margin-left:5px;width:180px;float:left;>类型:未记录</div>";
				}
				if(da.call.gongdan != null && da.call.gongdan > 0){
					html += "<div style=margin-left:5px;width:175px;float:left;>工单:"+da.call.gongdan+"</div>";
				}
				if(da.call.todu > 0){
					html += "<div style=margin-left:5px;width:175px;float:left;>指令:";
					switch(da.call.todu){
						case 1000:html +="<span style='color:blue;'>推送销售</span>";break;
						case 1001:html +="<span style='color:blue;'>技术支持</span>";break;
						case 1002:html +="<span style='color:blue;'>再联系</span>";break;
						case 1003:html +="<span style='color:blue;'>关闭</span>";
							$(".view_edit_tag_metric").removeClass("biankuang_blue_ding");
							$(".view_edit_tag_metric").addClass("biankuang_white_ding");
							$("#view_edit_tag_metric_4").removeClass("biankuang_white_ding");
							$("#view_edit_tag_metric_4").addClass("biankuang_blue_ding");
							break;
						default:case 1000:html +="<span style='color:blue;'>不明</span>";break;;
					}
					html +="</div>";
				}
						html += "<div style='margin-left:5px;width:350px;float:left;'>记录：</div>"
						+"<div style='margin-left:20px;width:350px;float:left;'>"+da.call.mark+"</div>"
						+"</div>";
				var h = $("#calls").html();
				$("#calls").html(html+h);
				if(da.card != null){
					var html = "<div class='view_p_content biankuang_gray_ding hand'  cardId='"+da.card.id+"'>"
					+"<div style='margin-left:10px;width:40px;margin-left:370px;position:absolute;color:blue;' class='hand edit_card edit_card_"+da.card.id+"'>编辑</div>";
					html += "<div style='margin-left:10px;width:165px;float:left;'><div style='width:30px;float:left;'>名片:</div>"+da.card.id+"</div>"
								+"<div style='margin-left:10px;width:165px;float:left;'>"
								+"<div style='width:30px;float:left;'>姓名:</div>"
								+"<div class='left card_name_"+da.card.id+"'>"+da.card.name+"</div>"
								+"<input style='width:130px;margin-top:3px' class='left card_name_edit_"+da.card.id+" hidden' type='text'/>"
								+"</div>"
								+"<div style='margin-left:10px;width:165px;float:left;'>"
								+"<div style='width:30px;float:left;'>部门:</div>"
								+"<div class='left card_branch_"+da.card.id+"'>"+da.card.branch+"</div>"
								+"<input style='width:130px;margin-top:3px' class='left card_branch_edit_"+da.card.id+" hidden' type='text'/>"
								+"</div>"
								+"<div style='margin-left:10px;width:165px;float:left;'>"
								+"<div style='width:30px;float:left;'>电话:</div>"
								+"<div class='left card_phone_"+da.card.id+"'>"+da.card.phone+"</div>"
								+"<input style='width:130px;margin-top:3px' class='left card_phone_edit_"+da.card.id+" hidden' type='text'/>"
								+"</div>"
								+"<div style='margin-left:10px;width:165px;float:left;'>"
								+"<div style='width:30px;float:left;'>QQ:</div>"
								+"<div class='left card_qq_"+da.card.id+"'>"+da.card.qq+"</div>"
								+"<input style='width:130px;margin-top:3px' class='left card_qq_edit_"+da.card.id+" hidden' type='text'/>"
								+"</div>"
								+"<div style='margin-left:10px;width:165px;float:left;'>"
								+"<div style='width:30px;float:left;'>性别:</div>"
								+"<div class='left card_gender_"+da.card.id+"' gender="+da.card.gender+">";
					if(da.card.gender == 1){
						html += "男";
					}else{
						if(da.card.gender == 2){
							html += "女";
						}else{
							html += "未选择";
						}
					}
					html +="</div>"
								+"<select style='margin-top:3px;' class='left hidden card_gender_edit_"+da.card.id+"'>"
								+"<option value='0'>不选择</option>"
								+"<option value='1'>男</option>"
								+"<option value='2'>女</option>"
								+"</select>"
								+"</div>"
								+"<div style='margin-left:10px;width:165px;float:left;'>"
								+"<div style='width:30px;float:left;'>职位:</div>"
								+"<div class='left card_position_"+da.card.id+"'>"+da.card.position+"</div>"
								+"<input style='width:130px;margin-top:3px' class='left card_position_edit_"+da.card.id+" hidden' type='text'/>"
								+"</div>"
								+"<div style='margin-left:10px;width:165px;float:left;'><div style='width:30px;float:left;'>来源:</div><div style='color:blue;float:left;'>"+da.card.fromName+"</div></div>"
								+"<div style='margin-left:10px;width:300px;float:left;'>"
								+"<div style='width:30px;float:left;'>邮箱:</div>"
								+"<div class='left card_email_"+da.card.id+"'>"+da.card.email+"</div>"
								+"<input style='width:260px;margin-top:3px' class='left card_email_edit_"+da.card.id+" hidden' type='text'/>"
								+"</div>"
					html +=  "</div>";
					var H = html + $("#cards").html();
					$("#cards").html(H);
					var html2 = "<option value='"+da.card.id+"'>名片："+da.card.id+"</option>";
					$("#add_call_card").append(html2);
				}
			}else{
				alert(da.msg)
			}
		});
	});
});

