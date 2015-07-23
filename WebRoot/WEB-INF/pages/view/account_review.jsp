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
	<script src="${applicationScope.staticPath}skin/js/jquery.min.js"></script>
	<script src="${applicationScope.staticPath}skin/js/account_review.js"></script>
	<script src="${applicationScope.staticPath}skin/js/timeAjax.js"></script>
</head>
<body>
	<s:include value="/WEB-INF/pages/public/header.jsp" />
	<section id="main" class="column"> 
	<input type="text" value="${adminType }" class="hidden" id="adminType"/>
	<div class="public_main width_3_quarter_customer">
	<div class="header">
		<div style="float:left"><h3 class="tabs_involved">REVIEW</h3></div>
		<div style="width:800px;float:left;">
			<div style="float:left;width:45px;"val1="0" class="account biankuang_blue_ding menu_button_2">全部</div>
			<div style="float:left;width:45px;" val1="1" class="account biankuang_gray menu_button_2">企业</div>
			<div style="float:left;width:45px;" val1="2" class="account biankuang_gray menu_button_2">开发者</div>
			<div style="float:left;width:55px;" val1="3"  class="account biankuang_gray menu_button_2">未定义</div>
			<div style="float:left;width:70px;"  class="account_info biankuang_gray menu_button_2">个人信息</div>
		</div>
	</div> 
		<div style="width:100%;" id="tab_1" class="tab">
			<div style="width:1200px;margin-left: auto;margin-right: auto;">
				<div style="width:80px;margin:10px 0 0 0;float:left;background-color:#F8F8F8 ;">
					<s:if test="%{admins != null}">
						<s:iterator value="admins">
							<s:if test="%{account.admin.id == id}">
								<div id="review_${id}" style="width:90px;border:1px solid #9CA1B0;" class="review boder_choose hand">&nbsp;${name}</div>
								<div class="hidden" id="review_id">${id }</div>
							</s:if>
							<s:else>
								<div id="review_${id}" style="width:90px;border:1px solid #9CA1B0;" class="review boder hand">&nbsp;${name}</div>
							</s:else>
						</s:iterator>
					</s:if>
				</div>
				<%-- <div style="width:370px;max-height:600px;float:left;">
					<div style="width:360px;float:left;line-height:25px;font-size:16px;">
						<div class="view_p_button"><strong>记录(${account.callSize})</strong></div>
					</div>
					<div id="calls" style="overflow:scroll;width:370px;max-height:550px">
						<s:if test="%{account.calls != null && account.calls.size > 0}">
						<s:iterator value="account.calls">
							<div class="view_p_content_350 biankuang_gray_ding hand">
								<div style="margin-left:5px;width:170px;float:left;">公司：<a href="info_view.action?id=${infoId }" target="_blank" style="color:black;">${company }</a></div>
								<div style="margin-left:5px;width:170px;float:left;">名片：<a>${cardName }</a></div>
								<div style="margin-left:5px;width:170px;float:left;">电话：${phone }</div>
								<div style="margin-left:5px;width:170px;float:left;">时间：${callTime }</div>
								<div style="margin-left:5px;width:170px;float:left;">记录：</div>
								<div style="margin-left:20px;width:320px;float:left;">
									<s:if test="%{mark == null}">无</s:if>
									<s:else>${mark}</s:else></div>
							</div>
						</s:iterator>
					</s:if>
					</div>
				</div> --%>
				<div style="margin-left:20px;float:left;width:1100px;height:30px;margin-top:10px;line-height:30px;">
					<div style="float:left;width:100px;" class="account_menu infos menu_button_2 biankuang_blue_ding">负责用户</div>
					<div style="float:left;width:100px;" class="account_menu calls menu_button_2 biankuang_gray">联系记录</div>
					<!-- <div style="float:left;width:100px;" class="account_menu mails menu_button_2 biankuang_gray">邮件记录</div> -->
				</div>
				<div style="margin-left:20px;width:1100px;height:800px;float:left;">
					<div style="width:1100px;float:left;line-height:30px;font-size:14px;">
						<div class="hidden" id="page">${page }</div>
						<div class="hidden" id="type">${type }</div>
						<div style="color:#4F94CD;height:30px;width:1100px;float:left;">
							<div style="float:left;width:350px;">
								<span id="account_info_size_total">负责用户(${account.sizeTotal}</span>) 
								| 当前(<span id="account_info_size">${account.size}</span>) 
								| 当前第(<span  id="account_page_now">${account.pageNow}</span>)页 
								| 总共(<span  id="account_page_total">${account.pageTotal}</span>)页
							</div>
							<div class="hand page_first" style="float:left;width:50px;">首页</div>
							<s:if test="%{account.pageNow > 1}">
								<div class="hand page_before" style="float:left;width:60px;">上一页</div>
							</s:if>
							<s:else>
								<div class="hidden hand page_before" style="float:left;width:60px;">上一页</div>
							</s:else>
							<s:if test="%{account.pageNow < account.pageTotal}">
								<div class="hand page_next" style="float:left;width:60px;">下一页</div>
							</s:if>
							<s:else>
								<div class="hidden hand page_next" style="float:left;width:60px;">下一页</div>
							</s:else>
							<div class="hand page_last" style="float:left;width:50px;">末页</div>
						</div>
					</div>
					<div id="infos" style="overflow:scroll;width:900px;height:700px;">
						<s:if test="%{account.infos != null && account.infos.size > 0}">
							<s:iterator value="account.infos">
								<div class="view_p_content_850 biankuang_gray_ding" style="font-size:14px;">
									<div style="margin-left:5px;width:540px;float:left;">公司：${project }</div>
									<%-- <div style="width:290px;float:left;">邮箱：<span>${email }</span></div> --%>
									<s:if test="%{userId != null && userId > 0}"><div style="width:240px;float:left;">ID：${userId }</div></s:if>
									<s:else><div style="width:240px;float:left;">ID：无</div></s:else>
									<div style="width:60px;float:left;">
										<input val1="${id }" class='check_view'  type='image' src='http://manage.oneapm.com/skin/images/icn_view_users.png' title='查看' />
									</div>
									<%-- <div style="margin-left:5px;width:180px;float:left;">电话：${phone }</div> --%>
									<div style="margin-left:5px;width:270px;float:left;">注册：${createTime }</div>
									<div style="width:270px;float:left;">登录：${loginTime }</div>
									<div style="width:270px;float:left;">最近联系：${contectTime }</div>
								</div>
							</s:iterator>
						</s:if>
					</div>
					<div id="calls" style="overflow:scroll;width:900px;height:700px;"  class="hidden">
						<s:if test="%{account.calls != null && account.calls.size > 0}">
							<s:iterator value="account.calls">
								<div class="view_p_content_850 biankuang_gray_ding hand">
									<div style="margin-left:5px;width:170px;height:20px;float:left;overflow: hidden;">公司：<a href="info_view.action?id=${infoId }" target="_blank" style="color:black;">${company }</a></div>
									<div style="margin-left:5px;width:170px;height:20px;float:left;">时间：${callTime }</div>
									<div style="margin-left:5px;width:170px;float:left;">项目：${project }</div>
									<div style="margin-left:5px;width:170px;height:20px;float:left;">名片：<a>${cardName }</a></div>
									<div style="margin-left:5px;width:170px;height:20px;float:left;">记录：</div>
									<div style="margin-left:20px;width:320px;float:left;">
										<s:if test="%{mark == null}">无</s:if>
										<s:else>${mark}</s:else></div>
								</div>
							</s:iterator>
						</s:if>
					</div>
				</div>
				<%-- <div style="width:370px;height:800px;float:left;margin-left:10px;">
					<div style="width:360px;float:left;line-height:25px;font-size:16px;">
						<div class="view_p_button"><strong>名片添加记录(${account.cardSize})</strong></div>
					</div>
					<div id="cards" style="overflow:scroll;width:370px;height:700px;">
						<s:if test="%{account.cards != null && account.cards.size > 0}">
							<s:iterator value="account.cards">
								<div class="view_p_content_350 biankuang_gray_ding hand" >
									<div style="margin-left:10px;width:340px;float:left;">公司：<a href="info_view.action?id=${infoId}" target="_blank">${company}</a></div>
									<div style="margin-left:10px;width:170px;float:left;">姓名：${name}</div>
									<div style="margin-left:5px;width:165px;float:left;">性别：
										<s:if test="%{gender == 0}">未选择</s:if>
										<s:else>
											<s:if test="%{gender == 1}">男</s:if>
											<s:else>女</s:else>
										</s:else></div>
									<div style="margin-left:10px;width:170px;float:left;">部门：${branch }</div>
									<div style="margin-left:5px;width:165px;float:left;">电话：${phone }</div>
									<div style="margin-left:10px;width:170px;float:left;">职务：${position }</div>
									<div style="margin-left:5px;width:170px;float:left;">时间：${createTime}</div>
									<div style="margin-left:10px;width:165px;float:left;">邮箱：${email }</div>
								</div>
							</s:iterator>
						</s:if>
					</div>
				</div> --%>
				<%-- <div style="width:1000px;max-height:600px;float:left;margin-left:10px;font-size:14px;">
					<div style="width:250px;float:left;line-height:25px;font-size:16px;">
						<div class="view_p_button"><strong>负责用户(${account.infoSize })</strong></div>
					</div>
					<div id="calls" style="width:960px;">
						<s:if test="%{account.infos != null && account.infos.size > 0}">
						<div class="view_p_content_900">
							<div style="margin-left:5px;float:left;width:300px;text-align: center;">公司</div>
							<div style="margin-left:5px;float:left;width:50px;">注册</div>
							<div style="margin-left:5px;float:left;width:300px;">项目</div>
						</div>
						<s:iterator value="account.infos">
							<div <s:if test="%{tag.metric == 1}">style="color:red;"</s:if><s:else>style="color:#000000;"</s:else> class="view_p_content_900 biankuang_gray_ding">
								<div style="margin-left:5px;width:300px;float:left;"><a
								 <s:if test="%{tag.metric == 1}">style="color:red;"</s:if><s:else>style="color:#000000;"</s:else> href="info_view.action?id=${id}"  target="_blank">${company }</a></div>
								<div style="margin-left:5px;width:50px;float:left;"><s:if test="%{userId != null && userId > 0}">是</s:if><s:else>否</s:else></div>
								<div style="margin-left:5px;width:300px;float:left;">${project}</div>
								<div style="margin-left:5px;width:180px;float:left;">邮箱：${email }</div>
								<s:if test="%{userId != null && userId > 0}"><div style="margin-left:5px;width:100px;float:left;">ID：${userId }</div></s:if>
								<div style="margin-left:5px;width:180px;float:left;">电话：${phone }</div>
							</div>
						</s:iterator>
					</s:if>
					</div>
				</div> --%>
				<%-- <div style="width:370px;max-height:600px;float:left;margin-left:10px;">
					<div style="width:360px;float:left;line-height:25px;font-size:16px;">
						<div class="view_p_button"><strong>名片添加记录(${account.cardSize})</strong></div>
					</div>
					<div id="cards" style="overflow:scroll;width:370px;max-height:550px">
						<s:if test="%{account.cards != null && account.cards.size > 0}">
							<s:iterator value="account.cards">
								<div class="view_p_content_350 biankuang_gray_ding hand" >
									<div style="margin-left:10px;width:340px;float:left;">公司：<a href="info_view.action?id=${infoId}" target="_blank">${company}</a></div>
									<div style="margin-left:10px;width:170px;float:left;">姓名：${name}</div>
									<div style="margin-left:5px;width:165px;float:left;">性别：
										<s:if test="%{gender == 0}">未选择</s:if>
										<s:else>
											<s:if test="%{gender == 1}">男</s:if>
											<s:else>女</s:else>
										</s:else></div>
									<div style="margin-left:10px;width:170px;float:left;">部门：${branch }</div>
									<div style="margin-left:5px;width:165px;float:left;">电话：${phone }</div>
									<div style="margin-left:10px;width:170px;float:left;">职务：${position }</div>
									<div style="margin-left:5px;width:170px;float:left;">时间：${createTime}</div>
									<div style="margin-left:10px;width:165px;float:left;">邮箱：${email }</div>
								</div>
							</s:iterator>
						</s:if>
					</div>
				</div> --%>
			</div>
		</div>
		<div style="width:100%;" id="tab_2" class="hidden tab">
			<div style="width:1200px;margin-left: auto;margin-right: auto;min-height:500px;">
				<div style="width:100px;margin:10px 0 0 0;float:left;background-color:#F8F8F8 ;">
					<s:if test="%{admins != null}">
						<s:iterator value="admins">
							<s:if test="%{account.admin.id == id}">
								<div id="review_${id}" style="width:90px;border:1px solid #9CA1B0;" class="review boder_choose hand">&nbsp;${name}</div>
							</s:if>
							<s:else>
								<div id="review_${id}" style="width:90px;border:1px solid #9CA1B0;" class="review boder hand">&nbsp;${name}</div>
							</s:else>
						</s:iterator>
					</s:if>
				</div>
				<%-- <div style="width:370px;height:800px;float:left;">
					<div style="width:360px;float:left;line-height:25px;font-size:16px;">
						<div class="view_p_button"><strong>邮件记录(${account.mailSize})</strong></div>
					</div>
					<div id="calls" style="overflow:scroll;width:370px;height:700px;">
						<s:if test="%{account.mails != null && account.mails.size > 0}">
						<s:iterator value="account.mails">
							<div class="view_p_content_350 biankuang_gray_ding hand">
								<div style="margin-left:5px;width:320px;float:left;">公司：<a href="info_view.action?id=${infoId }" target="_blank" style="color:black;">${companyName }</a></div>
								<div style="margin-left:5px;width:170px;float:left;">邮件：<a style="color:blue;">${modeName }</a></div>
								<div style="margin-left:5px;width:170px;float:left;">时间：${sendTime }</div>
							</div>
						</s:iterator>
					</s:if>
					</div>
				</div> --%>
			</div>
		</div>
		<div style="width:100%;" id="tab_3" class="hidden tab">
			<input class="hidden" type="text" value="${account.admin.id }" id="review_id"/>
			<div style="width:1200px;margin-left: auto;margin-right: auto;min-height:500px;">
				<div style="width:100px;margin:10px 0 0 0;float:left;background-color:#F8F8F8 ;">
					<s:if test="%{admins != null}">
						<s:iterator value="admins">
							<s:if test="%{account.admin.id == id}">
								<div id="review_${id}" style="width:90px;border:1px solid #9CA1B0;" class="review boder_choose hand">&nbsp;${name}</div>
							</s:if>
							<s:else>
								<div id="review_${id}" style="width:90px;border:1px solid #9CA1B0;" class="review boder hand">&nbsp;${name}</div>
							</s:else>
						</s:iterator>
					</s:if>
				</div>
				<div style="width:800px;margin-left: auto;margin-right: auto;">
					<div style="width:250px;float:left;height:400px;line-height:30px;margin-top:30px;font-size:14px;">
						<div style="width:250px;height:40px;">
							<div style="float:left;width:80px;color:#4F94CD;font-size:18px;">基本信息</div>
						</div>
						<div style="width:250px;height:30px;"><div style="float:left;width:50px;">姓名:</div><div style="float:left;width:200px;">${account.admin.name }</div></div>
						<div style="width:250px;height:30px;"><div style="float:left;width:50px;">邮箱:</div><div style="float:left;width:200px;">${account.admin.email }</div></div>
						<div style="width:250px;height:30px;"><div style="float:left;width:50px;">电话:</div><div style="float:left;width:200px;">${account.admin.phone }</div></div>
						<div style="width:250px;height:30px;"><div style="float:left;width:50px;">昵称:</div><div style="float:left;width:200px;">${account.admin.nickName }</div></div>
						<div style="width:250px;height:30px;"><div style="float:left;width:50px;">职称:</div><div style="float:left;width:200px;">${account.admin.position }</div></div>
					</div>
					<div style="width:250px;float:left;height:400px;line-height:30px;margin-top:30px;font-size:14px;">
						<div style="width:250px;height:40px;">
							<div style="float:left;width:80px;color:#4F94CD;font-size:18px;">权限</div>
						</div>
						<s:iterator value="account.quanxians">
							<s:if test="%{status == 1}">
								<div style="width:250px;height:20px;line-height:20px;color:blue;float:left;">
									<s:if test="%{admin.id == 99999999}">
										<div style="width:50px;float:left;"><input name="quanxian" class="quanxian" checked type="checkbox" value="${quanxian}" style="float:left;width:50px;" /></div>
									</s:if>
									<div style="width:200px;float:left;">${name}</div>
								</div>
							</s:if>
							<s:else>
								<div style="width:250px;height:20px;line-height:20px;color:#afb4db;float:left;">
									<s:if test="%{admin.id == 99999999}">
										<div style="width:50px;float:left;"><input name="quanxian" class="quanxian" type="checkbox" value="${quanxian}" style="float:left;width:50px;" /></div>
									</s:if>
									<div style="width:200px;float:left;">${name}</div>
								</div>
							</s:else>
						</s:iterator>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<div class="spacer"></div>
	<div class="hidden totalPage">${totalPage}</div>
	<div class="hidden nowPage">${nowPage}</div>
	<div class="hidden nowId">${id}</div>
	</section>

	<script src="${applicationScope.staticPath}skin/js/hideshow.js"
		type="text/javascript"></script>
	<script
		src="${applicationScope.staticPath}skin/js/jquery.tablesorter.min.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="${applicationScope.staticPath}skin/js/jquery.equalHeight.js"></script>
	<script type="text/javascript"
		src="${applicationScope.staticPath}skin/js/index.js"></script>
		<script type="text/javascript" src="${applicationScope.staticPath}skin/js/view.js"></script>
	<%-- <script type="text/javascript">
		$(document).ready(function(){
				$(".quanxian").live("change", function(){
					var quanxians = document.all("quanxian");
					var quanxian = "";
					 for(i=0;i<quanxians.length;i++){
						  if(quanxians[i].checked){
							  $(quanxians[i]).parent().parent().css("color","blue");
							  quanxian += $(quanxians[i]).val();
						  }else{
							  $(quanxians[i]).parent().parent().css("color","#afb4db");
						  }
						}
					 var id = $("#review_id").val();
					 $.ajax({
							dataType:'json',
							url:'account_quanxian.action',
							data:{quanxian:quanxian,id:id}
						}).done(function(da){
							if(da.status == 1){
							}else{
								alert(da.msg);
							}
						});
				});
				$(".review").live('click', function(){
					var adminType = $("#adminType").val();
					var ids = $(this).attr('id').split("_");
					var id = ids[1];
					window.location.href="review.action?id="+id+"&adminType="+adminType; 
				});
				$(".account").live('click',function(){
					var adminType = $("#adminType").val();
					var id = $(this).attr("id");
					var infoId = $("#review_id").val();
					window.location.href="review.action?id="+infoId+"&adminType="+adminType;
				});
		});
	</script> --%>
</body>
</html>