<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${userGroups.groupId }</title>
<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/layout.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/index.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/view.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/public.css" type="text/css" media="screen" />
<script type="text/javascript" src="${applicationScope.staticPath}skin/js/timeselect/laydate.js"></script>
<script src="${applicationScope.staticPath}skin/js/jquery.min.js"></script>
</head>
<body>
	<div style="width:400px;margin-left:10px;float:left;padding-bottom: 40px;">
				<div >
				<s:if test="%{userGroupList != null && userGroupList.size > 0}">
					<div  >
						<s:iterator value="userGroupList" >
						
					<div class="view_info biankuang_gray_ding" val="${info.id }" style="margin-top:30px;width:405px;float:left;line-height:25px;font-size:16px;">			
						
					<%-- <div style="margin-left:20px;width:380px;float:left;">公司：<span>${info.company }</span></div>
					<div style="margin-left:20px;width:380px;float:left;">
						<div style="float:left;width:340px;"><div style="float:left;width:48px;">项目：</div>
							<div style="float:left;"><span class="project_name">${info.project }</span></div>
							<input style="float:left;margin-top:3px;" class="edit_project_name hidden"/>
						</div>
						<div style="float:left;font-size:14px;" class="blue hand click_edit_project_name">编辑</div>
					</div> --%>
					<div style="margin-left:20px;width:380px;float:left;">
						<div style="float:left;width:340px;"><div style="float:left;width:48px;">姓名:</div>
							<div style="float:left;" ><span class="name_name">${info.name }</span></div>
							<input style="float:left;margin-top:3px;"  class="edit_name_name hidden"/>
						</div>
						<div style="float:left;font-size:14px;color:blue;" class="hand click_edit_name_name">编辑</div>
					</div>
					<div class="boder_line" style="width:350px;margin-left:30px;float:left;"></div>
					<s:if test="%{info.userId != null}"><div style="margin-left:20px;width:380px;float:left;">邮箱:<span  class="email ${info.userId}">${info.email }</span></div></s:if>
					<s:else>
						<div style="margin-left:20px;width:380px;float:left;">
							<div style="float:left;width:340px;"><div style="float:left;width:48px;">邮箱:</div>
								<div style="float:left;" ><span class="email_name">${info.email }</span></div>
								<input style="float:left;margin-top:3px;" class="edit_email_name hidden"/>
							</div>
							<div style="float:left;font-size:14px;color:blue;" class="hand click_edit_email_name">编辑</div>
						</div>
					</s:else>
					<div style="margin-left:20px;width:380px;float:left;">
						<s:if test="%{info.zhengzailianxi != null && info.zhengzailianxi.adminId == admin.id}">
							<div style="float:left;width:340px;"><div style="float:left;width:48px;">电话:</div>
								<div style="float:left;" ><span class="phone_name">${info.phone }</span></div>
								<%-- <div style="float:left;" ><span class="phone_name_hidden hidden">联系可见</span></div> --%>
								<input style="float:left;margin-top:3px;" class="edit_phone_name hidden"/>
							</div>
							<div style="float:left;font-size:14px;color:blue;" class="hand click_edit_phone_name">编辑</div>
						</s:if>
						<s:else>
							<div style="float:left;width:340px;"><div style="float:left;width:48px;">电话:</div>
								<div style="float:left;" ><span >${info.phone }</span></div>
								<%-- <div style="float:left;" ><span class="phone_name_hidden">联系可见</span></div>
								<input style="float:left;margin-top:3px;" class="edit_phone_name hidden"/> --%>
							</div>
							<div style="float:left;font-size:14px;color:blue;" class="hand click_edit_phone_name hidden">编辑</div>
						</s:else>
					</div>
					<div style="margin-left:20px;width:380px;float:left;">
						<div style="float:left;width:340px;"><div style="float:left;width:48px;">QQ:</div>
							<div style="float:left;" ><span class="qq_name">${info.qq }</span></div>
							<input style="float:left;margin-top:3px;" class="edit_qq_name hidden"/>
						</div>
						<div style="float:left;font-size:14px;color:blue;" class="hand click_edit_qq_name">编辑</div>
					</div>
					<%-- <s:if test="%{info.userId != null}"> --%>
						<div class="boder_line" style="width:350px;margin-left:30px;float:left;"></div>
						<div style="margin-left:20px;width:60px;float:left;">激活:
							<s:if test="%{status == 1}">是</s:if>
							<s:else>否</s:else>
						</div>
						<div style="margin-left:20px;width:130px;float:left;">UserId:<span id=${info.userId }>${info.userId}</span></div>
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
					<%-- </s:if>
					<s:else>
						<div style="margin-left:20px;width:300px;float:left;">注册：否</div>
					</s:else> --%>
					<div style="margin-left:20px;width:180px;float:left;">
							<div style="float:left;width:110px;"><div style="float:left;width:48px;">角色：</div>
								<div style="float:left;" ><span >${role}</span></div>
							</div>
					</div>
					<div style="margin-left:20px;width:300px;float:left;">工单：
					<a href="javascript:showAll();" val1="${info.id}" style="font-size:15px;color:blue;" class="check_gongdan">  点击进入</a>
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
				<div class="view_p_content biankuang_gray_ding">
				<div style="width:400px;float:left;line-height:25px;font-size:16px;">
						<div style="margin-left:20px;width:300px;float:left;">下载信息:
					<%-- 	<s:iterator value="userGroupList" >
				<s:if test="%{info.downloads != null && info.downloads.size > 0}">
				</s:if></s:iterator> --%>
						共<a href="javascript:showAll();" style="font-size:14px;color:blue;"> 
						${downloadsNum}
						</a>条 
						 </div>
					</div>
					</div>
					
				<s:iterator value="userGroupList" >
				<s:if test="%{info.downloads != null && info.downloads.size > 0}">
					<div class="download_view"  style="display:none">
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
					</s:iterator>
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
			</div>
</body>
</html>