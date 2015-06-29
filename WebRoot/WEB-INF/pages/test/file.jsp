<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>OneApm</title>

<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/layout.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/index.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/view.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/public.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/file.css" type="text/css" media="screen" />


<script type="text/javascript" src="${applicationScope.staticPath}skin/js/jquery.min.js"></script>
<script type="text/javascript" src="${applicationScope.staticPath}skin/js/ajaxfileupload.js"></script>
</head>
<body>
	<s:include value="/WEB-INF/pages/public/header.jsp" />
	<section id="main" class="column"> 
		<input class="hidden" id="father" tyle="text" value="${father}"/>
		<div class="public_main width_3_quarter_customer">
			<div class="header">
				<div style="float:left">
					<h3 class="tabs_involved">
						文档中心
					</h3>
				</div>
				<div style="width:700px;float:left;">
				</div>
			</div>
			<div style="width:100%;">
				<div style="width:1200px;min-height:600px;margin-left:auto;margin-right:auto;">
					<div style="width:1200px;height:40px;line-height:40px;margin-left:30px;">
						<div style="margin-left:20px;width:400px;float:left;" father="0">
							<div style="font-size:16px;float:left;"><a style="color:#6495ED;" href="#" class="file_list">全部文档</a></div>
							<div id="lians" class="float:left;"></div>
						</div>
					</div>
					<div style="width:1000px;margin:5px 0 0 100px;font-size:12px;">
						<div style='width:1100px;height:30px;line-height:30px;margin-bottom: 5px;'>
							<div style="width:600px;float:left;hieght:30px;color:#6495ED;">文件名</div>
							<div style="width:150px;float:left;hieght:30px;color:#6495ED;">
								<div style="width:15px;float:left;height:30px;"><img style="margin-top:8px;" src="${applicationScope.staticPath}skin/images/upload.png"/></div>
								<div style="width:55px;float:left;height:30px;" title="上传文档"><a href="#" id="click_to_file_wendang_manage">上传文档</a></div>
								<div style="width:15px;float:left;height:30px;"><img style="margin-top:8px;" src="${applicationScope.staticPath}skin/images/mulu_manage.png"/></div>
								<div style="width:50px;float:left;height:30px;" title="目录管理"><a href="#" id="click_to_file_mulu_manage">目录管理</a></div>
							</div>
							<div style="width:150px;float:left;hieght:30px;color:#6495ED;">创建时间</div>
							<div style="width:150px;float:left;hieght:30px;color:#6495ED;">创建人</div>
						</div>
						<div style="width:1000px;font-size:12px;"  id="file_list1">
						</div>
						<div style="width:1000px;font-size:12px;"  id="file_list2">
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<div class="file_window hidden">
		<div class="pop_up">
			<h4><span>目录管理</span></h4>
			<a href="javascript:void(0);" class="pop_close"></a>
			<div class="pop_content clear">
				<ul class="pop_list">
					<li>
						<div class="pop_list_mult">
							<select id="dir_list" class="mult" size="5" name="">	
								
							</select>
							<ul class="pop_list_mult_tool">
								<li><input id="btnDeleteDir" class="delete_button" type="button" title="回收"></input></li>
								<li>
									<input id="btnRenameDir" class="rename_button" style="width: 18px; height: 18px;" type="button" maxlength="200" title="重命名"></input>
									<input id="dir-rename-text" class="hidden" type="text" style="width: 100px; "></input>
									<a id="btnRenameDirSave" class="hidden" style="float:left;width:30px;" href="javascript:void(0);">修改</a>
								</li>
							</ul>
							<br  style="clear:left;"></br>
							<input id="dir-add" class="text" type="text" maxlength="200"></input>
							<a id="btnAddDir" class="button" href="javascript:void(0);" style="text-decoration: none;color: #4A73AB;outline: medium none;">添加目录</a>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	
	<div class="upload_window hidden">
		<div class="pop_up">
			<h4><span>上传文档</span></h4>
			<a href="javascript:void(0);" class="pop_close"></a>
			<div class="pop_content clear">
				<ul class="pop_list">
					<li>
						<div style="width:60px;height:20px;line-height:20px;border: 1px solid #CCC;text-align:center" class="hand" id="click_add_file">
							选择文件
						</div>
						<div style="width:300px;" id="add_file_content">
							
						</div>
						<input type="file" id="addFile" class="hidden" name="file" />
					</li>
				</ul>
			</div>
			<div class="pop_bottom">
				<div class="pop_button_area">
					<div class="pop_button hand" id="click_to_save">保存</div>
				</div>
			</div>
		</div>
	</div>
	
	<script src="${applicationScope.staticPath}skin/js/hideshow.js" type="text/javascript"></script>
	<script src="${applicationScope.staticPath}skin/js/jquery.tablesorter.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/jquery.equalHeight.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/index.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/view.js"></script>
	 <script type="text/javascript" src="${applicationScope.staticPath}skin/js/ajaxfileupload.js"></script>
	<script type="text/javascript">
		var upload = false; 
		var save = false;
		var change = function(){
			save = false;
			$("#").html("保存");
			$("#add_file_content").html("");
		}
		$(document).ready(function(){
			$("#click_to_file_wendang_manage").live('click', function(){
				$.ajax({
					dataType:'json',
					url:'account_grade.action',
					data:{type:107}
				}).done(function(da){
					if(da.status == 1){
						$(".upload_window").removeClass("hidden");
						$(".background").removeClass("hidden");
					}else{
						alert(da.msg);
					}
				});
			});
			$("#click_to_save").live('click', function(){
				if(!save){
					save = true;
					$("#").html("保存中");
					var a = $("#add_file_content").children();
					if(a == null || a.length <= 0){
						return;
					}
					var ids = "";
					for(var i=0;i<a.length;i++){
						ids += $(a[i]).attr('id').split("_")[2]+",";
					}
					var father = $("#father").val();
					$.ajax({
						dataType:'json',
						url:'file_save.action',
						data:{ids:ids,father:father,status:1}
					}).done(function(da){
						if(da.status == 1){
							var files = da.files;
							var html = "";
							for(var i=0;i<files.length;i++){
								html+="<div father='"+files[i].id+"' class='wendang'  id='show_view_"+files[i].id+"'>"
									+"<div class='img_wendang_aera'><img src='skin/images/wendang.png'  /></div>"
									+"<div class='name_aera'>"
									+"<a href='#' style='color:#000000;' class='file_view'>"+files[i].name+"</a>"
									+"</div>"
									+"<div class='guanli'>"
									+"<div class='hand view'><img class='download' src='skin/images/download.png' title='下载'/></div>"
									/* +"<div class='hand view'><img class='move' src='skin/images/move.png' title='移动'/></div>" */
									+"<div class='hand view'><img class='delete' src='skin/images/delete.png' title='删除'/></div>"
									/* +"<div class='hand view'><img class='store' src='skin/images/star_gray.png' title='收藏'/></div>" */
									+"</div>"
									+"<div class='createTime_wendang'>"+files[i].create_time+"</div>"
									+"<div class='admin'><a href=''#' style='color:#8A8A8A;'' admin='"+files[i].admin_id+"'>"+files[i].admin_name+"</a></div>"
									+"</div>";
							}
							$("#file_list2").append(html);
							change();
						}else{
							alert(da.msg);
							change();
						}
					});
				}
			})
			$("#click_add_file").live('click', function(){
				if(!upload){
					$("#addFile").click();
				}
			});
			$(".remove_upload").live('click', function(){
				$(this).parent().remove();
			});
			$("#addFile").live('change', function(){
				upload = true;
				$("#click_add_file").html("<span style='color:red;'>上传中</span>");
				$.ajaxFileUpload (
					{url:'file_upload.action',
			                secureuri:false,//一般设置为false
			                fileElementId:'addFile',
			                dataType: 'json',
			                success: function (data, status){
			                   if(data.status == 1){
			                	   var html ="<div style='width:350px;height:30px;line-height:30px;font-size:12px;' id='upload_view_"+data.id+"'>"
												+"<div style='float:left;width:320px;''>"+data.name+"</div>"
												+"<div style='float:left;widht:20px;' class='hand remove_upload'>X</div>"
												+"</div>";
									$("#add_file_content").append(html);
									$("#click_add_file").html("选择文件");
									upload = false;
			                   }else{
			                	   alert(data.msg);
			                	   $("#click_add_file").html("选择文件");
			                	   upload = false;
			                   }
			            	} 
				 });
			})
			$("#btnAddDir").live('click', function(){
				var name = $("#dir-add").val();
				if(name == null || name.trim==""){
					alert("目录名不能为空");
					return;
				}
				var father = $("#father").val();
				$.ajax({
					dataType:'json',
					url:'file_create.action',
					data:{father:father,name:name,status:1,type:1}
				}).done(function(da){
					if(da.status == 1){
						var file = da.file;
						var html = "<option id='option_"+file.id+"' value='"+file.id+"'>"+file.name+"</option>";
						$("#dir_list").append(html);
						$("#dir-add").val("");
						var html1 = "<div father='"+file.id+"' class='wendang' id='show_view_"+file.id+"'>"
										+"<div class='img_mulu_aera'><img src='skin/images/mulu.png'  /></div>"
										+"<div class='name_aera'>"
										+"<a href='#' style='color:#000000;' class='file_list' id='name_view_"+file.id+"'>"+file.name+"</a>"
										+"<span class='color_wight_blue'>（"+file.number+"）</span>"
										+"</div>"
										+"<div class='createTime'>"+file.create_time+"</div>"
										+"<div class='admin'><a href='#' style='color:#8A8A8A;'' admin='"+file.admin_id+"'>"+file.admin_name+"</a></div>"
										+"</div>";
						$("#file_list1").append(html1);
					}else{
						alert(da.msg);
					}
				});
			});
			$("#btnRenameDirSave").live('click', function(){
				$.ajax({
					dataType:'json',
					url:'account_grade.action',
					data:{type:108}
				}).done(function(da){
					if(da.status == 1){
						var name = $("#dir-rename-text").val();
						var id = $("#dir_list").val();
						if(name == null || name.trim() == ""){
							alert("目录名不能为空");
							return;
						}
						$.ajax({
							dataType:'json',
							url:'file_rename.action',
							data:{id:id,name:name}
						}).done(function(da){
							if(da.status == 1){
								$("#option_"+da.id).html(da.name);
								$("#name_view_"+id).html(da.name);
								$("#dir-rename-text").val("");
								$("#dir-rename-text").addClass("hidden");
								$("#btnRenameDirSave").addClass("hidden");
								$("#btnRenameDir").removeClass("hidden");
							}else{
								alert(da.msg);
							}
						});
					}else{
						alert(da.msg);
					}
				});
			});
			$("#btnRenameDir").live('click', function(){
				$.ajax({
					dataType:'json',
					url:'account_grade.action',
					data:{type:108}
				}).done(function(da){
					if(da.status == 1){
						var id = $("#dir_list").val();
						var name = $("#option_"+id).html();
						if(name == null){
							alert("请选择一个目录");
							return;
						}
						$("#dir-rename-text").removeClass("hidden");
						$("#dir-rename-text").val(name);
						$("#btnRenameDirSave").removeClass("hidden");
						$("#btnRenameDir").addClass("hidden");
					}else{
						alert(da.msg);
					}
				});
			});
			$("#btnDeleteDir").live('click', function(){
				$.ajax({
					dataType:'json',
					url:'account_grade.action',
					data:{type:108}
				}).done(function(da){
					if(da.status == 1){
						var id = $("#dir_list").val();
						if(id == null){
							alert("请选择一个目录");
							return;
						}
						$.ajax({
							dataType:'json',
							url:'file_delete.action',
							data:{id:id}
						}).done(function(da){
							if(da.status == 1){
								$("#option_"+da.id).remove();
								$("#show_view_"+da.id).remove();
							}else{
								alert(da.msg);
							}
						});
					}else{
						alert(da.msg);
					}
				});
			});
			$("#click_to_file_mulu_manage").live('click', function(){
				$.ajax({
					dataType:'json',
					url:'account_grade.action',
					data:{type:107}
				}).done(function(da){
					if(da.status == 1){
						var father = $("#father").val();
						$.ajax({
							dataType:'json',
							url:'file_mulus.action',
							data:{father:father}
						}).done(function(da){
							if(da.status == 1){
								var html = "";
								var files = da.files;
								if(files != null && files.length > 0){
									for(var i =0;i<files.length;i++){
										html += "<option id='option_"+files[i].id+"' value='"+files[i].id+"'>"+files[i].name+"</option>";
									}
									$("#dir_list").html(html);
								}
								$(".file_window").removeClass("hidden");
								$(".background").removeClass("hidden");
							}else{
								alert(da.msg);
							}
						});
					}else{
						alert(da.msg);
					}
				});
			});
			$(".pop_close").live('click', function(){
				$("#dir_list").html("");
				$(".file_window").addClass("hidden");
				$(".upload_window").addClass("hidden");
				$(".background").addClass("hidden");
			});
			$(".file_list").live('click', function(){
				var father = $(this).parent().parent().attr("father");
				list(father);
			});
			$(".download").live('click', function(){
				var ids = $(this).parent().parent().parent().attr('id');
				var id = ids.split("_")[2];
				location.href="file_download.action?id="+id;
			});
			$(".download").live('mouseover', function(){
				$(this).attr("src", "skin/images/download_blue.png");
			});
			$(".download").live('mouseout', function(){
				$(this).attr("src", "skin/images/download.png");
			});
			$(".move").live('click', function(){
				alert("move");
			});
			$(".move").live('mouseover', function(){
				$(this).attr("src", "skin/images/move_blue.png");
			});
			$(".move").live('mouseout', function(){
				$(this).attr("src", "skin/images/move.png");
			});
			$(".delete").live('click', function(){
				var ids = $(this).parent().parent().parent().attr('id');
				var id = ids.split("_")[2];
				$.ajax({
					dataType:'json',
					url:'account_grade.action',
					data:{type:108,id:id}
				}).done(function(da){
					if(da.status == 1){
						$.ajax({
							dataType:'json',
							url:'file_delete.action',
							data:{id:da.id}
						}).done(function(da){
							if(da.status == 1){
								$("#show_view_"+da.id).remove();
							}else{
								alert(da.msg);
							}
						});
					}else{
						alert(da.msg);
					}
				});
			});
			$(".delete").live('mouseover', function(){
				$(this).attr("src", "skin/images/delete_blue.png");
			});
			$(".delete").live('mouseout', function(){
				$(this).attr("src", "skin/images/delete.png");
			});
			$(".store").live('click', function(){
				alert("store");
			});
			$(".store").live('mouseover', function(){
				$(this).attr("src", "skin/images/star_yellow.png");
			});
			$(".store").live('mouseout', function(){
				$(this).attr("src", "skin/images/star_gray.png");
			});
		});
		
		var list = function(father){
			$.ajax({
				dataType:'json',
				url:'file_show.action',
				data:{father:father}
			}).done(function(da){
				if(da.status == 1){
					var html1 = "";
					var html2 = "";
					var lian = "";
					var files = da.files;
					var lians = da.lians;
					if(lians != null && lians.length > 0){
						for(var i=lians.length-1;i>=0;i--){
							lian += "<div style='font-size:14px;float:left;'>&nbsp;>&nbsp;</div>";
							lian += "<div style='font-size:16px;float:left;' father='"+lians[i].id+"'><div><a style='color:#6495ED;' href='#' class='file_list'>"+lians[i].name+"</a></div></div>";
						}
					}
					if(da.name != null){
						lian += "<div style='font-size:14px;float:left;'>&nbsp;>&nbsp;</div>";
						lian += "<div style='font-size:14px;float:left;'>"+da.name+"</div>";
					}
					$("#lians").html(lian);
					if(files != null && files.length > 0){
						for(var i=0;i<files.length;i++){
							if(files[i].type==1){
								html1+="<div father='"+files[i].id+"' class='wendang' id='show_view_"+files[i].id+"'>"
											+"<div class='img_mulu_aera'><img src='skin/images/mulu.png'  /></div>"
											+"<div class='name_aera'>"
											+"<a href='#' style='color:#000000;' class='file_list'  id='name_view_"+files[i].id+"'>"+files[i].name+"</a>"
											+"<span class='color_wight_blue'>（"+files[i].number+"）</span>"
											+"</div>"
											+"<div class='createTime'>"+files[i].create_time+"</div>"
											+"<div class='admin'><a href='#' style='color:#8A8A8A;'' admin='"+files[i].admin_id+"'>"+files[i].admin_name+"</a></div>"
											+"</div>";
							}
						}
						for(var i=0;i<files.length;i++){
							if(files[i].type==2){
								html2+="<div class='wendang'  id='show_view_"+files[i].id+"'>"
											+"<div class='img_wendang_aera'><img src='skin/images/wendang.png'  /></div>"
											+"<div class='name_aera'>"
											+"<a href='#' style='color:#000000;' class='file_view'>"+files[i].name+"</a>"
											+"</div>"
											+"<div class='guanli'>"
											+"<div class='hand view'><img class='download' src='skin/images/download.png' title='下载'/></div>"
											/* +"<div class='hand view'><img class='move' src='skin/images/move.png' title='移动'/></div>" */
											+"<div class='hand view'><img class='delete' src='skin/images/delete.png' title='删除'/></div>"
											/* +"<div class='hand view'><img class='store' src='skin/images/star_gray.png' title='收藏'/></div>" */
											+"</div>"
											+"<div class='createTime_wendang'>"+files[i].create_time+"</div>"
											+"<div class='admin'><a href=''#' style='color:#8A8A8A;'' admin='"+files[i].admin_id+"'>"+files[i].admin_name+"</a></div>"
											+"</div>";
							}
						}
					}
					$("#file_list1").html(html1);
					$("#file_list2").html(html2);
					$("#father").val(da.father);
				}else{
					alert(da.msg);
				}
			});
		};
		list(0);
	</script>
</body>
</html>