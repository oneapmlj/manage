<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!-- Totango Tracking Code -->
<script> 
	var admin_id = "${attr.admin_id}";
	var username = "${attr.username}";
	/* var admin = function(){
		$.ajax({
			dataType:'json',
			url:'account_admin.action',
			data:{}
		}).done(function(da){
			if(da.status == 1){
				admin_id=da.admin_id;
				username=da.username;
			}
		});
	}
	admin(); */
	var totango_options = {
		service_id : "SP-9085-01",
		user : {
			id : "geliang@oneapm.com",
			name : "admin"
		},
		account : {
			id : admin_id,
			name : username,
			status : "Paying",
			"Create Date" : "2011-12-20T19:25:58.0Z"
		},
	};
	(function() {
		var tracker_name = window.totango_options.tracker_name || "totango";
		window.totango_tmp_stack = [];
		window[tracker_name] = {
			go : function() {
				return -1;
			},
			setAccountAttributes : function() {
			},
			identify : function() {
			},
			track : function(t, o, n, a) {
				window.totango_tmp_stack.push({
					activity : t,
					module : o,
					org : n,
					user : a
				});
				return -1;
			}
		};
		var e = document.createElement('script');
		e.type = 'text/javascript';
		e.async = true;
		e.src = ('https:' == document.location.protocol ? 'https://'
				: 'http://')
				+ 'tracker.totango.com/totango3.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(e, s);
	})();
</script>
<!-- End Totango Tracking Code -->
<!-- Begin Inspectlet Embed Code -->
<%-- <script type="text/javascript" id="inspectletjs">
	window.__insp = window.__insp || [];
	__insp.push(['wid', 1182810273]);
	(function() {
		function __ldinsp(){var insp = document.createElement('script'); insp.type = 'text/javascript'; insp.async = true; insp.id = "inspsync"; insp.src = ('https:' == document.location.protocol ? 'https' : 'http') + '://cdn.inspectlet.com/inspectlet.js'; var x = document.getElementsByTagName('script')[0]; x.parentNode.insertBefore(insp, x); }
		if (window.attachEvent){
			window.attachEvent('onload', __ldinsp);
		}else{
			window.addEventListener('load', __ldinsp, false);
		}
	})();
</script> --%>
<!-- End Inspectlet Embed Code -->
<%-- <script type="text/javascript">
		mixpanel.track("Video play");
		mixpanel.identify("12148");
		/* mixpanel.people.set({
		    "$email": "jsmith@example.com",    // only special properties need the $
		    
		    "$created": "2011-03-16 16:53:54",
		    "$last_login": new Date(),         // properties can be dates...
		    
		    "credits": 150,                    // ...or numbers
		    
		    "gender": "Male"                    // feel free to define your own properties
		}); */
	</script>
	<script type="text/javascript" src="http://maven.natero.com/source/natero_analytics.min.js"></script>
	<script>
    window.onload = function() {
    	
        _na = new na('aveonfeko5uuhe6r0ziv4zhlcizxkovo ', '879bc0f9c414b2fda47c4cee1cd91eaf',
                     {
        		userId:'123456',
            		accountId:'654321',
                         trackUnload: true,
                         debugUrl:
                            "http://test.natero.com/api/submitEvents/aveonfeko5uuhe6r0ziv4zhlcizxkovo",

                         disableEventSend: false, // disable the sending of events
                         debug: false // disable console debug prints
                     });
        _na.setModuleId('test');
    };
</script> --%>

<div class="warming hidden">
	<div id="warming"
		style="margin-left: auto; margin-right: auto; width: 800px;"></div>
</div>
<header id="header">
	<hgroup>
		<h1 class="site_title">
			<a href="/">OneApm Manager</a>
		</h1>
		<h2 class="section_title">OneApm 后台管理</h2>
		<div class="btn_view_site">
			<a class="loginOut" href="#">注销</a>
		</div>
	</hgroup>
</header>
<div class="header_menu">
	<div class="user">
		<p>${admin.name}</p>
		<input id="adminId" class="hidden" value="${admin.id}" /> <input
			id="admin_group" class="hidden" value="${admin.group}" />
	</div>
	<div style="margin-left: 20px; float: left; width: 1000px; height: 38px;">
		<div class="message_history biankuang_gray menu_button"
			style="width: 80px;">消息</div>
		<div class="click_to_mark biankuang_gray menu_button">标记</div>
		<div class="click_to_add menu_button biankuang_gray" title="添加" style="display:none">
			添加</div>
		<div class="click_to_search menu_button biankuang_gray" title="搜索">
			搜索</div>
		<div class="click_to_account menu_button biankuang_gray" title="帐号">
			帐号</div>
		<div class="click_to_file menu_button biankuang_gray" title="文档">
			文档</div>
		<div class="click_to_task menu_button biankuang_gray" style="width: 80px;" title="任务">
			任务(<span style="color: red;" id="task_length">0</span>)
		</div>
		<s:if test="%{(admin.group >= 4 || admin.manage == 1) && admin.group != 1}"> 
			<div class="click_to_tongji menu_button biankuang_gray" title="统计">
				统计</div>
			<!-- <div class="click_to_baobiao menu_button biankuang_gray" title="报表">
				报表</div> -->
		</s:if>
		<s:if test="%{admin.group >= 4 || admin.duandian == 1}"> 
			<div class="click_to_duandian menu_button biankuang_gray" title="过滤" >过滤</div>
		</s:if>
		<s:if test="%{admin.id == 99999999}">
		</s:if>
		<s:if test="%{admin.group >= 4 || admin.review == 1}">
			<div class="click_to_review menu_button biankuang_gray"
				title="review">review</div>
		</s:if>
		<%-- <s:if test="%{admin.pay == 1}">
			<div class="click_to_pay menu_button biankuang_gray" title="pay">pay</div>
		</s:if> --%>
		<!-- <div class="click_to_zhengzailianxi menu_button biankuang_gray" style="width:80px;"" title="pay">正在联系</div> -->
		<div style="width: 280px; z-index: 999; position: absolute; margin: 28px 0 0 6px;" id="message_window" class="hidden hand biankuang_gray_ding">
			<div style="width: 280px; heigth: 30px; float: left;">\
				<div style="float: left; width: 80px; height: 30px;color:blue;margin-left:20px; font-size: 13px; line-height: 30px;" class="message_history_all">查看所有</div>
				<div style="float: right; width: 15px; height: 30px; font-size: 14px; line-height: 30px;" class="message_click_to_close">X</div>
			</div>
			<div id="message_view"
				style="width: 250px; margin: 0 0 10px 15px; float: left; line-height: 20px;">
				<div style="width: 250px; border-top: 1px solid #FFFFFF;">
					<div style="width: 250px;">
						<div style="color: blue; float: left;">李江</div>
						<div style="float: left;">&nbsp;向你申请成为&nbsp;</div>
						<div style="color: blue; float: left;">阳光保险</div>
						&nbsp;销售散发的是负责人
					</div>
					<div style="width: 250px; height: 20px;">
						<div style="float: right; width: 90px; color: red;">
							<div style="float: left; width: 30px;">查看</div>
							<div style="float: left; width: 30px;">同意</div>
							<div style="float: left; width: 30px;">拒绝</div>
						</div>
					</div>
				</div>
				<div style="width: 250px; border-top: 1px solid #FFFFFF;">
					<div style="width: 250px; height: 20px;">
						<div style="color: blue; float: left;">李江</div>
						<div style="float: left;">&nbsp;向你申请成为&nbsp;</div>
						<div style="color: blue; float: left;">阳光保险</div>
						&nbsp;负责人
					</div>
					<div style="width: 250px; height: 20px;">
						<div style="float: right; width: 90px; color: red;">
							<div style="float: left; width: 30px;">查看</div>
							<div style="float: left; width: 30px;">同意</div>
							<div style="float: left; width: 30px;">拒绝</div>
						</div>
					</div>
				</div>
				<div style="width: 250px; border-top: 1px solid #FFFFFF;">
					<div style="width: 250px; height: 20px;">
						<div style="color: blue; float: left;">李江</div>
						<div style="float: left;">&nbsp;拒绝了你对成为&nbsp;</div>
						<div style="color: blue; float: left;">阳光保险</div>
						&nbsp;负责人的申请
					</div>
					<div style="width: 250px; height: 20px;">
						<div style="float: right; width: 60px; color: red;">
							<div style="float: left; width: 30px;">查看</div>
							<div style="float: left; width: 30px;">确定</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%-- <s:if test="%{report.updateTime != null}">
		<div class="btn_view_site2">
					<a class="update" id="${id}" href="#">更新</a>
			</div>
		<div style="margin-right: 2%; float: right; width: 16%; height: 38px;">
			<s:if test="%{report.updateTime != null}">
				<p>更新时间：${report.updateTime}</p>
			</s:if>
		</div>
	</s:if> --%>
</div>
<div style="z-index: 1002;" class="index_add hidden">
	<div class="window">
		<div id="add_window_close"
			style="cursor: pointer; float: right; width: 20px; line-height: 20px; text-align: center; border-left: 1px solid #B8B8B8; border-bottom: 1px solid #B8B8B8;">x</div>
		<div style="margin: 20px 0 0 30px;">
			<form method="get" id="add_info" action="info_add.action"
				accept-charset="utf-8">
				<div>
					公司：<input name="company" id="add_company" tabindex="1" type="text" />
				</div>
				<div>
					姓名：<input name="name" id="add_name" tabindex="2" type="text" />
				</div>
				<div>
					电话：<input name="phone" id="add_phone" tabindex="3" type="text" />
				</div>
				<div>
					邮箱：<input name="email" id="add_email" tabindex="4" type="text" />
				</div>
				<div>
					QQ ：<input name="qq" id="add_qq" tabindex="5" type="text" />
				</div>
				<div>
					<input tabindex="6" id="add_tijiao" type="button"
						style="width: 40px; float: right; margin: 10px 40px 20px 0;"
						value="搜索" />
				</div>
				<input class="hidden keyboard_enter" type="button" value="1" />
			</form>
		</div>
	</div>
</div>
<div style="border: 1px solid #ccc; top: 200px; position: fixed; left: 200px; width: 900px; z-index: 1002; height: 300px; background-color: #F8F8F8;"
	id="view_window" class="hidden">
	<div class="hidden" id="view_window_id"></div>
	<div
		style="width: 430px; max-height: 500px; float: left; margin: 20px 0 0 20px;">
		<div
			style="width: 400px; float: left; line-height: 25px; font-size: 16px;">
			<div
				style="width: 398px; font-size: 16px; height: 25px; padding-left: 10px; margin: 5px 0 0 0; cursor: pointer; line-height: 25px; color: #4F94CD;">
				<strong>电话记录:</strong>
			</div>
		</div>
		<div id="calls_header" style="width: 430px;"></div>
	</div>
	<div
		style="width: 400px; max-height: 500px; float: left; margin: 20px 0 0 20px;">
		<div
			style="width: 400px; float: left; line-height: 25px; font-size: 16px;">
			<div
				style="width: 398px; font-size: 16px; height: 25px; padding-left: 10px; margin: 5px 0 0 0; cursor: pointer; line-height: 25px; color: #4F94CD;">
				<strong>邮件记录:</strong>
			</div>
		</div>
		<div id="mails_header" style="width: 430px;"></div>
	</div>
</div>
<div class="background hidden"></div>
<script src="${applicationScope.staticPath}skin/js/header.js" type="text/javascript"></script>
