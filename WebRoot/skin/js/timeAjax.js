$(document).ready(function(){
	$(".check").click(function(){
		var id = $(this).attr("id");
		if(document.getElementById(id).checked){
			$("#"+id+"_select").html(show(id));
		}else{
			$("#"+id+"_select").html("");
		}
	});
	
	$(".search_button").live('click', function(){
		var o = {};
		var company = $("#search_company").val();
		var name = $("#search_name").val();
		var email = $("#search_email").val();
		var phone = $("#search_phone").val();
		var appId = $("#search_appId").val();
		var agent = $("#search_appId_agent").val();
		/*var metric = $("#search_metric").val();
		var loudou = $("#search_loudou").val();*/
		/*var language = $("#search_language").val();*/
		var qq = $("#search_qq").val();
		var userId = $("#search_userId").val();
		if((company == null || company.trim()=="") && (name == null || name.trim()=="") &&
				(email == null || email.trim()=="") && (phone == null || phone.trim()=="") && (qq == null || qq.trim()=="")
				&& (userId == null || userId.trim()=="") &&(appId == null || appId.trim()=="")  ){
			$(".search_result_msg").html("请输入搜索条件");
			return;
		}
		o["company"] = company;
		o["name"] = name;
		o["email"] = email;
		o["phone"] = phone;
		o["qq"] = qq;
		o["userId"] = userId;
		o["appId"] = appId;
		o["agent"] = agent;
		$(".search_result_msg").html("搜索中……");
		$(this).removeClass('search_button');
		$(this).addClass('search_button_un');
		$(this).val("搜索中");
		$.ajax({
			dataType:'json',
			url:'user_group_searchOut.action',
			data:o
		}).done(function(da){
			if(da.status == 1){
				INFO = da.userGroupsList;
				var userGroupsList = da.userGroupsList;
				var html = "";
				var name = $("#search_name").val();
				for(var i=0;i<userGroupsList.length;i++){
					if(userGroupsList[i].level == 2){
						html += "<tr class='hand' style='color:#00FF00;'  val1='"+userGroupsList[i].groupId+"'> "
					}else{
						html += "<tr class='hand' val1='"+userGroupsList[i].groupId+"'> "
					}
					html +="<td></td> "
						+"<td>"+userGroupsList[i].groupId+"</td> "
						+"<td>"+userGroupsList[i].name+"</td> "
						+"<td>"+userGroupsList[i].company+"</td> "
						+"<td>"+userGroupsList[i].project+"</td> "
						+"<td>"+userGroupsList[i].comming+"</td> "
						+"<td>"+userGroupsList[i].saleName+"</td>"
						+"<td>"+userGroupsList[i].supportName+"</td>"
						+"<td>"+userGroupsList[i].preSaleName+"</td>" 
						+"<td>";
						html+="<input val1='"+userGroupsList[i].groupId+"' class='check_view'  type='image' src='http://manage.oneapm.com/skin/images/icn_view_users.png' title='查看' />";
					html +="</td></tr>";
				}
				$("#search_result").html(html);
				$(".search_result_msg").html("共搜索到"+da.size+"条信息");
			}else{
				$(".search_result_msg").html("没有搜索到相关信息");
				$("#search_result").html("");
			}
			$(".search_button_un").addClass("search_button");
			$(".search_button_un").removeClass("search_button_un");
			$(".search_button").val("搜索");
		});
	});
	
});

var getDateString = function(id, o){
	if(document.getElementById("mail_"+id).checked){
		o[id+"Start"] =  $("#mail_"+id+"_y_start").val()+"-"+$("#mail_"+id+"_m_start").val()+"-"+$("#mail_"+id+"_d_start").val()+"  00:00:00";
		o[id+"End"] = $("#mail_"+id+"_y_end").val()+"-"+$("#mail_"+id+"_m_end").val()+"-"+$("#mail_"+id+"_d_end").val()+" 00:00:00";
	}
	return o;
}
var show = function(id){
	var date = new Date();
	var y = date.getYear();
	var m = date.getMonth();
	var d = date.getDate();
	var html = "";
		html += getYear(id+"_y_start",2014);
		html += getMonth(id+"_m_start",1);
		html += getDay(id+"_d_start",1,1);
		html += "&nbsp;到&nbsp;";
		html += getYear(id+"_y_end",y);
		html += getMonth(id+"_m_end",m);
		html += getDay(id+"_d_end",m,d);
		html += "</div>";
		return html;
}
var getYear = function(id,checked){
	var html = "<select class='hand' style='height:20px;width:60px;font-size:11px;' id='"+id+"'>";
	var y = checked%100;
	for(var i=12;i<15;i++){
		if(i==y){
			html += "<option class='hand' selected value='20"+i+"'>20"+i+"</option>";
		}else{
			html += "<option class='hand' value='20"+i+"'>20"+i+"</option>";
		}
	}
	html += "</select>年";
	return html;
}

var getMonth = function(id, checked){
	var html = "<select class='hand' style='height:20px;width:40px;font-size:11px;' id='"+id+"'>";
	for(var i=1;i<13;i++){
		if(i==checked){
			if(i<10){
				html += "<option class='hand' selected value='0"+i+"'>"+i+"</option>";
			}else{
				html += "<option class='hand' selected value='"+i+"'>"+i+"</option>";
			}
		}else{
			html += "<option value='"+i+"'>"+i+"</option>";
		}
	}
	html += "</select>月";
	return html;
}

var getDay = function(id, month, checked){
	var html = "<select class='hand' style='height:20px;width:40px;font-size:11px;' id='"+id+"'>";
	var days = getDays(month, new Date().getFullYear());
	days ++;
	for(var i=1;i<days;i++){
		if(i==checked){
			if(i<10){
				html += "<option class='hand' selected value='0"+i+"'>"+i+"</option>";
			}else{
				html += "<option class='hand' selected value='"+i+"'>"+i+"</option>";
			}
		}else{
			html += "<option value='"+i+"'>"+i+"</option>";
		}
	}
	html += "</select>日";
	return html;
}

var getDays = function(month,year){
	switch(month){
		case 1:return 31;
		case 2:
			if(0==this.getYear()%4&&((this.getYear()%100!=0)||(this.getYear()%400==0))){
				return 29;
			}else{
				return 28;
			};
		case 3:return 31;
		case 4:return 30;
		case 5:return 31;
		case 6:return 30;
		case 7:return 31;
		case 8:return 31;
		case 9:return 30;
		case 10:return 31;
		case 11:return 30;
		case 12:return 31;
	}
}
