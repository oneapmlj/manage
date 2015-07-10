var liucun_in = true;
var liucun = function(){
	$("#baobiao_sign_type").html("");
	$("#baobiao_type").val(0);
	var agent = $(".agent_value").val();
	$.ajax({
		dataType:'json',
		url:'baobiao_liucun_yunying_data.action',
		data:{agent:agent}
	}).done(function(da){
		if(da.status == 1){
			var list = da.liucun;
			var width1 = list[0].length*82 + 170;
			var html = "<div style='width:1200px;font-size:13px;overflow:scroll;height:500px;' >";
			html += "<div style='width:"+width1+"px;'>";
			html += "<div style='width:"+width1+"px;min-width:1200px;float:left;'>"
						+"<div style='margin-left:0px;margin-top:0px;width:160px;height:30px;line-height:30px;float:left;' class='public_table_line_2'>时间</div>"
						+"<div style='margin-top:0px;float:left;width:80px;height:30px;line-height:30px;' class='public_table_line_2'>初始数据</div>";
			for(var i=1;i<list[0].length;i++){
				html+="<div style='margin-top:0px;float:left;width:80px;height:30px;line-height:30px;font-size:16px;' " 
							+"class='public_table_line_2 hand NUMBER' number="+i+">"
							+i
							+"</div>";
			}
			html +="</div>";
			for(var i=0;i<list.length;i++){
				var width = width1;
				html += "<div style='width:"+width+"px;min-width:1200px;float:left;'>"
				html += "<div style='margin-left:0px;width:160px;height:30px;line-height:30px;float:left;' class='public_table_line_2 hand HANG'"
							+" id='"+list[i][0].dataTimeStart+"-"+list[i][0].dataTimeEnd+"'><span>"
							+list[i][0].dataTimeStart+"-"+list[i][0].dataTimeEnd
							+"</span></div>";
				html+="<div style='float:left;width:80px;height:30px;line-height:30px;' " 
					+"class='public_table_line_2 hand click_to_view hang_"+list[i][0].dataTimeStart+"-"+list[i][0].dataTimeEnd+"'"
					+" number='"+list[i][0].number+"' dataTime='"+list[i][0].dataTimeStart+"' val='"+list[i][0].percent+"'"
					+" from='"+list[i][0].from_time+"'><span>"
					+list[i][0].count 
					+"</span></div>";
				for(var j=1;j<list[i].length;j++){
					html+="<div style='float:left;width:80px;height:30px;line-height:30px;' " 
								+"class='public_table_line_2 hand click_to_view number_"+list[i][j].number+" hang_"+list[i][0].dataTimeStart+"-"+list[i][0].dataTimeEnd+"'"
								+" dataTime='"+list[i][j].dataTimeStart+"' val='"+list[i][j].percent+"'"
								+" from='"+list[i][j].from_time+"'><span>"
								+list[i][j].count+"/" 
								+list[i][j].percent+"%"
								+"</span></div>";
				}
				html += "</div>"
			}
			html+="</div></div>";
			$(".tabs_involved").html("用户留存率");
			$("#content").html(html);
			var id = $("#baobiao_hang").val();
			if(id != null && id.length > 1){
				$(".hang_"+id).css("background-color", "#87CEFF");
			}
			var p = $("#baobiao_percent").val();
			var percent_value = $(".percent_value").val();
			if(p == 'up'){
				var a = $(".public_table_line_2");
				for(var i=0;i<a.length;i++){
					var percent = $(a[i]).attr('val');
					if(Number(percent) >= Number(percent_value)){
						$(a[i]).css("background-color", "#87CEFF");
					}else{
						$(a[i]).css("background-color", "");
					}
				}
			}
			if(p == 'down'){
				var a = $(".public_table_line_2");
				for(var i=0;i<a.length;i++){
					var percent = $(a[i]).attr('val');
					if(Number(percent) < Number(percent_value)){
						$(a[i]).css("background-color", "#87CEFF");
					}else{
						$(a[i]).css("background-color", "");
					}
				}
			}
		}else{
			alert(da.msg);
		}
	});
}
var zhuanhua = function(){
	var agent = $(".agent_value").val();
	$.ajax({
		dataType:'json',
		url:'baobiao_liucun_yunying_sign.action',
		data:{agent:agent}
	}).done(function(da){
		if(da.status == 1){
			var list = da.liucun;
			var width1 = list[0].length*60 + 170;
			var html = "<div style='width:1200px;margin-left:5px;font-size:13px;overflow:scroll;'>";
			html += "<div style='width:"+width1+"px;min-width:1200px;float:left;'>"
						+"<div style='margin-left:0px;margin-top:0px;width:160px;height:30px;line-height:30px;float:left;' class='public_table_line_2'>时间</div>"
						+"<div style='margin-top:0px;float:left;width:60px;height:30px;line-height:30px;' class='public_table_line_2'>初始数据</div>";
			for(var i=1;i<list[0].length;i++){
				var j = i-1;
				html+="<div style='margin-top:0px;float:left;width:60px;height:30px;line-height:30px;font-size:16px;' " 
							+"class='public_table_line_2 hand NUMBER' number='"+i+"'>"
							+j
							+"</div>";
			}
			html +="</div>";
			for(var i=0;i<list.length;i++){
				var width = list[i].length*60 + 170;
				html += "<div style='width:"+width+"px;min-width:100%;float:left;'>"
				html += "<div style='margin-left:0px;width:160px;height:40px;line-height:40px;float:left;' class='public_table_line_2 hand HANG'"
							+" id='"+list[i][0].signTimeStart+"-"+list[i][0].signTimeEnd+"'>"
							+list[i][0].signTimeStart+"-"+list[i][0].signTimeEnd
							+"</div>";
				html+="<div style='float:left;width:60px;height:40px;line-height:42px;' " 
					+"class='public_table_line_2 hand click_to_view_sign hang_"+list[i][0].signTimeStart+"-"+list[i][0].signTimeEnd+"'"
					+" number='"+list[i][0].number+"' dataTime='"+list[i][0].signTimeStart+"' val='"+list[i][0].percent+"'"
					+" from='"+list[i][0].from_time+"'>"
					+list[i][0].count_download 
					+"</div>";
				for(var j=1;j<list[i].length;j++){
					html+="<div style='width:60px;height:40px;line-height:20px;font-size:10px;float:left;' class='public_table_line_2 hand number_"+list[i][j].number_download+" hang_"+list[i][0].signTimeStart+"-"+list[i][0].signTimeEnd+"' dataTime='"+list[i][j].signTimeStart+"' from="+list[i][0].from_time+">"
								+"<div style='width:60px;height:20px;line-height:20px;float:left;' class='public_table_line hand click_to_view_sign_download' val='"+list[i][j].percent_download+"'>"
								+list[i][j].count_download+"/" +list[i][j].percent_download+"%" +"</div>"
								+"<div style='width:60px;height:20px;line-height:20px;float:left;' class='public_table_line hand click_to_view_sign_app' val='"+list[i][j].percent_app+"'>"
								+list[i][j].count_app+"/" +list[i][j].percent_app+"%" +"</div>"
								+"</div>";
				}
			}
			html+="</div>";
			
			$(".tabs_involved").html("用户转化率");
			$("#content").html(html);
			var type = $("#baobiao_type").val();
			var h = "";
			if(type == '2'){
				h += "<div style='float:left;width:45px;'  class='fenlei fenlei_2 view biankuang_blue_ding menu_button_2' fenlei='2'>下载</div>";
				$(".click_to_view_sign_download").css("background-color", "#87CEFF");
			}else{
				h += "<div style='float:left;width:45px;'  class='fenlei fenlei_2 view biankuang_gray menu_button_2' fenlei='2'>下载</div>";
			}
			if(type == '3'){
				h += "<div style='float:left;width:45px;'  class='fenlei fenlei_3 view biankuang_blue_ding menu_button_2' fenlei='3'>添加</div>";
				$(".click_to_view_sign_app").css("background-color", "#87CEFF");
			}else{
				h += "<div style='float:left;width:45px;'  class='fenlei fenlei_3 view biankuang_gray menu_button_2' fenlei='3'>添加</div>";
			}
			$("#baobiao_sign_type").html(h);
			var id = $("#baobiao_hang").val();
			if(id != null && id.length > 1){
				$(".hang_"+id).css("background-color", "#87CEFF");
			}
			var p = $("#baobiao_percent").val();
			var percent_value = $(".percent_value").val();
			if(p == 'up'){
				var a = $(".public_table_line");
				for(var i=0;i<a.length;i++){
					var percent = $(a[i]).attr('val');
					if(Number(percent) >= Number(percent_value)){
						$(a[i]).css("background-color", "#87CEFF");
					}else{
						$(a[i]).css("background-color", "");
					}
				}
			}
			if(p == 'down'){
				var a = $(".public_table_line");
				for(var i=0;i<a.length;i++){
					var percent = $(a[i]).attr('val');
					if(Number(percent) < Number(percent_value)){
						$(a[i]).css("background-color", "#87CEFF");
					}else{
						$(a[i]).css("background-color", "");
					}
				}
			}
		}else{
			alert(da.msg);
		}
	});
}
var download = function(){
	liucun();
};
$(document).ready(function(){
	$(".percent_up").live('click', function(){
		$("#baobiao_percent").val('up');
		var a = $(".public_table_line");
		var percent_value = $(".percent_value").val();
		for(var i=0;i<a.length;i++){
			var percent = $(a[i]).attr('val');
			if(Number(percent) >= Number(percent_value)){
				$(a[i]).css("background-color", "#87CEFF");
			}else{
				$(a[i]).css("background-color", "");
			}
		}
		var a1 = $(".public_table_line_2");
		for(var i=0;i<a1.length;i++){
			var percent = $(a1[i]).attr('val');
			if(Number(percent) >= Number(percent_value)){
				$(a1[i]).css("background-color", "#87CEFF");
			}else{
				$(a1[i]).css("background-color", "");
			}
		}
	});
	$(".percent_down").live('click', function(){
		$("#baobiao_percent").val('down');
		var a = $(".public_table_line");
		var percent_value = $(".percent_value").val();
		for(var i=0;i<a.length;i++){
			var percent = $(a[i]).attr('val');
			if(Number(percent) < Number(percent_value)){
				$(a[i]).css("background-color", "#87CEFF");
			}else{
				$(a[i]).css("background-color", "");
			}
		}
	});
	$(".quse").live('click', function(){
		$(".public_table_line").css("background-color", "");
		$(".public_table_line_2").css("background-color", "");
		$("#baobiao_type").val(0);
		$("#baobiao_hang").val(0);
		$("#baobiao_percent").val(0);
	});
	$(".zongxiang").live('click', function(){
		$(".kong").removeClass("hidden");
		$("#baobiao_fangxiang").val("zongxiang");
		$(this).removeClass("biankuang_gray");
		$(this).addClass("biankuang_blue_ding");
		$(".hengxiang").removeClass("biankuang_blue_ding");
		$(".hengxiang").addClass("biankuang_gray");
	});
	$(".hengxiang").live('click', function(){
		$(".kong").addClass("hidden");
		$("#baobiao_fangxiang").val("hengxiang");
		$(this).removeClass("biankuang_gray");
		$(this).addClass("biankuang_blue_ding");
		$(".zongxiang").removeClass("biankuang_blue_ding");
		$(".zongxiang").addClass("biankuang_gray");
	});
	$(".HANG").live('click', function(){
		var id = $(this).attr('id');
		$(".public_table_line").css("background-color", "");
		$(".public_table_line_2").css("background-color", "");
		$(".hang_"+id).css("background-color", "#87CEFF");
	});
	$(".NUMBER").live('click', function(){
		var number = $(this).attr('number');
		$(".public_table_line").css("background-color", "");
		$(".public_table_line_2").css("background-color", "");
		$(".number_"+number).css("background-color", "#87CEFF");
	});
	$(".fenlei").live('click', function(){
		var fenlei = $(this).attr("fenlei");
		$(".public_table_line").css("background-color", "");
		$(".public_table_line_2").css("background-color", "");
		$("#baotiao_type").val(fenlei);
		$(".fenlei").removeClass("biankuang_blue_ding");
		$(".fenlei").addClass("biankuang_gray");
		$("#baobiao_type").val(fenlei);
		switch(fenlei){
			case '0':
				$(".fenlei_0").addClass("biankuang_blue_ding");
				break;
			case '2':
				$(".click_to_view_sign_download").css("background-color", "#87CEFF");
				$(".fenlei_2").addClass("biankuang_blue_ding");
				break;
			case '3':
				$(".click_to_view_sign_app").css("background-color", "#87CEFF");
				$(".fenlei_3").addClass("biankuang_blue_ding");
				break;
			default:break;
		}
	});
	$(".click_to_view").live('click', function(){
		var dataTime = $(this).attr('dataTime');
		var from= $(this).attr('from');
		var agent = $(".agent_value").val();
		window.open("http://manage.oneapm.com/baobiao_liucun_yunying_view.action?from="+from+"&dataTime="+dataTime+"&agent="+agent);
	});
	$(".click_to_view_sign").live('click', function(){
		var dataTime = $(this).parent().attr('dataTime');
		var from= $(this).parent().attr('from');
		var agent = $(".agent_value").val();
		window.open("http://manage.oneapm.com/baobiao_sign_yunying_view.action?from="+from+"&dataTime="+dataTime+"&agent="+agent);
	});
	$(".click_to_view_sign_download").live('click', function(){
		var dataTime = $(this).parent().attr('dataTime');
		var from= $(this).parent().attr('from');
		var agent = $(".agent_value").val();
		window.open("http://manage.oneapm.com/baobiao_download_yunying_view.action?from="+from+"&dataTime="+dataTime+"&agent="+agent);
	});
	$(".click_to_view_sign_app").live('click', function(){
		var dataTime = $(this).parent().attr('dataTime');
		var from= $(this).parent().attr('from');
		var agent = $(".agent_value").val();
		window.open("http://manage.oneapm.com/baobiao_app_yunying_view.action?from="+from+"&dataTime="+dataTime+"&agent="+agent);
	});
	
	$(".liucun").live('click', function(){
		$(".view").removeClass("biankuang_blue_ding");
		$(".view").addClass("biankuang_gray");
		liucun();
		$(".liucun").removeClass("biankuang_gray");
		$(".liucun").addClass("biankuang_blue_ding");
		liucun_in = true;
	});
	
	$(".zhuanhua").live('click', function(){
		$(".view").removeClass("biankuang_blue_ding");
		$(".view").addClass("biankuang_gray");
		zhuanhua();
		$(".zhuanhua").removeClass("biankuang_gray");
		$(".zhuanhua").addClass("biankuang_blue_ding");
		liucun_in = false;
	});
	$(".agent_value").live('change', function(){
		if(liucun_in){
			liucun();
		}else{
			zhuanhua();
		}
	});
});
