var addInfos = function(nowPage,type){
	$("#infos").html("");
	$("#infos").removeClass("hidden");
	$("#calls").addClass("hidden");
	var id = $("#review_id").html();
	$.ajax({
		dataType:'json',
		url:'review_view.action',
		data:{nowPage:nowPage,page:0,type:type,id:id}
	}).done(function(da){
		if(da.status == 1){
			$("#page").html(da.page);
			$("#type").html(da.type);
			$("#account_info_size_total").html("负责用户("+da.sizeTotal);
			$("#account_info_size").html(da.size);
			$("#account_page_now").html(da.pageNow);
			$("#account_page_total").html(da.pageTotal);
			if(da.pageNow > 1){
				$(".page_before").removeClass("hidden");
			}else{
				$(".page_before").addClass("hidden");
			}
			if(da.pageNow < da.pageTotal){
				$(".page_next").removeClass("hidden");
			}else{
				$(".page_next").addClass("hidden");
			}
			var userGroupsList = da.userGroupsList;
			if(userGroupsList != null && userGroupsList.length > 0){
				var html = "";
				for(var i=0;i<userGroupsList.length;i++){
					html += "<div class='view_p_content_850 biankuang_gray_ding' style='font-size:14px;'>"
							+"<div style='margin-left:5px;width:540px;float:left;'>公司："+userGroupsList[i].company+"</div>";
					if(userGroupsList[i].groupId != null){
						html += "<div style='width:240px;float:left;'>ID："+userGroupsList[i].groupId+"</div>";
					}else{
						html += "<div style='width:240px;float:left;'>ID：无</div>";
					}
					html	+= "<div style='width:60px;float:left;'>"
							+"	<input val1='"+userGroupsList[i].groupId+"' class='check_view'  type='image' src='http://manage.oneapm.com/skin/images/icn_view_users.png' title='查看' />"
							+"</div>"
							if(userGroupsList[i].createTime != null){
								html += "<div style='margin-left:5px;width:270px;float:left;'>注册："+userGroupsList[i].createTime+"</div>";
							}else{
								html += "<div style='margin-left:5px;width:270px;float:left;'>注册：</div>";
							}
							
							html +="<div style='width:270px;float:left;'>最近联系："+userGroupsList[i].contectTime+"</div>"
							+"</div>";
				}
				$("#infos").html(html);
			}
		}else{
			alert(da.msg);
		}
	});
}

var addCalls= function(nowPage,type){
	$("#calls").html("");
	$("#infos").addClass("hidden");
	$("#calls").removeClass("hidden");
	var id = $("#review_id").html();
	$.ajax({
		dataType:'json',
		url:'review_view.action',
		data:{nowPage:nowPage,page:1,type:type,id:id}
	}).done(function(da){
		if(da.status == 1){
			$("#page").html(da.page);
			$("#type").html(da.type);
			$("#account_info_size_total").html("总记录数("+da.sizeTotal);
			$("#account_info_size").html(da.size);
			$("#account_page_now").html(da.pageNow);
			$("#account_page_total").html(da.pageTotal);
			if(da.pageNow > 1){
				$(".page_before").removeClass("hidden");
			}else{
				$(".page_before").addClass("hidden");
			}
			if(da.pageNow < da.pageTotal){
				$(".page_next").removeClass("hidden");
			}else{
				$(".page_next").addClass("hidden");
			}
			var calls = da.calls;
			if(calls != null && calls.length > 0){
				var html = "";
				for(var i=0;i<calls.length;i++){
					html += "<div class='view_p_content_850 biankuang_gray_ding hand'>"
								+"<div style='margin-left:5px;width:780px;float:left;overflow: hidden;'>公司："+calls[i].company+"</div>"
								+"<div style='width:60px;float:left;'>";
								if(calls[i].groupId != null){
									html += "<input val1='"+calls[i].groupId+"' class='check_view'  type='image' src='http://manage.oneapm.com/skin/images/icn_view_users.png' title='查看' />";
								}else{
								}
								
								
								html+="</div>"
								+"<div style='margin-left:5px;width:800px;height:20px;float:left;'>时间："+calls[i].callTime+"</div>"
								+"<div style='margin-left:5px;width:800px;height:20px;float:left;'>记录：</div>"
								+"<div style='margin-left:20px;width:700px;float:left;'>"+calls[i].mark+"</div></div>";
				}
				$("#calls").html(html);
			}
		}else{
			alert(da.msg);
		}
	});
}

$(document).ready(function() {
	$(".change_control").live('click', function(){
		var id = $(this).attr('control');
		$.ajax({
			dataType:'json',
			url:'account_update_control.action',
			data:{id:id}
		}).done(function(da){
			if(da.status == 1){
				if(da.control == 1){
					$(".control_"+da.id).removeClass("biankuang_gray_ding");
					$(".control_"+da.id).addClass("biankuang_blue_ding");
				}else{
					$(".control_"+da.id).removeClass("biankuang_blue_ding");
					$(".control_"+da.id).addClass("biankuang_gray_ding");
				}
			}
		});
	});
	$("#add").click(function(){
		var title = $("#title").val();
		var id = $("#mode_id").val();
		var status = $("#status").val();
		var content = $("#content").val();
		$.ajax({
			dataType:'json',
			url:'account_update.action',
			data:{title:title,id:id,status:status,content:content}
		}).done(function(da){
			alert(da.status);
		});
	});
	$(".click_to_add_account").live('click', function(){
		window.open("account_add.action");
	});
	$(".page_next").live("click", function(){
		var nowPage = $("#account_page_now").text();
		var page = $("#page").html();
		var type = $("#type").html();
		switch(page){
			case '0':addInfos(nowPage,type);break;
			case '1':addCalls(nowPage,type);break;
			default:alert("参数错误"+page);break;
		}
	});
	
	$(".page_before").live("click", function(){
		var nowPage = $("#account_page_now").text();
		var page = $("#page").html();
		var type = $("#type").html();
		nowPage = nowPage - 2;
		switch(page){
			case '0':addInfos(nowPage,type);break;
			case '1':addCalls(nowPage,type);break;
			default:alert("参数错误");break;
		}
	});
	
	$(".page_first").live("click", function(){
		var nowPage = 0;
		var page = $("#page").html();
		var type = $("#type").html();
		switch(page){
			case '0':addInfos(nowPage,type);break;
			case '1':addCalls(nowPage,type);break;
			default:alert("参数错误");break;
		}
	});
	
	$(".page_last").live("click", function(){
		var nowPage = $("#account_page_total").text();
		var page = $("#page").html();
		var type = $("#type").html();
		nowPage = nowPage - 1;
		switch(page){
			case '0':addInfos(nowPage,type);break;
			case '1':addCalls(nowPage,type);break;
			default:alert("参数错误");break;
		}
	});
	
	$(".account").live('click',function(){
		$(".account").removeClass("biankuang_blue_ding");
		$(".account").addClass("biankuang_gray");
		$(".account_info").removeClass("biankuang_blue_ding");
		$(".account_info").addClass("biankuang_gray");
		$(this).removeClass("biankuang_gray");
		$(this).addClass("biankuang_blue_ding");
		var type = $(this).attr("val1");
		$("#type").html(type);
		var type = $("#type").html();
		var page = $("#page").html();
		$("#tab_3").addClass("hidden");
		$("#tab_1").removeClass("hidden");
		switch(page){
			case '0':addInfos(0,type);break;
			case '1':addCalls(0,type);break;
			default:alert("参数错误");break;
		}
	});
	
	$(".account_menu").live("click", function(){
		$(".account_menu").removeClass("biankuang_blue_ding");
		$(".account_menu").addClass("biankuang_gray");
		$(this).removeClass("biankuang_gray");
		$(this).addClass("biankuang_blue_ding");
	});
	$(".infos").live("click", function(){
		var type = $("#type").html();
		addInfos(0,type);
	});
	$(".calls").live("click", function(){
		var type = $("#type").html();
		addCalls(0,type);
	});
	$(".review").live('click', function(){
		var adminType = $("#adminType").val();
		var ids = $(this).attr('id').split("_");
		var id = ids[1];
		window.location.href="review.action?id="+id+"&adminType="+adminType; 
	});
});
