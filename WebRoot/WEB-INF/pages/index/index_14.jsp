<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="tab_container">
	<div class="tab_content">
	<table class="tablesorter" cellspacing="0"> 
	<thead> 
		<tr> 
			<th style="width:5%;"></th>
  			<th style="width:8%;">ID</th> 
  			<th style="width:8%;">Name</th>
  			<th style="width:14%;">Email</th>
  			<th style="width:10%;">Company</th>
  			<th style="width:12%;">Phone</th>
  			<th style="width:15%;">Edit</th>
		</tr> 
	</thead> 
	<tbody> 
		<s:if test="%{report == null || report.users == null || report.users.length <= 0}">
		</s:if>
		<s:else>
			<s:iterator value="report.infos">
				<tr <s:if test="%{tag.metric==1}">style="color:red"</s:if>
					<s:if test="%{tag.metric==3}">style="color:blue;"</s:if>  class="hand">
		 			<td><input class="user_id" type="checkbox" name="code_Value" value="${infoId}" /></td> 
					<td>${userId}</td> 
					<td>${name}</td> 
					<td>${email}</td> 
					<td>${company}</td>
					<td>${phone}</td>
					<td>
						<s:if test="%{admin.group == 2 || admin.group == 4 || admin.group == 5}">
							<input val1="${id}" class="check_gongdan"  type="image" src="${applicationScope.staticPath }skin/images/icn_view_users.png" title="工单" />
						</s:if>
						<s:if test="%{level == 1}">
							<input val1="${id}" class="check_view"  type="image" src="${applicationScope.staticPath }skin/images/icn_view_users.png" title="查看" />
						</s:if>
						<input class="check_remove" type="image" src="${applicationScope.staticPath}skin/images/icn_logout.png" title="忽略" />
					</td> 
				</tr> 
			</s:iterator>
		</s:else>
	</tbody> 
	</table>
	</div>
</div>
