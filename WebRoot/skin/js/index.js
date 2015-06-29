var id = null;
var page_show = function(){
	var totalPage = new Number($(".totalPage").text());
	var nowPage = new Number($(".nowPage").text());
	var html = "";
	html += "<div class='pages'>";
	if(totalPage == 1){
		return;
	}
	if(nowPage == 1){
		html = createButton(1,true);
	}else{
		html = createButton(1,false);
	}
	var start = nowPage - 3;
	var space = false;
	var end = nowPage + 3;
	if(start <= 1){
		var index = 1 - start;
		start = 2;
		while(end < totalPage && index > 0){
			end ++;
			index --;
		}
	}else{
		html += "....";
		start ++;
	}
	if(end >= totalPage){
		var index = end - totalPage;
		end = totalPage;
		while(index > 0 && start > 2){
			start --;
			index --;
		}
	}else{
		space = true;
		end --;
	}
	for(var i=start;i<=end;i++){
		if(i==nowPage){
			html += createButton(i, true);
		}else{
			html += createButton(i, false);
		}
	}
	if(space){
		html += "...."+createButton(totalPage, false);
	}
	if(nowPage > 1){
		html += "<input class='hand before' type='button' value='上一页' />";
	}
	if(nowPage < totalPage){
		html += "&nbsp;<input class='hand next' type='button' value='下一页' />";
	}
	html += "&nbsp;第<input type='text' class='input' value='1'/>页";
	html += "<button class='hand go center' type='button' >GO</button>";
	$(".page").html(html);
}
createButton = function(page, now){
	var html;
	if(page < 10){
		html = "<input class='p1 pb hand' type='button' value='"+page+"'/>";
	}
	if(page < 100 && page >= 10){
		html = "<input class='p2 pb hand' type='button' value='"+page+"'/>";
	}
	if(page >= 100){
		html = "<input class='p3 pb hand' type='button' value='"+page+"'/>";
	}
	if(now){
		html = html.replace("hand","hand blue");
	}
	return html;
}
page_show();
var check = function(){
	var code_Values = document.getElementsByName("code_Value");
	var code = document.getElementsByName("checkAll");
	if(code[0].checked == true){
		for(var i = 0;i < code_Values.length;i++){ 
			code_Values[i].checked = true;
		}
	}else{
		for(var i = 0;i < code_Values.length;i++){ 
			code_Values[i].checked = false;
		}
	}
}

$(document).ready(function() {
	$(".index_language").live('click', function(){
		var id = $("#index_id").val();
		var language = $(this).attr("id");
		window.location.href="show.action?id="+id+"&"+language;
	});
	$(".check_view").live({
	   mouseenter:
		   function(){
		   return;
		   		var infoId = $(this).attr("val2");
		   		if(infoId == null){
		   			var userId = $(this).attr("val1");
		   			$("#view_window_id").html(userId);
		   			$("#calls").html("<div style='margin:30px 0 0 100px;font-size:20px;'>正在加载……………</div>");
		   			$("#mails").html("<div style='margin:30px 0 0 100px;font-size:20px;'>正在加载……………</div>");
		   			$("#view_window").removeClass("hidden");
		   			$.ajax({
		   				dataType:'json',
		   				url:'info_view_json.action',
		   				data:{userId:userId}
		   			}).done(function(da){
		   				if(da.status == 1){
		   					var info = da.info;
		   					var id = $("#view_window_id").html();
		   					if(info == null || info.userId != id){
		   						return;
		   					}
		   					if(info.calls != null && info.calls.length > 0){
		   						var html = "";
		   						var i = 0;
		   						var calls = info.calls;
		   						while(i<calls.length && i<5){
		   							var call = calls[i];
		   							html += "<div class='view_p_content biankuang_gray hand'>"
		   									+"<div style='margin-left:5px;width:180px;float:left;'>时间："+call.callTime+"</div>"
		   									+"<div style='margin-left:5px;width:170px;float:left;'>名片："+call.cardName+"</div>"
				   							+"<div style='margin-left:5px;width:180px;float:left;'>电话："+call.phone+"</div>"
				   							+"<div style='margin-left:5px;width:170px;float:left;>记录：</div>"
				   							+"<div style='margin-left:20px;width:350px;float:left;'>";
		   							if(call.mark == null){
		   								html += "无";
		   							}else{
		   								html+= call.mark;
		   							}
		   							html+= "</div></div>";
		   							i++;
		   						}
		   						$("#calls").html(html);
		   					}else{
		   						$("#calls").html("<div style='margin:30px 0 0 100px;font-size:20px;'>无数据</div>");
		   					}
		   					
		   					if(info.mails != null && info.mails.length > 0){
		   						var html = "";
		   						var i = 0;
		   						var mails = info.mails;
		   						while(i<mails.length && i<4){
		   							var mail = mails[i];
		   							html += "<div class='view_p_content biankuang_gray hand'><div style='margin-left:5px;width:170px;float:left;'>模板："+mail.modeName+"</div>";
		   							html += "<div style='margin-left:5px;width:180px;float:left;>时间："+mail.sendTime+"</div></div>";
		   							i++;
		   						}
		   						$("#mails").html(html);
		   					}else{
		   						$("#mails").html("<div style='margin:30px 0 0 100px;font-size:20px;'>无数据</div>");
		   					}
		   				}else{
		   					alert(da.msg);
		   				}
		   			})
		   		}else{
		   			$("#view_window_id").html(infoId);
		   			$("#calls").html("<div style='margin:30px 0 0 100px;font-size:20px;'>正在加载……………</div>");
		   			$("#mails").html("<div style='margin:30px 0 0 100px;font-size:20px;'>正在加载……………</div>");
		   			$("#view_window").removeClass("hidden");
		   			$.ajax({
		   				dataType:'json',
		   				url:'info_view_json.action',
		   				data:{id:infoId}
		   			}).done(function(da){
		   				if(da.status == 1){
		   					var info = da.info;
		   					var id = $("#view_window_id").html();
		   					if(info == null || info.id != id){
		   						return;
		   					}
		   					if(info.calls != null && info.calls.length > 0){
		   						var html = "";
		   						var i = 0;
		   						var calls = info.calls;
		   						while(i<calls.length && i<5){
		   							var call = calls[i];
		   							html += "<div class='view_p_content biankuang_gray hand'>"
		   									+"<div style='margin-left:5px;width:180px;float:left;'>时间："+call.callTime+"</div>"
		   									+"<div style='margin-left:5px;width:170px;float:left;'>名片："+call.cardName+"</div>"
				   							+"<div style='margin-left:5px;width:180px;float:left;'>电话："+call.phone+"</div>"
				   							+"<div style='margin-left:5px;width:170px;float:left;>记录：</div>"
				   							+"<div style='margin-left:20px;width:350px;float:left;'>";
		   							if(call.mark == null){
		   								html += "无";
		   							}else{
		   								html+= call.mark;
		   							}
		   							html+= "</div></div>";
		   							i++;
		   						}
		   						$("#calls").html(html);
		   					}else{
		   						$("#calls").html("<div style='margin:30px 0 0 100px;font-size:20px;'>无数据</div>");
		   					}
		   					
		   					if(info.mails != null && info.mails.length > 0){
		   						var html = "";
		   						var i = 0;
		   						var mails = info.mails;
		   						while(i<mails.length && i<4){
		   							var mail = mails[i];
		   							html += "<div class='view_p_content biankuang_gray hand'><div style='margin-left:5px;width:170px;float:left;'>模板："+mail.modeName+"</div>";
		   							html += "<div style='margin-left:5px;width:180px;float:left;>时间："+mail.sendTime+"</div></div>";
		   							i++;
		   						}
		   						$("#mails").html(html);
		   					}else{
		   						$("#mails").html("<div style='margin:30px 0 0 100px;font-size:20px;'>无数据</div>");
		   					}
		   				}else{
		   					alert(da.msg);
		   				}
		   			})
		   		}
		   },
		   mouseleave:
		   function(){
			   return;
			   $("#view_window").addClass("hidden");
		   }
		});
	$(".check_view").live("click", function(){
			var id = $(this).attr("val1");
			window.open("info_view.action?id="+id);
	});
	
	$(".check_remove").click(function(){
		$(this).parent().parent().remove();
	});
	
	$(".tablesorter").tablesorter();
	$(".checkAll").click(function(){
		check();
	});
	$("ul.tabs li").click(function() {

		$("ul.tabs li").removeClass("active"); //Remove any "active" class
		$(this).addClass("active"); //Add "active" class to selected tab
		$(".tab_content").hide(); //Hide all tab content

		var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content
		$(activeTab).fadeIn(); //Fade in the active ID content
		return false;
	});
	
	$(".pb").click(function(){
		var id = $(".nowId").text();
		var nowPage = $(".nowPage").text();
		var toPage = $(this).attr("value");
		if(nowPage == toPage){
			return;
		}
		var language = $("#index_language").val();
		window.location.href="/show.action?language="+language+"&id="+id+"&nowPage="+toPage;
	});
	$(".before").click(function(){
		var id = $(".nowId").text();
		var toPage = new Number($(".nowPage").text());
		toPage --;
		window.location.href="/show.action?id="+id+"&nowPage="+toPage;
	});
	$(".next").click(function(){
		var id = $(".nowId").text();
		var toPage = new Number($(".nowPage").text());
		toPage ++;
		window.location.href="/show.action?id="+id+"&nowPage="+toPage;
	});
	$(".go").click(function(){
		var id = $(".nowId").text();
		var toPage = new Number($(".input").val());
		var nowPage = new Number($(".nowPage"));
		if(toPage == nowPage){
			return;
		}
		window.location.href="/show.action?id="+id+"&nowPage="+toPage;
	});
});
$(function(){
    $('.column').equalHeight();
});

