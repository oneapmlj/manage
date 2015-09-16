<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${info.userId }</title>
<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/layout.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/index.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/view.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/public.css" type="text/css" media="screen" />
<script type="text/javascript" src="${applicationScope.staticPath}skin/js/timeselect/laydate.js"></script>
<script src="${applicationScope.staticPath}skin/js/jquery.min.js"></script>
</head>
<body>
	<s:include value="/WEB-INF/pages/public/header.jsp" />
	<section id="main" class="column"> 
	<div class="public_main width_3_quarter_customer">
	<div class="header">
		<div style="float:left;margin-left:20px;">
			<div style="float:left;height:20px;line-height:20px;margin:5px 0 0 5px;" class="sale_assign">
				<div style="float:left;width:40px;">销售：</div>
				<s:if test="%{info.sale > 0}">
					<div style="float:left;">
						<div style="float:left;color:blue;margin-right:10px;">${userGroups.saleName}</div>
							<s:if test="%{admin.id == info.sale}">
								<div style="float:left;width:40px;">
									<input style="width:38px;"  id="view_info_delete_sale" class="biankuang_blue_ding hand" type="button" value="放弃"/>
								</div>
							</s:if>
							<s:else>
								<s:if test="%{(admin.group == 4 || admin.group >= 7)&&admin.assign == 1}">
									<div style="float:left;width:40px;">
										<input style="width:38px;"  id="view_info_remind_sale" class="biankuang_blue_ding hand" type="button" value="提醒"/>
									</div>
									<div style="float:left;width:40px;">
										<input style="width:38px;"  id="view_info_back_sale" class="biankuang_blue_ding hand" type="button" value="收回"/>
									</div>
								</s:if>
							</s:else>
					</div>
				</s:if>
				<s:else>
					<div style="float:left;"><div style="float:left;width:30px;color:red;">无</div>
					<s:if test="%{(admin.group == 4 || admin.group == 7)&&admin.assign == 1}">
						<div style="float:left;width:40px;">
							<input style="width:38px;"  id="view_info_assign_sale" class="biankuang_blue_ding hand" type="button" value="分配"/>
						</div>
					</s:if>
					</div>
				</s:else>
			</div>
			<div style="float:left;height:20px;line-height:20px;margin:5px 0 0 5px;" class="presale_assign">
				<div style="float:left;width:40px;">售前：</div>
				<s:if test="%{info.preSale > 0}">
					<div style="float:left;">
						<div style="float:left;margin-right:10px;color:blue;">${info.preSaleName}</div>
						<%-- <s:if test="%{info.supportApply == null}"> --%>
							<s:if test="%{admin.id == info.preSale}">
								<div style="float:left;width:40px;">
									<input style="width:38px;"  id="view_info_delete_presale" class="biankuang_blue_ding hand" type="button" value="放弃"/>
								</div>
							</s:if>
							<s:else>
								<s:if test="%{(admin.group == 5 || admin.group == 6 || admin.group >= 7)&&admin.assign == 1}">
									<div style="float:left;width:40px;">
										<input style="width:38px;"  id="view_info_remind_presale" class="biankuang_blue_ding hand" type="button" value="提醒"/>
									</div>
									<div style="float:left;width:40px;">
										<input style="width:38px;"  id="view_info_back_presale" class="biankuang_blue_ding hand" type="button" value="收回"/>
									</div>
								</s:if>
							</s:else>
					</div>
				</s:if>
				<s:else>
					<div style="float:left;"><div style="float:left;width:30px;color:red;">无</div>
					<s:if test="%{(admin.group >= 7 || admin.group == 6 || admin.group == 5 || admin.group == 2 || admin.group == 3)&&admin.assign == 1}">
						<div style="float:left;width:40px;">
							<input style="width:38px;"  id="view_info_assign_presale" class="biankuang_blue_ding hand" type="button" value="分配"/>
						</div>
					</s:if>
					</div>
				</s:else>
			</div>
			<div style="float:left;height:20px;line-height:20px;margin:5px 0 0 5px;" class="support_assign">
				<div style="float:left;width:40px;">运营：</div>
				<s:if test="%{info.support > 0}">
					<div style="float:left;">
						<div style="float:left;color:blue;margin-right:10px;">${info.supportName}</div>
						<%-- <s:if test="%{info.supportApply == null}"> --%>
							<s:if test="%{admin.id == info.support}">
								<div style="float:left;width:40px;">
										<input style="width:38px;"  id="view_info_delete_support" class="biankuang_blue_ding hand" type="button" value="放弃"/>
									</div>
							</s:if>
							<s:else>
								<s:if test="%{(admin.group == 5 || admin.group == 2 || admin.group == 3 || admin.group==6 || admin.group >= 7)&&admin.assign == 1}">
									<div style="float:left;width:40px;">
										<input style="width:38px;"  id="view_info_remind_support" class="biankuang_blue_ding hand" type="button" value="提醒"/>
									</div>
									<div style="float:left;width:40px;">
										<input style="width:38px;"  id="view_info_back_support" class="biankuang_blue_ding hand" type="button" value="收回"/>
									</div>
								</s:if>
							</s:else>
					</div>
				</s:if>
				<s:else>
					<div style="float:left;"><div style="float:left;width:30px;color:red;">无</div>
					<s:if test="%{(admin.group == 5 || admin.group >= 7)&&admin.assign == 1}">
						<div style="float:left;width:40px;">
							<input style="width:38px;"  id="view_info_assign_support" class="biankuang_blue_ding hand" type="button" value="分配"/>
						</div>
					</s:if>
					</div>
				</s:else>
			</div>
		</div>
		<div style="width:350px;float:left;">
			<s:if test="%{userGroups.mark != null}">
				<div style="float:left;width:100px;color:red;"  class="remove_mark_view biankuang_gray menu_button">取消标记</div><input type="text" value="${userGroups.mark.id}"  class="add_mark_view_id hidden"/>
			</s:if>
			<s:else><div style="float:left;width:60px;"  class="add_mark_view biankuang_gray menu_button">标记</div><input type="text"  class="add_mark_view_id hidden"/></s:else>
			<div val1="${info.id}" style="float:left;width:60px;"  class="check_gongdan biankuang_gray menu_button">工单</div>
			
			<s:if test="%{userGroups.xiaoshouyi != null && userGroups.xiaoshouyi > 0}">
				<div style="float:left;width:120px;color:blue;"  class="biankuang_gray_ding menu_button">已推送销售</div>
			</s:if>
			<s:else><div style="float:left;width:80px;"  class="add_xiaoshouyi_view biankuang_gray menu_button">推送销售</div>
			<div style="float:left;width:80px;"><input class="add_xiaoshouyi_view_sale" type="text"/></div></s:else>
		</div>
		
	</div> 
	<input class="hidden" id="view_infoId" value="${info.id}"/>
	<div style="width:100%;min-width:1200px;">
		<div style="width:1150px;margin-left: auto;margin-right: auto;">
			<div class="view_info biankuang_gray_ding" style="margin-top:30px;width:1120px;margin-left:10px;float:left;line-height:25px;font-size:16px;">
				<div style="margin-left:20px;width:380px;float:left;">公司：<span>${userGroups.groupName }</span></div>
				<div style="float:left;width:280px;"><div style="float:left;width:48px;">项目：</div>
					<div style="float:left;"><span class="project_name">${userGroups.project }</span></div>
					
					<input style="float:left;" class="edit_project_name hidden"/>
					
				</div>
				<div style="float:left;font-size:14px;width:60px" class="blue hand click_edit_project_name">编辑</div>
				<div style="float:left;width:340px;"><div style="float:left;width:48px;">姓名：</div>
					<div style="float:left;" ><span class="name_name">${info.name }</span></div>
					<input style="float:left;margin-top:3px;" class="edit_name_name hidden"/>
				</div>
				<div style="margin-left:20px;width:380px;float:left;"><div style="float:left;width:90px;">Groug_ID：</div>
					<div style="float:left;"><span  id="group_id">${userGroups.groupId }</span></div>
					
				</div>
				<div style="float:left;width:340px;"><div style="float:left;width:80px;">用户状态：</div>
					<div style="float:left;"><div style="float:left;"><span >
					<s:if test="%{deleted == 0}">存在</s:if>
					<s:else>已删除</s:else>
					</span></div></div>
				</div>
				<div style="float:left;width:340px;"><div style="float:left;width:90px;">Parent_ID：</div>
					<div style="float:left;"><span>
					<s:if test="%{userGroups.parentId == 0}">无</s:if>
					<s:else><a href="http://manage.oneapm.com/user_group_view.action?id=${userGroups.parentId }">${userGroups.parentId }</a></s:else>
					</span></div>
				</div>
				<div style="margin-left:20px;width:380px;float:left;"><div style="float:left;width:48px;">邮箱：</div>
					<div style="float:left;" ><span class="name_name">${userGroups.userGroups[0].email }</span></div>
					<input style="float:left;margin-top:3px;" class="edit_name_name hidden"/>
				</div>
				<div style="float:left;width:340px;"><div style="float:left;width:80px;">到期时间：</div>
					<div style="float:left;"><span >${userGroups.expireTime }</span></div>
				</div>
				
				
		
			</div>
			
			<div style="width:400px;margin-left:10px;float:left;padding-bottom: 40px;">
				<div class="view_info biankuang_gray_ding" style="margin-top:30px;width:405px;float:left;line-height:25px;font-size:16px;">
				<s:if test="%{infos != null && infos.size > 0}">
					<div  >
						<s:iterator value="infos" >
					<div>			
						
					<%-- <div style="margin-left:20px;width:380px;float:left;">公司：<span>${info.company }</span></div>
					<div style="margin-left:20px;width:380px;float:left;">
						<div style="float:left;width:340px;"><div style="float:left;width:48px;">项目：</div>
							<div style="float:left;"><span class="project_name">${info.project }</span></div>
							<input style="float:left;margin-top:3px;" class="edit_project_name hidden"/>
						</div>
						<div style="float:left;font-size:14px;" class="blue hand click_edit_project_name">编辑</div>
					</div> --%>
					<div style="margin-left:20px;width:380px;float:left;">
						<div style="float:left;width:340px;"><div style="float:left;width:48px;">姓名：</div>
							<div style="float:left;" ><span class="name_name">${info.name }</span></div>
							<input style="float:left;margin-top:3px;" class="edit_name_name hidden"/>
						</div>
						<div style="float:left;font-size:14px;color:blue;" class="hand click_edit_name_name">编辑</div>
					</div>
					<div class="boder_line" style="width:350px;margin-left:30px;float:left;"></div>
					<s:if test="%{info.userId != null}"><div style="margin-left:20px;width:380px;float:left;">邮箱：<span  id="email">${info.email }</span></div></s:if>
					<s:else>
						<div style="margin-left:20px;width:380px;float:left;">
							<div style="float:left;width:340px;"><div style="float:left;width:48px;">邮箱：</div>
								<div style="float:left;" ><span class="email_name">${info.email }</span></div>
								<input style="float:left;margin-top:3px;" class="edit_email_name hidden"/>
							</div>
							<div style="float:left;font-size:14px;color:blue;" class="hand click_edit_email_name">编辑</div>
						</div>
					</s:else>
					<div style="margin-left:20px;width:380px;float:left;">
						<s:if test="%{info.zhengzailianxi != null && info.zhengzailianxi.adminId == admin.id}">
							<div style="float:left;width:340px;"><div style="float:left;width:48px;">电话：</div>
								<div style="float:left;" ><span class="phone_name">${info.phone }</span></div>
								<%-- <div style="float:left;" ><span class="phone_name_hidden hidden">联系可见</span></div> --%>
								<input style="float:left;margin-top:3px;" class="edit_phone_name hidden"/>
							</div>
							<div style="float:left;font-size:14px;color:blue;" class="hand click_edit_phone_name">编辑</div>
						</s:if>
						<s:else>
							<div style="float:left;width:340px;"><div style="float:left;width:48px;">电话：</div>
								<div style="float:left;" ><span >${info.phone }</span></div>
								<%-- <div style="float:left;" ><span class="phone_name_hidden">联系可见</span></div>
								<input style="float:left;margin-top:3px;" class="edit_phone_name hidden"/> --%>
							</div>
							<div style="float:left;font-size:14px;color:blue;" class="hand click_edit_phone_name hidden">编辑</div>
						</s:else>
					</div>
					<div style="margin-left:20px;width:380px;float:left;">
						<div style="float:left;width:340px;"><div style="float:left;width:48px;">QQ：</div>
							<div style="float:left;" ><span class="qq_name">${info.qq }</span></div>
							<input style="float:left;margin-top:3px;" class="edit_qq_name hidden"/>
						</div>
						<div style="float:left;font-size:14px;color:blue;" class="hand click_edit_qq_name">编辑</div>
					</div>
					<s:if test="%{info.userId != null}">
						<div class="boder_line" style="width:350px;margin-left:30px;float:left;"></div>
						<div style="margin-left:20px;width:70px;float:left;">激活：
							<s:if test="%{status == 1}">是</s:if>
							<s:else>否</s:else>
						</div>
						<div style="margin-left:20px;width:110px;float:left;">UserId：<span>${info.userId}</span></div>
						<div style="margin-left:20px;width:140px;float:left;">
							<div style="float:left;width:110px;"><div style="float:left;width:48px;">性别：</div>
								<div style="float:left;" ><span class="gender_name">
									<s:if test="%{info.gender==0}">未知</s:if>
									<s:if test="%{info.gender==1}">男</s:if>
									<s:if test="%{info.gender==2}">女</s:if>
								</span></div>
								<select style="float:left;margin-top:3px;" class="edit_gender_name hidden">
								</select>
							</div>
							<div style="float:left;font-size:14px;color:blue;" class="hand click_edit_gender_name">编辑</div>
						</div>
						<div style="margin-left:20px;width:300px;float:left;">注册时间：${info.createTime}</div>
						<div style="margin-left:20px;width:300px;float:left;">登录时间：${info.loginTime}</div>
					</s:if>
					<s:else>
						<div style="margin-left:20px;width:300px;float:left;">注册：否</div>
					</s:else>
					<div style="margin-left:20px;width:140px;float:left;">
							<div style="float:left;width:110px;"><div style="float:left;width:48px;">角色：</div>
								<div style="float:left;" ><span class="gender_name">
									<s:if test="%{info.role=='admin'}">admin</s:if>
									<s:if test="%{info.role=='manager'}">manager</s:if>
									<s:if test="%{info.role=='normal'}">normal</s:if>
								</span></div>
								<select style="float:left;margin-top:3px;" class="edit_gender_name hidden">
								</select>
							</div>
					</div>
					<div style="width:400px;float:left;line-height:25px;font-size:16px;">
						<div style="margin-left:20px;width:300px;float:left;">下载信息:<a href="javascript:showAll();" style="font-size:14px;color:blue;">  显示全部</a></div>
					</div>
					</div>
					</s:iterator>
					</div>
					</s:if>
				
					
					<%-- <div style="margin-left:20px;width:380px;float:left;">
						<div style="float:left;width:340px;"><div style="float:left;width:80px;">到期时间：</div>
							<div style="float:left;" ><span class="license_name">${info.expireTime }</span></div>
							<input style="float:left;width:150px;" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" class="edit_license_name hidden"/>
							<select class="hand edit_license_pay_level hidden">
								<option value="10">免费用户</option>
								<option value="30">付费用户</option>
								<option value="20">免费到期</option>
								<option value="40">付费到期</option>
							</select>
						</div>
						<s:if test="%{info.userId == null || admin.id == 99999999}">
							<div style="float:left;font-size:14px;color:blue;" class="hand click_edit_license_name">编辑</div>
						</s:if>
					</div>
					<div style="margin-left:20px;width:380px;float:left;">
						<div style="float:left;width:240px;"><div style="float:left;width:80px;">用户状态：</div>
							<div style="float:left;" class="pay_level"><span <s:if test="%{info.payLevel == 20 || info.payLevel == 40}">style="color:red;"</s:if>>${info.pay_level }</span></div>
						</div>
					</div> --%>
				</div>
				<s:if test="%{info.level >= 1}">
					<%-- <div style="width:410px;float:left;line-height:25px;font-size:12px;">
						<div class="view_p_button"><strong>名片:</strong></div>
						<div class="add_card biankuang_gray hand" style="float:left;padding:3px;width:400px;margin:5px 0 0 0;height:20px;">
							<div style="width:100%;height:20;font-size:16px;text-align: center;line-height:20px;">
								<strong>添加</strong>
							</div>
						</div>
						<div id="cards">
							<s:if test="%{info.cards != null && info.cards.size > 0}">
								<s:iterator value="info.cards">
									<div class="view_p_content biankuang_gray_ding"  cardId="${id }">
										<div style="margin-left:10px;width:40px;margin-left:370px;position:absolute;color:blue;" class="hand edit_card edit_card_${id }">编辑</div>
										<div style="margin-left:10px;width:165px;float:left;"><div style="width:30px;float:left;">名片:</div>${id}</div>
										<div style="margin-left:10px;width:165px;float:left;">
											<div style="width:30px;float:left;">姓名:</div>
											<div class="left card_name_${id }">${name}</div>
											<input style="width:130px;margin-top:3px" class="left card_name_edit_${id } hidden" type="text"/>
										</div>
										<div style="margin-left:10px;width:165px;float:left;">
											<div style="width:30px;float:left;">部门:</div>
											<div class="left card_branch_${id }">${branch}</div>
											<input style="width:130px;margin-top:3px" class="left card_branch_edit_${id } hidden" type="text"/>
										</div>
										<div style="margin-left:10px;width:165px;float:left;">
											<div style="width:30px;float:left;">电话:</div>
											<div class="left card_phone_${id }">${phone}</div>
											<input style="width:130px;margin-top:3px" class="left card_phone_edit_${id } hidden" type="text"/>
										</div>
										<div style="margin-left:10px;width:165px;float:left;">
											<div style="width:30px;float:left;">QQ:</div>
											<div class="left card_qq_${id }">${qq}</div>
											<input style="width:130px;margin-top:3px" class="left card_qq_edit_${id } hidden" type="text"/>
										</div>
										<div style="margin-left:10px;width:165px;float:left;">
											<div style="width:30px;float:left;">性别:</div>
											<div class="left card_gender_${id }" gender=${gender }>
												<s:if test="%{gender == 0}">未选择</s:if>
												<s:else>
													<s:if test="%{gender == 1}">男</s:if>
													<s:else>女</s:else>
												</s:else>
											</div>
											<select style="margin-top:3px;" class="left hidden card_gender_edit_${id }">
												<option value="0">不选择</option>
												<option value="1">男</option>
												<option value="2">女</option>
											</select>
										</div>
										<div style="margin-left:10px;width:165px;float:left;">
											<div style="width:30px;float:left;">职位:</div>
											<div class="left card_position_${id }">${position}</div>
											<input style="width:130px;margin-top:3px" class="left card_position_edit_${id } hidden" type="text"/>
										</div>
										<div style="margin-left:10px;width:165px;float:left;"><div style="width:30px;float:left;">来源:</div><div style="color:blue;float:left;">${fromName}</div></div>
										<div style="margin-left:10px;width:300px;float:left;">
											<div style="width:30px;float:left;">邮箱:</div>
											<div class="left card_email_${id }">${email}</div>
											<input style="width:260px;margin-top:3px" class="left card_email_edit_${id } hidden" type="text"/>
										</div>
									</div>
								</s:iterator>
							</s:if>
						</div>
					</div> --%>
					
					
					<s:if test="%{info.downloads != null && info.downloads.size > 0}">
					<div id="download_view_${info.userId }"  style="display:none">
						<s:iterator value="info.downloads" >
							<div class="view_p_content biankuang_gray_ding">
								<div style="margin-left:10px;width:165px;float:left;">ID：${id }</div>
								<div style="margin-left:5px;width:170px;float:left;">Agent：${agentName }</div>
								<div style="margin-left:10px;width:165px;float:left;">版本：${vesion }</div>
								<div style="margin-left:5px;width:170px;float:left;">时间：${downloadTime }</div>
							</div>
						</s:iterator>
					</div>
					</s:if>
					<div style="width:400px;float:left;line-height:25px;font-size:16px;">
						<div class="view_p_button"><strong>添加应用信息:</strong></div>
					</div>
					<s:if test="%{userGroups.apps != null && userGroups.apps.size > 0}">
						<s:iterator value="userGroups.apps">
							<div class="view_p_content biankuang_gray_ding" val="${appId }">
								<div style="margin-left:10px;width:165px;float:left;">ID：${appId }</div>
								<div style="margin-left:5px;width:170px;float:left;">时间：${createTime }</div>
								<div style="margin-left:10px;width:165px;float:left;">name：${appName }</div>
								<div style="margin-left:5px;width:170px;float:left;">语言：${language }</div>
								<div style="margin-left:10px;width:165px;float:left;">版本：${version }</div>
								<div style="margin-left:5px;width:170px;float:left;">应用：${parentId }</div>
								<div style="margin-left:10px;width:300px;float:left;" val1="${agent}" val2="${agentId}">
									<div style="width:180px;float:left;">最近数据：${dataTime }</div>
									<div style="width:30px;float:left;color:blue;" class="hand app_data_view">>>>></div>
									<div style="width:80px;float:left;" class="app_data_view_content_${appId }_${agent }_${agentId}"></div>
								</div>
							</div>
						</s:iterator>
					</s:if>
				</s:if>
			</div>
			<div style="width:430px;float:left;padding-bottom: 40px;margin-top:30px;margin-left:30px;font-size:14px;">
				<%-- <div style="width:430px;height:25px;float:left;font-size:16px;" id="lianxi">
					<s:if test="%{info.zhengzailianxi != null}">
						<div style="color:red;float:left;" >${info.zhengzailianxi.adminName }正在联系.......</div>
						<s:if test="%{info.zhengzailianxi.adminId == admin.id}"><div style="float:left;"><input id="unzhengzailianxi" class="hand" style="color:red;" type="button" value="联系完毕"/></div></s:if>
					</s:if>
					<s:else>
						<input type="button" value="现在联系" class="hand" id="zhengzailianxi"/>
					</s:else>
				</div> --%>
				<div class="add_call biankuang_gray hand" style="float:left;padding:3px;width:400px;margin:5px 0 0 0;height:20px;">
					<div style="width:100%;height:20px;font-size:16px;text-align: center;line-height:20px;">
						<strong>添加记录</strong>
					</div>
				</div>
				<div id="calls" style="width:410px;height:300px;">
					<s:if test="%{userGroups.calls != null && userGroups.calls.size > 0}">
					<s:iterator value="userGroups.calls">
						<div class="view_p_content biankuang_gray_ding">
							<div style="margin-left:5px;width:175px;float:left;">操作:${adminName }</div>
							<div style="margin-left:5px;width:180px;float:left;">时间:${callTime }</div>
							<div style="margin-left:5px;width:175px;float:left;">名片:
								<s:if test="%{cardId >=100}">${cardId }</s:if>
								<s:else>
									<s:if test="%{cardId == 1}">注册</s:if>
									<s:else>无</s:else>
								</s:else>
							</div>
							<s:if test="%{typeName!= null}"><div style="margin-left:5px;width:180px;float:left;">类型:${typeName }</div></s:if>
							<s:else><div style="margin-left:5px;width:180px;float:left;">类型:未记录</div></s:else>
							<s:if test="%{gongdan != null}"><div style="margin-left:5px;width:175px;float:left;">工单:${gongdan }</div></s:if>
							<s:if test="%{todu > 0}"><div style="margin-left:5px;width:175px;float:left;">指令:<span style="color:blue;">
								<s:if test="%{todu == 1000}">推送销售</s:if>
								<s:if test="%{todu == 1001}">技术支持</s:if>
								<s:if test="%{todu == 1002}">再联系</s:if>
								<s:if test="%{todu == 1003}">关闭</s:if>
								</span></div>
							</s:if>
							<div style="margin-left:5px;width:350px;float:left;">记录:</div>
							<div style="margin-left:20px;width:350px;float:left;font-size:13px;">${mark}</div>
						</div>
					</s:iterator>
				</s:if>
				</div>
				<div style="width:400px;float:left;line-height:25px;font-size:16px;">
					<div class="view_p_button"><strong>邮件记录:</strong></div>
				</div>
				<div class="add_mail biankuang_gray hand" style="float:left;padding:3px;width:400px;margin:5px 0 0 0;height:20px;">
					<div style="width:100%;height:20px;font-size:16px;text-align: center;line-height:20px;">
						<strong>发送邮件</strong>
					</div>
				</div>
				<%-- <div id="mails" style="overflow:scroll;width:430px;max-height:300px">
					<s:if test="%{userGroups.mails != null && userGroups.mails.size > 0}">
						<s:iterator value="userGroups.mails">
							<div class="view_p_content biankuang_gray_ding">
								<div style="margin-left:5px;width:170px;float:left;">操作者：${adminName }</div>
								<div style="margin-left:5px;width:180px;float:left;">时间：${sendTime }</div>
								<div style="margin-top:5px;margin-left:5px;width:300px;float:left;">模板名称：
									<span>${modeName }</span>
								</div>
							</div>
						</s:iterator>
					</s:if>
					<s:else>
						<span>暂无邮件信息</span>
					</s:else>
				</div> --%>
				
				<div id="sendcloud"  style="overflow:scroll;display:none; width:460px;position:relative;max-height:300px">
						
							
							<!-- <div id="sendcloud_date" style="margin-left:5px;width:70px;float:left;"></div>
								<div id="sendcloud_id" style="margin-left:5px;width:70px;float:left;"></div>
								<div id="sendcloud_event" style="margin-left:5px;width:80px;float:left;"></div>
								<div id="sendcloud_email" style="margin-left:5px;width:80px;float:left;"></div>
								<div id="sendcloud_url" style="margin-left:5px;width:80px;float:left;"></div>
								<div id="sendcloud_labelId" style="margin-left:5px;width:80px;float:left;"></div>
							
							 -->
						
				
					
				</div>
				<input type = "button" id = "sendcloudbtn" value="查看用户邮件记录"/>
				<!-- <input type = "button" id = "exportexcel" value="导出成excel"/> -->
			</div>
			<s:if test="%{info.pushs != null}">
				<div style="width:250px;float:left;margin:30px 0 0 10px;font-size:12px; " class="biankuang_gray_ding">
					<div style="width:100%;height:20px;margin:5px 0 0 10px;font-size:14px;">任务:</div>
					<div style="100%">
						<s:iterator value="userGroups.pushs">
							<div style="<s:if test='%{point}'>color:red;</s:if>border-top: 1px solid #9BA0AF;width:230px;margin-left:10px;height:30px;line-height:30px;">
								<div style="float:left;width:60px;">
									<s:if test="%{type == 1}">未激活</s:if>
									<s:if test="%{type == 2}">欢迎使用</s:if>
									<s:if test="%{type == 3}">未下载</s:if>
									<s:if test="%{type == 4}">未登录</s:if>
									<s:if test="%{type == 5}">无应用</s:if>
									<s:if test="%{type == 6}">登录断点</s:if>
									<s:if test="%{type == 7}">数据断点</s:if>
									<s:if test="%{type == 9}">再联系</s:if>
									<s:if test="%{type == 10}">销售商务</s:if>
									<s:if test="%{type == 11}">技术支持</s:if>
									<s:if test="%{type == 12}">最新应用</s:if>
								</div>
								<div style="float:left;width:85px;">
									<s:if test="%{from == 0}">系统推送</s:if>
									<s:if test="%{from == 1}">转交(${fromName })</s:if>
									<s:if test="%{from == 2}">生成(${fromName })</s:if>
									<s:if test="%{from == 3}">回收(${fromName })</s:if>
								</div>
								<div style="float:left;width:65px;">
									${adminName }
								</div>
								<s:if test="%{remove}">
									<div style="float:left;width:20px;" id="task_view_${id}">
										<input style="width:15px;height:15px;margin-top:7px;" class="task_remove" type="image" title="关闭" src="${applicationScope.staticPath}skin/images/icn_logout.png"/>
									</div>
								</s:if>
							</div>
						</s:iterator>
					</div>
				</div>
			</s:if>
			<div style="width:250px;float:left;margin:30px 0 0 10px;font-size:12px;" class="biankuang_gray_ding">
				<div style="float:left;width:210px;margin:5px 0 0 10px;font-size:16px;">关联帐号</div>
				<div style="float:left;width:220px;margin:5px 0 0 10px;">
					<input value="增加" type="button" class="left hand guanlian" id="guanlian"/>
					<input type="text" class="hand left hidden" style="width:90px;" id="guanlian_add_value"/>
					<input type="button" value="保存" class="hand hidden"  id="guanlian_add"/>
					<div class="left" style="width:220px;" id="guanlian_value">
						<s:iterator value="info.guanlians" id="ingl">
							<div style="width:210px;float:left;line-height:20px;margin-top:5px;" id="guanlian_value_${guanlianId}">
								<s:if test="%{userId == guanlianId}">
									<div style="float:left;width:80px;" class="menu_button hand  biankuang_gray_ding" id="guanlian_value_${userId}">当前</div>
									<s:if test="%{ role == 1}">								
									 <div class="guanlian_change menu_button  hand  biankuang_blue_ding" style="float:left;width:80px;">主帐号</div> 									
									</s:if>
								</s:if> 
								
								 <s:if test="%{userId != guanlianId}">		 							
									<div style="float:left;width:80px;" class="guanlian_view menu_button hand  biankuang_gray" id="${guanlianId}">${guanlianId }</div>
									<s:if test="%{role  == 1}">
									
									 <div class="guanlian_change menu_button  hand  biankuang_blue_ding" style="float:left;width:80px;">主帐号</div> 
									
									</s:if>
									<s:else>		 							
											 <div class="guanlian_change menu_button  hand  biankuang_gray_ding" style="float:left;width:80px;">定为主帐号</div> 
											 <div class="guanlian_remove menu_button  hand  biankuang_gray_ding" style="float:left;width:20px;">X</div>
								 		</s:else> 
								 </s:if> 
								 	
								 		
							
							</div>
						</s:iterator>
					</div>
				</div>
			</div>
			<div style="width:250px;float:left;padding-bottom: 40px;margin:0 0 0 10px;font-size:14px;">
				<div  id="right_side" style="margin:10px 0 0 0;width:250px;height:800px;" class="biankuang_gray_ding">
					<s:if test="%{info.tag != null}">
						<input id="view_tag_id" class="hidden"  value="${userGroups.tag.id}"/>
						<div id="view_edit_tag" style="width:230px;line-height:30px;">
							<div style="width:230px;height:20px;margin:15px 0 0 15px;">
								<div style="float:left;width:60px;">注册：</div>
								<%-- <div style="float:left;width:170px;">${info.tag.lou.name}</div> --%>
								<div style="float:left;width:170px;color:#4F4F4F;">
									<s:if test="%{info.comming == null}">未知</s:if>
									<s:else>${info.from }</s:else>
								</div>
							</div>
							<div style="width:230px;height:20px;margin:15px 0 0 15px;">
								<div style="float:left;width:80px;">用户状态：</div>
								<div style="float:left;width:120px;" class="group_edit_1">
									<s:if test="%{userGroups.group == null}">
										<input val="0" type="button" class="group_area_1 biankuang_white_ding hand" style="float:left;width:110px;height:23px;line-height:20px;" value="状态未知"/>
									</s:if>
									<s:else>
										<s:if test="%{userGroups.groupFather != null}">
											<input val="${userGroups.groupFather.id }" type="button" class="group_area_1 biankuang_white_ding hand" style="float:left;width:110px;height:23px;line-height:20px;" value="${userGroups.groupFather.name}"/>
										</s:if>
										<s:else>
											<input val="${userGroups.group.id }" type="button" class="group_area_1 biankuang_white_ding hand" style="float:left;width:110px;height:23px;line-height:20px;" value="${userGroups.group.name}"/>
										</s:else>
									</s:else>
								</div>
							</div>
							<div style="width:230px;height:20px;margin:15px 0 0 15px;">
								<div style="float:left;width:80px;">状态分类：</div>
								<div style="float:left;width:120px;" class="group_edit_2">
									<s:if test="%{userGroups.groupFather != null}">
										<input type="button" class="group_area_2 biankuang_white_ding hand" style="float:left;width:110px;height:23px;line-height:20px;" value="${userGroups.group.name}"/>
									</s:if>
									<s:else>
										<input type="button" class="group_area_2 biankuang_white_ding hand" style="float:left;width:110px;height:23px;line-height:20px;" value="无"/>
									</s:else>
								</div>
							</div>
							<div style="width:230px;height:20px;margin:10px 0 0 15px;">
								<div style="float:left;width:45px;">定位：</div>
								<%-- <div style="float:left;width:170px;">${info.tag.lou.name}</div> --%>
								<div style="float:left;width:180px;">
									<s:iterator value="METRIC">
										<s:if test="%{id > 0}">
											<s:if test="%{userGroups.tag.metric == id}">
												<input id="view_edit_tag_metric_${id}" type="button" class="view_edit_tag_metric biankuang_blue_ding hand" style="margin: 5px 0 0 5px;width:50px;height:20px;line-height:18px;" value="${name}"/>
											</s:if>
											<s:else>
												<input id="view_edit_tag_metric_${id}" type="button" class="view_edit_tag_metric biankuang_white_ding hand" style="margin: 5px 0 0 5px;width:50px;height:20px;line-height:18px;" value="${name}"/>
											</s:else>
										</s:if>
									</s:iterator>
								</div>
							</div>
							<%-- <div style="width:230px;height:20px;margin:5px 0 0 15px;">
								<div style="float:left;width:50px;">进度：</div>
								<div style="float:left;width:170px;">
									<s:iterator value="LOUDOU">
										<s:if test="%{info.tag.loudou == id}">
											<div id="view_edit_tag_loudou" style="width:50px;float:left;">${name }</div>
										</s:if>
									</s:iterator>
									<s:iterator value="LOUDOU">
										<s:if test="%{info.tag.loudou+1 == id}">
											<div  style="width:100px;float:left;">
												<input id="view_edit_tag_loudou_next" title="点击进入下一阶段" type="button" class="view_edit_tag_loudou biankuang_blue_ding hand" value="下一阶段:${name }"/>
											</div>
										</s:if>
									</s:iterator>
								</div>
							</div> --%>
							<%-- <div style="width:230px;height:120px;margin:15px 0 0 15px;">
								<div style="float:left;width:45px;">语言：</div>
								<div style="float:left;width:170px;">${info.tag.lou.name}</div>
								<div style="height:100px;float:left;width:180px;">
									<s:iterator value="LANGUAGE">
										<s:if test="%{id > 0}">
											<input id="view_edit_tag_language_${id}" type="button" class="view_edit_tag_language biankuang_white_ding hand" style="margin: 5px 0 0 5px;width:50px;height:24px;line-height:20px;" value="${name}"/>
										</s:if>
									</s:iterator>
								</div>
							</div> --%>
							<div style="margin:15px 0 0 185px;width:35px;line-height:30px;height:30px;position:absolute;" id="view_edit_tag_click" class="hand">
								<input  class="biankuang_blue_ding hand" type="button" value="编辑"/>
							</div>
							<div style="width:230px;height:20px;margin:40px 0 0 15px;">
								<div style="float:left;width:60px;">来源：</div>
								<div style="float:left;width:170px;color:#4F4F4F;">
									<div style="width:120px;" id="view_edit_tag_from">
										<s:if test="%{userGroups.tag.from==0}">未填写</s:if>
										<s:if test="%{userGroups.tag.from == 1}">搜索</s:if>
										<s:if test="%{userGroups.tag.from == 2}">朋友介绍</s:if>
										<s:if test="%{userGroups.tag.from == 3}">展会</s:if>
										<s:if test="%{userGroups.tag.from == 4}">媒体</s:if>
										<s:if test="%{userGroups.tag.from==5}">QQ群</s:if>
										<s:if test="%{userGroups.tag.from == 6}">微博</s:if>
										<s:if test="%{userGroups.tag.from == 7}">微信</s:if>
										<s:if test="%{userGroups.tag.from == 8}">邮件</s:if>
										<s:if test="%{userGroups.tag.from == 9}">论坛</s:if>
										<s:if test="%{userGroups.tag.from == 10}">开发者平台</s:if>
									</div>
									<select class="hand hidden" style="width:120px;" id="view_edit_tag_from_val">
										<option value="0" <s:if test="%{userGroups.tag.from==0}">selected = "selected" </s:if>>不确定</option>
										<option value="1" <s:if test="%{userGroups.tag.from==1}">selected = "selected" </s:if>>搜索</option>
										<option value="2" <s:if test="%{userGroups.tag.from==2}">selected = "selected" </s:if>>朋友介绍</option>
										<option value="3" <s:if test="%{userGroups.tag.from==3}">selected = "selected" </s:if>>展会</option>
										<option value="4" <s:if test="%{userGroups.tag.from==4}">selected = "selected" </s:if>>媒体</option>
										<option value="5" <s:if test="%{userGroups.tag.from==5}">selected = "selected" </s:if>>QQ群</option>
										<option value="6" <s:if test="%{userGroups.tag.from==6}">selected = "selected" </s:if>>微博</option>
										<option value="7" <s:if test="%{userGroups.tag.from==7}">selected = "selected" </s:if>>微信</option>
										<option value="8" <s:if test="%{userGroups.tag.from==8}">selected = "selected" </s:if>>邮件</option>
										<option value="9" <s:if test="%{userGroups.tag.from==9}">selected = "selected" </s:if>>论坛</option>
										<option value="10" <s:if test="%{userGroups.tag.from==10}">selected = "selected" </s:if>>开发者平台</option>
									</select>
								</div>
							</div>
							<div style="width:230px;height:20px;margin:10px 0 0 15px;">
								<div style="float:left;width:60px;">区域：</div>
								<%-- <div style="float:left;width:170px;">${info.tag.lou.name}</div> --%>
								<div style="float:left;width:170px;color:#4F4F4F;">
									<div style="width:120px;" id="view_edit_tag_province">
										<s:if test="%{userGroups.tag.province==0}">未填写</s:if>
										<s:else>
											<s:iterator value="PROVINCE">
											<s:if test="%{userGroups.tag.province == id}">
												${name}
											</s:if>
										</s:iterator>
										</s:else>
									</div>
									<select class="hand hidden" style="width:120px;" id="view_edit_tag_province_val">
										<s:iterator value="PROVINCE">
											<s:if test="%{userGroups.tag.province == id}">
												<option  selected = "selected" value="${id}">${name}</option>
											</s:if>
											<s:else>
												<option value="${id}">${name}</option>
											</s:else>
										</s:iterator>
									</select>
								</div>
							</div>
							<div style="width:230px;height:20px;margin:10px 0 0 15px;">
								<div style="float:left;width:60px;">人数：</div>
								<%-- <div style="float:left;width:170px;">${info.tag.lou.name}</div> --%>
								<div style="float:left;width:170px;color:#4F4F4F;">
									<div style="width:120px;" id="view_edit_tag_person">
										<s:if test="%{userGroups.tag.person==0}">未填写</s:if>
										<s:else>
											<s:iterator value="PERSON">
											<s:if test="%{userGroups.tag.person == id}">
												${name}
											</s:if>
										</s:iterator>
										</s:else>
									</div>
									<select class="hand hidden" style="width:120px;" id="view_edit_tag_person_val">
										<s:iterator value="PERSON">
											<s:if test="%{userGroups.tag.person == id}">
												<option  selected = "selected" value="${id}">${name}</option>
											</s:if>
											<s:else>
												<option value="${id}">${name}</option>
											</s:else>
										</s:iterator>
									</select>
								</div>
							</div>
							<%-- <div style="width:230px;height:20px;margin:5px 0 0 15px;">
								<div style="float:left;width:60px;">PV：</div>
								<div style="float:left;width:170px;">${info.tag.lou.name}</div>
								<div style="float:left;width:170px;color:#4F4F4F;">
									<div style="width:120px;" id="view_edit_tag_pv">
										<s:if test="%{info.tag.pv == 0]">未填写</s:if>
										<s:else>
											<s:iterator value="PV">
												<s:if test="%{info.tag.pv == id}">
													${name}
												</s:if>
											</s:iterator>
										</s:else>
									</div>
									<select class="hand hidden" style="width:120px;" id="view_edit_tag_pv_val">
										<s:iterator value="PV">
											<s:if test="%{info.tag.pv == id}">
												<option  selected = "selected" value="${id}">${name}</option>
											</s:if>
											<s:else>
												<option value="${id}">${name}</option>
											</s:else>
										</s:iterator>
									</select>
								</div>
							</div> --%>
							<div style="width:230px;height:20px;margin:10px 0 0 15px;">
								<div style="float:left;width:60px;">融资：</div>
								<div style="float:left;width:170px;color:#4F4F4F;">
									<div style="width:120px;" id="view_edit_tag_rongzi">
										<s:if test="%{userGroups.tag.rongzi == 0}">未定义</s:if>
										<s:else>
											<s:iterator value="RONGZI">
												<s:if test="%{userGroups.tag.rongzi == id}">
													${name}
												</s:if>
											</s:iterator>
										</s:else>
									</div>
									<select class="hand hidden" style="width:120px;" id="view_edit_tag_rongzi_val">
										<s:iterator value="RONGZI">
											<s:if test="%{userGroups.tag.rongzi == id}">
												<option  selected = "selected" value="${id}">${name}</option>
											</s:if>
											<s:else>
												<option value="${id}">${name}</option>
											</s:else>
										</s:iterator>
									</select>
								</div>
							</div>
							<div style="width:230px;height:20px;margin:10px 0 0 15px;">
								<div style="float:left;width:60px;">分类：</div>
								<div style="float:left;width:170px;color:#4F4F4F;">
									<div style="width:120px;" id="view_edit_tag_category">
										<s:if test="%{userGroups.tag.category == 0}">未填写</s:if>
										<s:else>
											<s:iterator value="CATEGORY">
												<s:if test="%{userGroups.tag.category == id}">
													${name}
												</s:if>
											</s:iterator>
										</s:else>
									</div>
									<select class="hand hidden" style="width:120px;" id="view_edit_tag_category_val">
										<s:iterator value="CATEGORY">
											<s:if test="%{userGroups.tag.category == id}">
												<option  selected = "selected" value="${id}">${name}</option>
											</s:if>
											<s:else>
												<option value="${id}">${name}</option>
											</s:else>
										</s:iterator>
									</select>
								</div>
							</div>
							<div style="width:230px;height:20px;margin:10px 0 0 15px;">
								<div style="float:left;width:60px;">平台：</div>
								<div style="float:left;width:170px;color:#4F4F4F;">
									<div style="width:120px;" id="view_edit_tag_fuwuqi">
										<s:if test="%{userGroups.tag.fuwuqi == 0}">未选择</s:if>
										<s:else>
											<s:iterator value="FUWUQI">
												<s:if test="%{userGroups.tag.fuwuqi == id}">
													${name}
												</s:if>
											</s:iterator>
										</s:else>
									</div>
									<select class="hand hidden" style="width:120px;" id="view_edit_tag_fuwuqi_val">
										<s:iterator value="FUWUQI">
											<s:if test="%{userGroups.tag.fuwuqi == id}">
												<option  selected = "selected" value="${id}">${name}</option>
											</s:if>
											<s:else>
												<option value="${id}">${name}</option>
											</s:else>
										</s:iterator>
									</select>
								</div>
							</div>
							<div style="width:230px;height:20px;margin:5px 0 0 15px;">
								<div style="float:left;width:150px;">简介：</div>
							</div>
							<div style="margin-left:15px;float:left;width:220px;font-size:12px;height:200px;">
								<s:if test="%{userGroups.tag.description != null && userGroups.tag.description.length() > 0}">
									<div style="width:190px;margin-left:15px;color:#4F4F4F;" id="view_edit_tag_description">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${userGroups.tag.description}</div>
								</s:if>
								<s:else><div style="width:190px;margin-left:15px;color:#4F4F4F;" id="view_edit_tag_description">未填写</div></s:else>
								<textarea style="height:80px;width:200px;" class="hidden" id="view_edit_tag_description_val">${userGroups.tag.description}</textarea>
							</div>
						</div>
					</s:if>
				</div>
			</div>
		</div>
	</div>
	</div>
	</section>
	<div class="card_add hidden" style="z-index:1002;">
		<div class="window">
				<div id="add_card_window_close" style="cursor: pointer;float:right;width:20px;line-height:20px;text-align:center;border-left:1px solid #B8B8B8;border-bottom:1px solid #B8B8B8;">x</div>
			<div style="margin:20px 0 0 30px;">
					<div>姓名：<input name="name" id="add_card_name" tabindex="1" type="text"/></div>
					<div>性别：
						<select name="gender" id="add_card_gender" tabindex="2" >
							<option value="0">不选择</option>
							<option value="1">男</option>
							<option value="2">女</option>
						</select>
					</div>
					<div>部门：<input name="branch" id="add_card_branch" tabindex="3" type="text"/></div>
					<div>电话：<input name="phone" id="add_card_phone" tabindex="4" type="text"/></div>
					<div>职务：<input name="position" id="add_card_position" tabindex="5" type="text"/></div>
					<div>邮箱：<input name="email" id="add_card_email" tabindex="6" type="text"/></div>
					<div>QQ：<input style="margin-left:7px;" name="qq" id="add_card_qq" tabindex="6" type="text"/></div>
					<div><input val1="${info.id}"  tabindex="7" id="add_card_tijiao"  type="button"  style="width:40px;float:right;margin:10px 40px 20px 0;" value="提交"/></div>
					<input class="hidden keyboard_card_enter" type="button" value="1"/>
			</div>
		</div>
	</div>
	<div class="mail_add hidden" style="z-index:1002;font-size:13px;">
		<div class="window">
			<s:if test="%{MAIL_MODE != null}">
				<div style="margin-top:5px;float:left;">模板：</div>
				<select id="view_mail_mode" style="margin:15px 0 0 0;height:25px;width:100px;float:left;"  class="hand" >
					<s:iterator value="MAIL_MODE">
						<option value="${id}">${name}</option>
					</s:iterator>
				</select>
			</s:if>
			<input type="button" class="hand" val1="${info.id}" id="view_send_mail" style="margin:15px 0 0 10px;float:left;" value="发送"/>
			<input type="button" class="hand" val1="${info.id}" id="view_preview_mail" style="margin:15px 0 0 10px;float:left;" value="预览"/>
			<div id="add_mail_window_close" class="hand" style="float:right;width:20px;line-height:20px;text-align:center;border-left:1px solid #B8B8B8;border-bottom:1px solid #B8B8B8;">x</div>
		</div>
	</div>
	<div class="call_add hidden" >
		<div class="window" style="min-height:300px;;max-height:600px;">
				<div id="add_call_window_close" style="cursor: pointer;float:right;width:20px;line-height:20px;text-align:center;border-left:1px solid #B8B8B8;border-bottom:1px solid #B8B8B8;">x</div>
			<div style="margin:40px 0 0 10px;font-size:16px;">
					<div style="width:440px;float:left;"  class="call_add_select1">
						<div id="selects">
							
						</div>
					</div>
					<s:if test="%{admin.id==99999999}">
						<input id="note_add_button" class="hand" style="margin-top:5px;margin-left:10px;"  type="button"  value="添加新标签"/>
						<div id="note_add" class="hidden">
							<div style="margin:10px 0 0 10px;float:left;width:440px;height:">
								<input type="text" style="float:left;width:60px" id="notetype_add_text"/>
								<input style="float:left;width:70px;" class="hand" value="添加选项" id="notetype_add" type="button"/>
								<input type="text" style="float:left;margin-left:10px;width:60px" id="notetype_add_text_chiled"/>
								<select id="notetype_add_chiled_father" class="hand" style="float:left;width:80px;height:21px;"></select>
								<input style="float:left;width:70px;" class="hand" value="添加子项" id="notetype_add_chiled" type="button"/>
							</div>
							<div style="margin:10px 0 0 10px;float:left;width:440px;height:">
								<select id="notetype_add_todu" class="hand" style="float:left;width:80px;height:21px;">
									<option value="0">其他操作</option>
									<s:iterator value="todus">
										<option value="${id }">${name }</option>
									</s:iterator>
								</select>
								<input id="note_add_false" class="hand" type="button" value="取消标签添加"/>
							</div>
						</div> 
					</s:if>
					<div style="margin:5px 0 0 10px;float:left;width:440px;height:">
						<div class="contact call_add_card"  style="width:430px;float:left;"><div style="float:left;width:50px;">名片:</div>
							<div style="float:left;">
								<select style="float:left;margin-top:5px;" name="card" id="add_call_card" class="hand" tabindex="1">
									<option value="0">---请选择---</option>
									<s:if test="%{info.userId != null && info.userId > 0}">
										<option value="1">注册信息</option>
									</s:if>
									<s:if test="%{info.cards != null && info.cards.size > 0}">
										<s:iterator value="info.cards">
											<option value="${id}">名片：${id}</option>
										</s:iterator>
									</s:if>
								</select>
							</div>
						</div>
						<%-- <div class="contact"  style="width:440px;float:left;line-height:23px;"><div style="float:left;width:46px;">QQ:</div>
							<div style="float:left;"><input id="call_add_qq" style="float:left;width:160px;" type="text" /></div>
							<div style="float:left;width:46px;margin-left:10px;">姓名:</div>
							<div style="float:left;"><input id="call_add_name" style="float:left;width:160px;" type="text" /></div>	
						</div>
						<div class="contact"  style="width:440px;float:left;line-height:23px;margin-top:3px;">
							<div style="float:left;width:46px;">电话:</div>
							<div style="float:left;"><input id="call_add_phone" style="float:left;width:160px;" type="text" /></div>	
							<div style="float:left;width:46px;margin-left:10px;">邮箱:</div>
							<div style="float:left;"><input id="call_add_email"  style="float:left;width:160px;" type="text" /></div>
						</div>
						<div class="contact"  style="width:440px;float:left;line-height:23px;margin-top:3px;">
							<div style="float:left;width:46px;">部门:</div>
							<div style="float:left;"><input id="call_add_branch" style="float:left;width:160px;" type="text" /></div>	
							<div style="float:left;width:46px;margin-left:10px;">职位:</div>
							<div style="float:left;"><input id="call_add_position"  style="float:left;width:160px;" type="text" /></div>
						</div>
						<div class="contact"  style="width:440px;float:left;line-height:23px;margin-top:3px;">
							<div style="float:left;width:46px;">性别:</div>
							<div style="float:left;width:160px;">
								<select class="hand" id="call_add_gender">
									<option value="0">不选择</option>
									<option value="1">男</option>
									<option value="2">女</option>
								</select></div>
							<div style="float:left;width:46px;margin-left:15px;"">工单:</div>
							<div style="float:left;"><input id="call_add_gongdan"  style="float:left;width:160px;" type="text" /></div>
						</div> --%>
					</div>
					<div style="margin:5px 0 0 10px;float:left;width:440px;height:">
						<div class="todu call_add_sale hidden"  style="color:blue;">附带操作：推送销售</div>
						<div class="todu call_add_support hidden"  style="color:blue;">附带操作：技术支持</div>
						<div class="todu hidden call_add_time_show">
							<div style="float:left;width:46px;">日期:</div>
							<div style="float:left;width:200px;">
								<input style="width:180px;" placeholder="请输入日期" type="text"
								 id="call_add_time" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/>
							</div>
							<div style="float:left;width:60px;"><input class="hand" id="call_add_point" type="checkbox"/>重点</div>
							<%-- <div style="float:left;width:300px;color:red;">还有<span id="before_task">0</span>个任务需要完成</div> --%>
						</div>
						<%-- <div class="todu hidden call_add_time_show"   style="color:blue;">日期：
							<select id="call_add_time">
								<option value="0">现在</option>
								<option value="1">3小时</option>
								<option value="2">6小时</option>
								<option value="3">12小时</option>
								<option value="4">1天</option>
								<option value="5">2天</option>
								<option value="6">3天</option>
								<option value="7">1周</option>
							</select>
						</div> --%>
						<div class="todu call_add_guanbi hidden"  style="color:blue;">附带操作：关闭</div>
					</div>
					<div style="float:left;width:440px;height:20px;color:red;margin-left:10px;" id="warming_msg" class="hidden">请选择类型！</div>
					<div style="width:460;float:left;margin-top:20px;">
					
						<div>
							<div id="testtest"
								 style="position:absolute;width:80px;margin:35px 0 0 20px;background-color:#C1CDCD;font-size:12px;line-height:20px;" >
								<!-- <div style="border:1px solid #B5ACDC;width:78px;height:20px;" class="at_view hand">
									<div style="width:70px;height:20px;margin-left:5px;">李江</div>
								</div>
								<div style="border:1px solid #B5ACDC;width:78px;height:20px;" class="at_view hand">
									<div style="width:70px;height:20px;margin-left:5px;">孟智</div>
								</div>
								<div style="border:1px solid #B5ACDC;width:78px;height:20px;" class="at_view hand">
									<div style="width:70px;height:20px;margin-left:5px;">超级管理员</div>
								</div>
								<div style="border:1px solid #B5ACDC;width:78px;height:20px;" class="at_view hand">
									<div style="width:70px;height:20px;margin-left:5px;">支持小组</div>
								</div>
								<div style="border:1px solid #B5ACDC;width:78px;height:20px;" class="at_view hand">
									<div style="width:70px;height:20px;margin-left:5px;">赵伟</div>
								</div> -->
							</div>
							<textarea name="mark" id="add_call_mark" style="width:440px;height:100px;" cols="50"></textarea>
						</div>
					</div>
					<div style="width:440px;float:left;">
					<input class="hand" tabindex="7" id="add_call_tijiao"  type="button"  style="width:40px;float:right;margin:10px 40px 20px 0;" value="提交"/></div>
					<input class="hidden keyboard_call_enter" type="button" value="1"/>
			</div>
		</div>
	</div>

	<script src="${applicationScope.staticPath}skin/js/hideshow.js" type="text/javascript"></script>
	<script src="${applicationScope.staticPath}skin/js/note.js" type="text/javascript"></script>
	<script src="${applicationScope.staticPath}skin/js/jquery.tablesorter.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/jquery.equalHeight.js"></script>
	<%-- <script type="text/javascript" src="${applicationScope.staticPath}skin/js/time.js"></script> --%>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/index.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/view.js"></script>
	<script type="text/javascript">
	
		  $("#sendcloudbtn").click(function(){
			  
		  
		  	var email = $("#email").html();
			if(email==null){
				email =  $(".email_name").html();
			}
			if(email==null){
				return false;
			}
			$("#sendcloud").slideToggle(50);
		 	     $.ajax({
						dataType:'json',
						url:'info_findByEmail.action',
						data:{email:email}
					}).done(function(data){
						if(data.status==1){
								 var listCnter = $("#sendcloud").empty();
								var rows = data.sdList; 
								var table = $('<table  width="400" border="0" cellspacing="0" cellpadding="0"></table>').appendTo(listCnter);
								var titleTr = $('<tr height="25px;"></tr>').appendTo(table);
								var dateTd = $('<td width="61"></td>').appendTo(titleTr);
								var eventTd = $('<td width="61"></td>').appendTo(titleTr);						
								var labelIdTd = $('<td width="61"></td>').appendTo(titleTr);						
								var urlTd = $('<td width="61"></td>').appendTo(titleTr);		
								var dateSpan = $('<span></span>').html("日期").appendTo(dateTd);
								var eventSpan = $('<span></span>').html("事件").appendTo(eventTd);
								var labelIdSpan = $('<span></span>').html("labelId").appendTo(labelIdTd);
								var urlSpan = $('<span></span>').html("URL").appendTo(urlTd);
								 for(var i=0; i<rows.length; i++){
									var r = rows[i];
									var table = $('<table  width="400" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"></table>').appendTo(listCnter);
									var titleTr = $('<tr height="25px;"></tr>').appendTo(table);
									var dateTd = $('<td width="61"></td>').appendTo(titleTr);
									var eventTd = $('<td width="61"></td>').appendTo(titleTr);						
									var labelIdTd = $('<td width="61"></td>').appendTo(titleTr);						
									var urlTd = $('<td width="61"></td>').appendTo(titleTr);		
									var dateSpan = $('<span></span>').html(r.date?r.date:"").appendTo(dateTd);
									var eventSpan = $('<span></span>').html(r.event?r.event:"").appendTo(eventTd);
									var labelIdSpan = $('<span></span>').html(r.labelId?r.labelId:"").appendTo(labelIdTd);
									if(r.url=="null"){
										r.url="";										
									}
									var url_sub = r.url;
									if(r.url.length>20){
										url_sub=r.url.substring(0,20)+"...";
									}
									var urlSpan = $('<a href="'+r.url+'" target="_Blank" title="'+r.url+'"></a>').html(url_sub).appendTo(urlTd);
									
								} 
							
						}
					});
			    }) 
		/* 	$("#exportexcel").click(function(){
				var name = $(".name_name").html();
				var email = $("#email").html();
				var phone = $(".phone_name").html();
				var qq = $(".qq_name").html();
				 $.ajax({
						dataType:'json',
						url:'info_exportExcel.action',
						data:{id:infoId}
					}).done(function(data){
						if(data.status==1){
							alert(data.msg);
						}else{
							alert(data.msg);
						}
					
					
					
					
					})
			}) */
			    
		var call_height = function(){
			var height = Number($("#calls").height());
			if(height > 299){
				$("#calls").css("overflow-x","scroll");
			}
		}
		call_height();
		var language = function(){
			var infoId = $("#view_infoId").val();
			$.ajax({
				dataType:'json',
				url:'tag_language_list.action',
				data:{infoId:infoId}
			}).done(function(da){
				if(da.status == 1){
					var language = da.language;
					if(language != null && language.length>0){
						for(var i=0;i<language.length;i++){
							$("#view_edit_tag_language_"+language[i]).removeClass('biankuang_white_ding');
							$("#view_edit_tag_language_"+language[i]).addClass('biankuang_blue_ding');
						}
					}
				}else{
					alert(da.msg);
				}
			});
		};
		language();
		var userId =${info.userId };
		var infoId =${info.id };
		$(document).ready(function(){
			
		
		
			$("#add").click(function(){
				var title = $("#title").val();
				var id = $("#mode_id").val();
				var status = $("#status").val();
				var content = $("#content").val();
				$.ajax({
					dataType:'json',
					url:'mode_insert.action',
					data:{title:title,id:id,status:status,content:content}
				}).done(function(da){
					alert(da.status);
				});
			});
		});
		
		window.document.onkeydown = function(){
			if(event.keyCode==13)
		    {
		    	//document.activeElement.click();   
		    	if($(".keyboard_card_enter").val()=="0"){
					$("#add_card_tijiao").click();
		    	}
		    }
		}
		$("#add_card_window_close").click(function(){
			$(".card_add").addClass("hidden");
			$(".keyboard_card_enter").val("1");
			$(".background").addClass("hidden");
		});
		
		$("#add_call_window_close").click(function(){
			$(".background").addClass("hidden");
			$(".call_add").addClass("hidden");
			$(".keyboard_call_enter").val("1");
		});
		
	</script>
</body>
</html>