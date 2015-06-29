var note = 0;
var atCode = 0;
var atMax = 0;
$(document).ready(function(){
	document.onkeydown=function(){
		if(atMax <= 0){
			return;
		}
		if(window.event.keyCode==38){ // 按 up
			if(atCode <= 2){
				atCode = 1;
			}else{
				atCode --;
			}
			$(".at_view").css("background-color","#C1CDCD");
			$(".at_"+atCode).parent().css("background-color","#B5ACDC");
			return;
		}
		if(window.event.keyCode==40){ // 按 down
			atCode ++;
			if(atMax < atCode){
				atCode --;
				return;
			}
			$(".at_view").css("background-color","#C1CDCD");
			$(".at_"+atCode).parent().css("background-color","#B5ACDC");
			return;
		}
		if(window.event.keyCode==13){ // 按 down
			if(atCode > 0){
				var name = $(".at_"+atCode).html();
				var content = $("#add_call_mark").val();
				content = content.substring(0, content.lastIndexOf("@")+1);
				content += name +" ";
				$("#testtest").html("");
				$("#add_call_mark").val(content);
			}
			return;
		}
	}; 
	$("#notetype_add").live('click', function(){
		var name = $("#notetype_add_text").val();
		if(name == null || name.length <= 0){
			alert("请输入有效的名字");
			return;
		}
		var father = note;
		if(note > 0){
			father = $("#add_record_type_"+(note-1)).val();
		}
		
		var todu = $("#notetype_add_todu").val();
		$.ajax({
			dataType:'json',
			url:'note_insert.action',
			data:{father:father,name:name,todu:todu}
		}).done(function(da){
			if(da.status == 1){
				$("#add_record_type_"+note).append("<option value='"+da.note.id+"'>"+da.note.name+"</option>");
				$("#notetype_add_chiled_father").append("<option value='"+da.note.id+"'>"+da.note.name+"</option>");
				$("#notetype_add_text").val("");
				$("#notetype_add_contact").val(0);
				$("#notetype_add_todu").val(0);
			}else{
				alert(da.msg);
			}
		});
	});
	$("#notetype_add_chiled").live("click", function(){
		var name = $("#notetype_add_text_chiled").val();
		var father = $("#notetype_add_chiled_father").val();
		if(name == null || name.length <= 0){
			alert("请输入有效的名字");
			return;
		}
		if(father <= 0){
			alert("上级选项不明");
			return;
		}
		var todu = $("#notetype_add_todu").val();
		$.ajax({
			dataType:'json',
			url:'note_insert.action',
			data:{father:father,name:name,todu:todu}
		}).done(function(da){
			if(da.status == 1){
				note ++;
				var html = "<div style='margin:5px 0 0 10px;float:left;'>"
					+"	<select class='hand type_change' style='width:100px;' id='add_record_type_"+note+"'>"
					+"<option value='0'>请选择</option>"
					+"<option value='"+da.note.id+"'>"+da.note.name+"</option>";
					html +="</select>	</div>";
					$("#selects").append(html);
					var html2 = "<option value='"+da.note.id+"'>"+da.note.name+"</option>";
					$("#notetype_add_chiled_father").html(html2);
					$("#add_record_type_"+(note-1)).val(da.note.father);
				$("#notetype_add_text_chiled").val("");
				$("#notetype_add_contact").val(0);
				$("#notetype_add_todu").val(0);
			}else{
				alert(da.msg);
			}
		});
	});
	$("#call_add_time").live("change", function(){
		$("#before_task").html(10);
	});
	$(".type_change").live("change", function(){
		var id = $(this).attr('id');
		var ids = id.split("_");
		var ID =ids[3];
		if(ID < note){
			for(var i=ID;i<=note;i++){
				if(i > ID){
					$("#add_record_type_"+i).parent().remove();
				}
			}
			note = ID;
		}
		$(".todu").addClass("hidden");
		var father = $(this).val();
		$.ajax({
			dataType:'json',
			url:'note_type.action',
			data:{id:father}
		}).done(function(da){
			if(da.status == 1){
				if(da.note != null){
					if(da.note.todu > 0 ){
						switch(da.note.todu){
						case 1000:$(".call_add_sale").removeClass("hidden");break;
						case 1001:$(".call_add_support").removeClass("hidden");break;
						case 1002:$(".call_add_time_show").removeClass("hidden");break;
						case 1003:$(".call_add_guanbi").removeClass("hidden");break;
						}
					}
				}
			}else{
				alert(da.msg);
			}
		});
		if(father > 0){
			$.ajax({
				dataType:'json',
				url:'note_types.action',
				data:{father:father,}
			}).done(function(da){
				if(da.status == 1){
					var notes = da.notes;
					if(notes != null && notes.length > 0){
						note ++;
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
					}else{
						var father = note;
						if(note > 0){
							father = $("#add_record_type_"+(note-1)).val();
						}
						$.ajax({
							dataType:'json',
							url:'note_types.action',
							data:{father:father}
						}).done(function(da){
							if(da.status == 1){
								var notes = da.notes;
								if(notes != null && notes.length > 0){
									var html2 = "<option value='0'>父选项</option>";
									for(var i=0;i<notes.length;i++){
										html2 += "<option value='"+notes[i].id+"'>"+notes[i].name+"</option>";
									}							
									$("#notetype_add_chiled_father").html(html2);
								}
							}else{
								alert(da.msg);
							}
						});
					}
				}else{
					alert(da.msg);
				}
			});
		}else{
			if(note > 0){
				father = $("#add_record_type_"+(note-1)).val();
			}
			$.ajax({
				dataType:'json',
				url:'note_types.action',
				data:{father:father}
			}).done(function(da){
				if(da.status == 1){
					var notes = da.notes;
					if(notes != null && notes.length > 0){
						var html2 = "";
						for(var i=0;i<notes.length;i++){
							html2 += "<option value='"+notes[i].id+"'>"+notes[i].name+"</option>";
						}							
						$("#notetype_add_chiled_father").html(html2);
					}
				}else{
					alert(da.msg);
				}
			});
		}
	})
	
	$("#add_call_mark").live('focus', function(){
		var id = $("#add_record_type_"+note).val();
		if(id <= 0){
			$("#warming_msg").removeClass("hidden");
		}else{
			$("#warming_msg").addClass("hidden");
		}
	});
	$("#add_call_mark").live('input propertychange', function(){
		$("#testtest").html("");
		atCode = 0;
		atMax = 0;
		var content = $(this).val();
		if(content == null || content.length <= 0){
			return;
		}
		if(content.indexOf("@")>-1){
			var name = content.substring(content.lastIndexOf("@")+1, content.length);
			$.ajax({
				dataType:'json',
				url:'account_at.action',
				data:{name:name}
			}).done(function(da){
				if(da.status == 1){
					var admins = da.admins;
					var html = "";
					atMax = admins.length+1;
					for(var i=0;i<admins.length;i++){
						var j = i+1;
						html +="<div style='border:1px solid #C1CDCD;width:78px;height:20px;' class='at_view hand' atCode='"+j+"'>"
									+"<div style='width:70px;height:20px;margin-left:5px;' id='at_"+admins[i].id+"' class='at_"+j+"'>"+admins[i].name+"</div>"
									+"</div>";
					}
					$("#testtest").html(html);
				}
			});
		}
	});
	$(".at_view").live("mouseover", function(){
		$(".at_view").css("background-color","#C1CDCD");
		$(this).css("background-color","#B5ACDC");
		atCode = $(this).attr('atCode');
	})
	$(".at_view").live("click", function(){
		var name = $(".at_"+atCode).html();
		var content = $("#add_call_mark").val();
		content = content.substring(0, content.lastIndexOf("@")+1);
		content += name +" ";
		$("#testtest").html("");
		$("#add_call_mark").val(content);
	});
});

