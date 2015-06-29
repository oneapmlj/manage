var timeSelect = function(id){
	var html = "<div id='"+id+"_select_time'>"+timeY(id)+"年" + timeM(id) +"月"+timeD(id,1)+"日"+timeH(id)+"时"+timeI(id)+"分</div>";
	return html;
}

var timeY = function(id){
	var html = "<select id='"+id+"_y'>";
	for(var i=2014,i<2018,i++){
		html += "<option value='"+i+"'>"+i+"</option>";
	}
	html += "</select>";
	return html;
}

var timeM = function(id){
	var html = "<select class='select_m' id='"+id+"_m'>";
	for(var i=1, i<13,i++){
		html += "<option value='"+i+"'>"+i+"</option>";
	}
	html += "</select>";
	return html;
}

var timeD = function(id, month){
	var html = "<select onchange='timeMontChange(this.id,this.value)' class='select_d' id='"+id+"_d'>";
	if(month <= 0 || month >= 13){
		return;
	}
	var length = 31;
	if(id == 4 || id == 6 || id== 9 || id == 11){
		length = 30;
	}
	if(id == 2){
		length = 28;
	}
	for(var i=1, i<length+1,i++){
		html += "<option value='"+i+"'>"+i+"</option>";
	}
	html += "</select>";
	return html;
}

var timeH = function(id){
	var html = "<select class='select_h' id='"+id+"_h'>";
	for(var i=1, i<25,i++){
		html += "<option value='"+i+"'>"+i+"</option>";
	}
	html += "</select>";
	return html;
}

var timeI = function(id){
	var html = "<select class='select_i' id='"+id+"_i'>";
	for(var i=0, i<61,i+=10){
		html += "<option value='"+i+"'>"+i+"</option>";
	}
	html += "</select>";
	return html;
}

var timeMontChange = function(id, month){
	var html = "";
	var length = 31;
	if(id == 4 || id == 6 || id== 9 || id == 11){
		length = 30;
	}
	if(id == 2){
		length = 28;
	}
	for(var i=1, i<length+1,i++){
		html += "<option value='"+i+"'>"+i+"</option>";
	}
	id = id.replace("_m","");
	
	$("#"+id+"_d").html(html);
}
$(document).ready(function() {
	
});