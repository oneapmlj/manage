<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>OneApm</title>
	
	<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/layout.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/index.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/add.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/public.css" type="text/css" media="screen" />
	<script src="${applicationScope.staticPath}skin/js/jquery.min.js"></script>
</head>
<body>
	<s:include value="/WEB-INF/pages/public/header.jsp"/>
	<section id="main" class="column"> 
	<div class="public_main width_3_quarter_customer">
	<div class="header">
				<h3 class="tabs_involved">
					添加客户信息
				</h3>
			</div>
			<s:if test="%{infos != null && infos.size > 0}">
				<div style="margin:10px 0 0 20px;;font-size:16px;"><strong>匹配到以下用户：</strong></div>
				<s:iterator value="infos">
					<div class="add_edit_view biankuang_gray hand" title="点击查看详情" val1="${id}">
						<div style="height:20px;width:90px;float:left;">
							<%-- <div class="hebing add_button_1 biankuang_gray hand" val1="${id}">合并</div> --%>
						</div>
						<div style="height:20px;float:left;">
							<div style="width:300px;float:left;">公司：${company}</div>
							<div style="width:300px;float:left;">项目：${project}</div>
							<div style="width:300px;float:left;">姓名：${name}</div>
							<%-- <div style="width:300px;float:left;">语言：${language}</div> --%>
							<%-- <div style="width:300px;float:left;">ID：${userId}</div> --%>
							<div style="width:200px;float:left;">销售：${saleName}</div>
							<%-- <div style="width:300px;float:left;">运营：${supportName}</div>
							<div style="width:300px;float:left;">售前：${preSaleName}</div> --%>
						</div>
					</div>
				</s:iterator>
				<div style="margin:10px 0 0 20px;;font-size:16px;"><strong>继续添加：</strong></div>
			</s:if>
			<div></div>
			<div style="width:100%;min-height:450px;border: 1px;">
				<div class="add_aera">
					<div class="p">
						<div class="before">公司:</div>
						<div class="after">
							<input type="text" class="add_company" value="${company }"/>
						</div>
						<div id="add_company_msg" class="hidden msg"></div>
					</div>
					<div class="p">
						<div class="before">项目:</div>
						<div class="after">
							<input type="text" class="add_project" value=""/>
						</div>
						<div id="add_project_msg" class="hidden msg"></div>
					</div>
					<div class="p">
						<div class="before">电话:</div>
						<div class="after">
							<input type="text" class="add_celphone" value="${phone }"/>
						</div>
						<div id="add_cellphone_msg" class="hidden msg"></div>
					</div>
					<div class="p">
						<div class="before">邮箱:</div>
						<div class="after">
							<input type="text" class="add_email" value="${email }"/>
						</div>
						<div id="add_email_msg" class="hidden msg"></div>
					</div>
					<div class="p">
						<div class="before">姓名:</div>
						<div class="after">
							<input type="text" class="add_name" value="${name }"/>
						</div>
						<div id="add_name_msg" class="hidden msg"></div>
					</div>
					<!-- <div class="p">
						<div class="before">备注:</div>
						<div class="after">
							<input type="text" class="add_mark" value=""/>
						</div>
						<div id="add_remark_msg" class="hidden msg"></div>
					</div> -->
					<div class="p">
						<div class="before">部门:</div>
						<div class="after">
							<input type="text" class="add_branch" value=""/>
						</div>
					</div>
					<div class="p">
						<div class="before">职位:</div>
						<div class="after">
							<input type="text" class="add_position" value=""/>
						</div>
					</div>
					<div class="p">
						<div class="before">QQ:</div>
						<div class="after">
							<input type="text" class="add_qq" value=""/>
						</div>
					</div>
					<div class="p">
						<div class="before">性别:</div>
						<div class="after">
							<select class="add_gender">
								<option value="0">性别</option>
								<option value="1">男</option>
								<option value="2">女</option>
							</select>
						</div>
					</div>
					<div class="p">
						<button style="cursor: pointer;" type="submit" id="add_submit">添加</button>
					</div>
				</div>
			</div>
			<!--  -->
		</div>
	</section>
	<div class="window hidden">
		<div style="width:100%;height:40px;"></div>
		<div class="list"></div>
		<div style="width:100%;height:30px;"></div>
	</div>
	<script src="${applicationScope.staticPath}skin/js/hideshow.js" type="text/javascript"></script>
	<script src="${applicationScope.staticPath}skin/js/jquery.tablesorter.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/jquery.equalHeight.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/index.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/view.js"></script>
	<script type="text/javascript">
		var email_error = true;
		var name_error = true;
		var company_error = true;
		$(document).ready(function(){
			$(".add_edit_view").click(function(){
				var id = $(this).attr("val1");
				window.open("info_view.action?id="+id);
			});
			$(".hebing").click(function(){
				var id = $(this).val("val1");
				window.open("info_edit.action?id="+id);
			});
			
			$("#add_submit").click(function(){
				var email = $(".add_email").val();
				var company = $(".add_company").val();
				var name = $(".add_name").val();
				var phone = $(".add_celphone").val();
				var branch = $(".add_branch").val();
				var position = $(".add_position").val();
				var gender = $(".gender").val();
				var qq = $(".add_qq").val();
				var project = $(".add_project").val();
				var search_str = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
				if(email_error && !search_str.test(email)){
					alert("邮箱信息错误！")
					return;
				}
				if(name_error && name == null){
					alert("姓名信息错误！")
					return;
				}
				if(company_error && company == null){
					alert("项目信息错误！")
					return;
				}
				var o = {name:name, company:company,email:email,phone:phone,branch:branch,position:position,gender:gender,qq:qq,project:project};
				$.ajax({
					dataType:'json',
					url:'info_insert.action',
					data:o
				}).done(function(da){
					if(da.status == 1){
						window.location.href="info_view.action?id="+da.infoId;
					}else{
						alert(msg);
					}
				});
			});
			$("input").focus(function(){
				var id = $(this).attr("class");
				msgsh("",id,false);
			});
			
			$("input").blur(function(){
				var id = $(this).attr("class");
				switch(id){
					case "add_email":
						var content = $(this).val();
						/* if(content == null || content.length <=1){
							msgsh("邮箱信息不能为空！", id, true);
							email_error = true;
							break;
						} */
						if(content != null && content.trim().length > 0){
							var search_str = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
							if(!search_str.test(content)){
								msgsh("邮箱信息格式不对！", id, true);
								email_error = true;
								break;
							}
						}
						email_error = false;
						break;
					case "add_name":
						var content = $(this).val();
						if(content == null || content.length <=1){
							msgsh("姓名信息不能为空！", id, true);
							name_error = true;
							break;
						}
						name_error = false;
						break;
					case "add_company":
						var content = $(this).val();
						if(content == null || content.length <=1){
							msgsh("公司信息不能为空！", id, true);
							company_error = true;
							break;
						}
						company_error = false;
						break;
					default:break;					
				}
			});
		});
		var msgsh = function(msg,id,show){
			if(show){
				var doc = $("#"+id+"_msg");
				doc.html(msg);
				doc.removeClass("hidden");
			}else{
				$("#"+id+"_msg").addClass("hidden");
			}
		}
	</script>
</body>
</html>