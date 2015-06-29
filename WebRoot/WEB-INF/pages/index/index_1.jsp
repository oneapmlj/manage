<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="tab_container">
	<div class="tab_content">
	<table class="tablesorter" cellspacing="0"> 
	<thead> 
		<tr> 
			<th style="width:5%;"></th>
			<th style="width:2%;"></th>
  			<th style="width:6%;">ID</th> 
  			<th style="width:10%;">Name</th>
  			<th style="width:14%;">Project</th>
  			<th style="width:15%;">Language</th>
  			<th style="width:6%;">Sale</th>
  			<th style="width:6%;">Support</th>
  			<th style="width:6%;">PreSale</th>
  			<th style="width:10%">Edit</th>
  			<th style="width:5%"></th>
		</tr> 
	</thead> 
	<tbody class="tbody"> 
		<s:if test="%{report == null || report.users == null || report.users.length <= 0}">
		</s:if>
		<s:else>
			<s:iterator value="report.infos">
				<tr <s:if test="%{level == 2}">style="color:#00FF00;"</s:if>
					class="hand"> 
					<td></td>
		 			<td><input class="user_id" type="checkbox" name="code_Value" value="${id}" /></td> 
					<td>${userId}</td> 
					<td>${name}</td> 
					<td>${company}</td>
					<td>${language }</td>
					<s:if test="%{saleName != null}">
						<td>${saleName }</td>
					</s:if>
					<s:else><td class="green">无</td></s:else>
					<s:if test="%{supportName != null}">
						<td>${supportName }</td>
					</s:if>
					<s:else><td class="green">无</td></s:else>
					<s:if test="%{preSaleName != null}">
						<td>${preSaleName }</td>
					</s:if>
					<s:else><td class="green">无</td></s:else>
					<td>
						<s:if test="%{level >0}">
							<input val1="${id}" class="check_gongdan"  type="image" src="${applicationScope.staticPath }skin/images/icn_view_users.png" title="工单" />
							<input val1="${id}" class="check_view"  type="image" src="${applicationScope.staticPath }skin/images/icn_view_users.png" title="查看" />
						</s:if>
						<input class="check_remove" type="image" src="${applicationScope.staticPath}skin/images/icn_logout.png" title="忽略" />
					</td> 
					<td></td>
				</tr> 
			</s:iterator>
		</s:else>
	</tbody> 
	</table>
	</div>
</div>
