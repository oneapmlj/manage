var red = false;
var messageNum = 0;
var JmessageNum = function(number){
	messageNum -= number;
	if(messageNum <=0){
		messageNum = 0;
		location.reload(); 
	}
}
		var getMessage1 = function(vo){
			switch(vo.message.type){
				/*case 1:return "向你申请成为";
				case 2:return "向你申请成为";
				case 3:return "同意了你成为";
				case 4:return "同意了你成为";
				case 5:return "拒绝了你成为";
				case 6:return "拒绝了你成为";*/
				case 7:return "提醒你关注";
				case 8:return "提醒你关注";
				case 9:return "将用户";
				case 10:return "将用户";
				case 11:return "将用户";
				case 15:return "在用户";
				case 16:return "收回了你负责的用户";
				case 17:return "收回了你负责的用户";
				case 18:return "收回了你负责的用户";
				case 19:return "更改了";
				case 20:return "更改了";
				case 21:return "在用户";
				default:return "";
			}
		}
		var getMessage2 = function(vo){
			switch(vo.message.type){
				/*case 1:return "的销售负责人";
				case 2:return "的运营负责人";
				case 3:return "销售负责人的请求";
				case 4:return "运营负责人的请求";
				case 5:return "销售负责人的请求";
				case 6:return "运营负责人的请求";*/
				case 7:return "的使用状态";
				case 8:return "的使用状态";
				case 9:return "分配给你";
				case 10:return "分配给你";
				case 11:return "分配给你";
//				case 12:return "的运营负责人";
//				case 13:return "的销售负责人";
//				case 14:return "的运营负责人";
				case 15:return "添加了记录";
				case 19:return "的定位";
				case 20:return "的进度";
				case 21:return "中@了你";
				default:return "";
			}
		}
		var getMessage3 = function(vo){
			if(vo.message.type == 1 || vo.message.type == 2){
				return "<div message='"+vo.message.id+"' from='"+vo.message.from+"' t='"+vo.message.type+"' val='"+vo.infoId+"' style='float:right;width:70px;color:red;'>"
								+"<div class='agree' style='float:left;width:30px;'>同意</div>"
								+"<div class='refuse'  style='float:left;width:30px;'>拒绝</div>"
								+"</div>"
			}else{
				return "<div message='"+vo.message.id+"'  from='"+vo.message.from+"'  t='"+vo.message.type+"' val='"+vo.infoId+"' style='float:right;width:70px;color:red;'>"
					+"<div class='chakan' style='float:left;width:30px;'>查看</div>"
					+"<div class='kanle' style='float:left;width:30px;'>确定</div>" 
					+"</div>"
			}
		}
		var getMessageContent = function(vo){
			var html = "";
			html += "<div class='message_"+vo.message.id+"' style='width:250px;border-top:1px solid #FFFFFF;'>"
				+"<div style='width:250px;margin-top:5px;'>"
//				+"<div style='float:left;'>&nbsp;"+getMessage1(vo)+"&nbsp;</div>"
				+"<div style='float:left;'><a style='color:blue;'>"+vo.fromName+"</a>&nbsp;"+getMessage1(vo)+"<a style='color:blue;' href='info_view.action?id="+vo.infoId+"'>"+vo.company+"</a>"+getMessage2(vo)+"</div>&nbsp;"
				+"</div>"
				+"<div style='margin-bottom:5px;width:250px;height:20px;'>"
				+getMessage3(vo)
				+"</div>"						
				+"</div>";
				return html;
		}
		var getWarming = function(){
			$.ajax({
				dataType:'json',
				url:'message_warming.action',
				data:{}
			}).done(function(da){
				if(da.status == 1){
					$("#warming").html(da.warming);
					$(".warming").removeClass("hidden");
				}else{
					$(".warming").addClass("hidden");
				}
			});
		}
		var getMessage = function(){
			$.ajax({
				dataType:'json',
				url:'message_message.action',
				data:{}
			}).done(function(da){
				$("#task_length").html(da.task);
				if(da.length > 0){
					$(".message_history").addClass("show_message");
					$(".show_message").removeClass("message_history");
					$(".show_message").css("color", "red");
					$(".show_message").css("width", "80px");
					$(".show_message").html("消息("+da.length+")");
					var vos = da.vos;
					var html = "";
					for(var i=0;i<vos.length;i++){
						var vo = vos[i];
						html += getMessageContent(vo);
					}
					messageNum = vos.length;
					$("#message_view").html(html);
					$(".show_message").removeClass("hidden");
				}else{
					$(".show_message").html("消息");
					$(".show_message").addClass("message_history");
					$(".message_history").removeClass("show_message");
					$(".message_history").css("width", "50px");
					$(".show_message").css("color", "");
				}
			});
		}
		var message = function(){
			setInterval(function(){
				if(red){
					$(".show_message").css('color', 'red');
					red = false;
				}else{
					$(".show_message").css('color', '');
					red = true;
				}
			}, 100);
			getMessage();
			getWarming();
			setInterval(function(){
				getMessage();
				getWarming();
			}, 10000);
		}
		 message(); 
		$(document).ready(function(){
			$(".click_to_zhengzailianxi").live('click', function(){
				window.open("/info_onlianxi.action");
			});
			$(".message_history").live('click', function(){
				window.open("/message_index.action");
			});
			$(".message_history_all").live('click', function(){
				window.open("/message_index.action");
			});
			$(".click_to_mark").live('click', function(){
				window.open("mark_list.action");
			})
			$(".click_to_task").live('click', function(){
				window.location.href="task_index.action";
			})
			$(".agree").live('click', function(){
				var id = $(this).parent().attr("val");
				var type = $(this).parent().attr("t");
				var from = $(this).parent().attr("from");
				var message = $(this).parent().attr("message");
				if(type==1){
					type = 3;
				}
				if(type == 2){
					type = 4;
				}
				if(type ==9){
					type = 11;
				}
				if(type ==10){
					type = 12;
				}
				$.ajax({
					dataType:'json',
					url:'info_change.action',
					data:{messageId:message,from:from,id:id,type:type}
				}).done(function(da){
					if(da.status == 1){
						$(".message_"+da.id).remove();
						JmessageNum(1);
					}else{
						alert(da.msg);
						$(".message_"+da.id).remove();
						JmessageNum(1);
					}
				});
			});
			$(".click_to_file").live('click', function(){
				window.location.href = "file_file.action";
			});
			$(".refuse").live('click', function(){
				var id = $(this).parent().attr("val");
				var type = $(this).parent().attr("t");
				var from = $(this).parent().attr("from");
				var message = $(this).parent().attr("message");
				if(type==1){
					type = 5;
				}
				if(type == 2){
					type = 6;
				}
				if(type ==9){
					type = 13;
				}
				if(type ==10){
					type = 14;
				}
				$.ajax({
					dataType:'json',
					url:'info_change.action',
					data:{messageId:message,from:from,id:id,type:type}
				}).done(function(da){
					if(da.status == 1){
						$(".message_"+da.id).remove();
						JmessageNum(1);
					}else{
						alert(da.msg);
					}
				});
			});
			$(".kanle").live('click', function(){
				var id = $(this).parent().attr("val");
				var message = $(this).parent().attr("message");
				$.ajax({
					dataType:'json',
					url:'message_view.action',
					data:{id:message}
				}).done(function(da){
					if(da.status == 1){
						$(".message_"+da.id).remove();
						JmessageNum(1);
					}else{
						alert(da.msg);
					}
				});
			});
			
			$(".message_view_check_chakan").live('click', function(){
				var id = $(this).parent().parent().attr("id");
				var ids = id.split("_");
				$.ajax({
					dataType:'json',
					url:'message_view.action',
					data:{id:ids[2]}
				}).done(function(da){
					if(da.status == 1){
						$("#list_message_"+da.id).css("color","gray");
						window.open("/info_view.action?id="+da.infoId);
					}else{
						alert(da.msg);
					}
				});
			});
			$(".message_view_queding").live('click', function(){
				var id = $(this).parent().parent().attr("id");
				var ids = id.split("_");
				$.ajax({
					dataType:'json',
					url:'message_view.action',
					data:{id:ids[2]}
				}).done(function(da){
					if(da.status == 1){
						$("#list_message_"+da.id).css("color","gray");
					}else{
						alert(da.msg);
					}
				});
			});
			
			$(".chakan").live('click', function(){
				var id = $(this).parent().attr("val");
				var message = $(this).parent().attr("message");
				window.open("info_view.action?id="+id);
				$.ajax({
					dataType:'json',
					url:'message_view.action',
					data:{id:message}
				}).done(function(da){
					if(da.status == 1){
						$(".message_"+da.id).remove();
						JmessageNum(1);
					}else{
						alert(da.msg);
					}
				});
			});
			$(".click_to_review").live('click', function(){
				window.location.href="review.action"; 
			})
			$(".click_to_pay").live('click', function(){
				window.location.href="pay.action"; 
			})
			$(".click_to_baobiao").live('click', function(){
				window.location.href="baobiao_liucun_yunying.action"; 
			});
			$(".click_to_duandian").live('click', function(){
				window.location.href="duandian_index.action"; 
			});
			$(".click_to_language").live('click', function(){
				window.location.href="info_language.action"; 
			})
			$(".click_to_tongji").live('click', function(){
				window.location.href="tongji_index.action";
			});
			$(".message_click_to_close").live('click',function(){
				$("#message_window").addClass("hidden");
			});
			$(".show_message").live('click', function(){
				$("#message_window").removeClass("hidden");
			})
			$(".click_to_search").live('click', function(){
				window.location.href="info_search.action";
//				window.open("info_search.action");
			});
			
			window.document.onkeydown = function(){
				if(event.keyCode==13)
			    {
			    	//document.activeElement.click();   
			    	if($(".keyboard_enter").val()=="0"){
						$("#add_tijiao").click();
			    	}
			    }
			}
			$("#add_tijiao").click(function(){
				var company = $("#add_company").val();
				var name = $("#add_name").val();
				var phone = $("#add_phone").val();
				var email = $("#add_email").val();
				var qq = $("#add_qq").val();
				if(company == null || company == ""){
					alert("公司名不能为空！");
					return;
				}
				if((phone == null || phone == "") && (email == null || email == "") && (qq == null || qq == "")){
					alert("至少要有一种联系方式！");
					return;
				}
				 document.getElementById('add_info').submit();
			});
			$(".click_to_add").click(function(){
				$.ajax({
					dataType:'json',
					url:'account_grade.action',
					data:{type:101}
				}).done(function(da){
					if(da.status == 1){
						$(".background").removeClass("hidden");
						$(".index_add").removeClass("hidden");
						$("#add_company").focus();
						$(".keyboard_enter").val("0");
					}else{
						alert(da.msg);
					}
				});
			});
			$(".loginOut").click(function(){
				window.location.href="loginOut.action";
			});
			$(".menu_button").mouseover(function(){
				$(this).removeClass("biankuang_gray");
				$(this).addClass("biankuang_blue");
			});
			$(".menu_button").mouseleave(function(){
				$(this).removeClass("biankuang_blue");
				$(this).addClass("biankuang_gray");
			});
			$("#add_window_close").click(function(){
				$(".background").addClass("hidden");
				$(".index_add").addClass("hidden");
				$(".keyboard_enter").val("1");
			});
			$(".click_to_account").click(function(){
				window.location.href = "account.action";
			});
			$(".check_gongdan").live('click', function(){
				id = $(this).attr("val1");
				$.ajax({
					dataType:'json',
					url:'account_grade.action',
					data:{type:104}
				}).done(function(da){
					if(da.status == 1){
						window.open("/info_check_udesk.action?id="+id);
					}else{
						alert(da.msg);
					}
				});
			});
		});