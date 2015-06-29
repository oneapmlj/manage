<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
	<s:include value="/WEB-INF/pages/public/header.jsp"/>
	<aside id="sidebar" class="column" style="position:absolute;margin-left:-17%;">
		<s:iterator value="menu">
			<s:if test="%{id == menuId}">
				<div id="menu_${menuId}" class="boder_choose">&nbsp;&nbsp;&nbsp;${name}</div>
			</s:if>
			<s:else>
				<div id="menu_${menuId}" class="boder">&nbsp;&nbsp;&nbsp;${name}</div>
			</s:else>
		</s:iterator>
		<footer>
			<p><strong>&copy; 2014 <a href="http://www.oneapm.com">OneAPM</a> Manager</strong></p>
		</footer>
	</aside>
	<script type="text/javascript">
		var show_view = false;
		var hide_view = false;
		var menu_show = false; 
		$(document).ready(function(){
			$("#sidebar").mouseover(function(){
				if(menu_show || show_view)return;
				menu_show = true;
				show_view = true;
				$(this).animate({'margin-left':'0%'},300,function(){
					menu_show = false;
					hide_view = false;
				});
			});
			$("#sidebar").mouseleave(function(){
				if(menu_show || hide_view)return;
				menu_show = true;
				hide_view = true;
				$(this).animate({'margin-left':'-17%'},300,function(){
					menu_show = false;
					show_view = false;
				});
			});
			$(".boder").click(function(){
				$(".boder_choose").addClass("boder");
				$(".boder_choose").removeClass(".boder_choose");
				var ids = $(this).attr("id");
				var id = ids.replace("menu_", "").trim();
				window.location.href="show.action?id="+id;
			});
		});
	</script>