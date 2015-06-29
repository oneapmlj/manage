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
	<script src="${applicationScope.staticPath}skin/js/timeAjax.js"></script>
</head>
<body>
	<s:include value="/WEB-INF/pages/public/header.jsp" />
	<section id="main" class="column">
		<div class="public_main width_3_quarter_customer">
		<div class="header">
			<div style="float:left"><h3 class="tabs_involved">分组信息</h3></div>
		</div> 
			<div style="width:100%;">
				<div style="width:1200px;margin-left: auto;margin-right: auto;min-height:500px;">

				</div>
			</div>
		</div>
	</section>

	<script src="${applicationScope.staticPath}skin/js/hideshow.js" type="text/javascript"></script>
	<script src="${applicationScope.staticPath}skin/js/jquery.tablesorter.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/jquery.equalHeight.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/index.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/view.js"></script>
	<script type="text/javascript">
		var father = 0; 
		$(document).ready(function(){
			$(".group_add").live("click", function(){
				var name = $(".group_name").val();
				var description = $(".group_description").val();
				$.ajax({
					dataType:'json',
					url:'group_insert.action',
					data:{father:father,name:name,description:description}
				}).done(function(da){
					if(da.status == 1){
						/* var html = $(".group_list_"+da.grade).html();
						html += "<option value='"+da.id+"'>"+da.name+"</option>";
						$(".group_list_"+da.grade).html(html); */
						aelrt("ok");
					}else{
						alert(da.msg)
					}
				});
			});
		});
	</script>
</body>
</html>