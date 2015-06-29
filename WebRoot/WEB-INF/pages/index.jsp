<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>OneApm</title>
	
	<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/layout.css" type="text/css" media="screen" />
	<!--[if lt IE 9]>
	<link rel="stylesheet" href="css/ie.css" type="text/css" media="screen" />
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
	<script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
</head>
<style type="text/css">
.tyle1{width:100%;float:left;font-size:16px;line-height:20px;margin-top:10px;}
.button{width:100px;height:25px;float:left;cursor:pointer;}
.text{float:left;width:40px;height:20px;}
.hidden{visibility: hidden ;}
</style>
<body>
	<div style="margin:0 auto; width:500px;height:300px;margin-top:200px;">
		<div class="tyle1">
			<input type="button" class="button" id="value_0"  value="sleep1index"/>
			<input type="text" id="index_0" class="text" value="1"/>次
			<span id="index_0_text" class="hidden">还剩<span class="index_0_number"></span>次</span>
			<span>[1秒一次]</span>
		</div>
		<div class="tyle1">
			<input type="button" class="button" id="value_1" value="sleep5index"/>
			<input type="text" id="index_1" class="text" value="1"/>次
			<span id="index_1_text" class="hidden">还剩<span class="index_1_number"></span>次</span>
			<span>[5秒一次]</span>
		</div>
		<div class="tyle1">
			<input type="button" class="button" id="value_2" value="堆栈溢出index"/>
			<input type="text" id="index_2" class="text" value="1"/>次
			<span id="index_2_text" class="hidden">还剩<span class="index_2_number"></span>次</span>
			<span>[6秒一次]</span>
		</div>
		<div class="tyle1">
			<input type="button" class="button" id="value_3" value="sleep1"/>
			<input type="text" id="index_3" class="text" value="1"/>次
			<span id="index_3_text" class="hidden">还剩<span class="index_3_number"></span>次</span>
			<span>[1秒一次]</span>
		</div>
		<div class="tyle1">
			<input type="button" class="button" id="value_4" value="sleep5"/>
			<input type="text" id="index_4" class="text" value="1"/>次
			<span id="index_4_text" class="hidden">还剩<span class="index_4_number"></span>次</span>
			<span>[5秒一次]</span>
		</div>
		<div class="tyle1">
			<input type="button" class="button" id="value_5" value="堆栈溢出"/>
			<input type="text" id="index_5" class="text" value="1"/>次
			<span id="index_5_text" class="hidden">还剩<span class="index_5_number"></span>次</span>
			<span>[6秒一次]</span>
		</div>
		<div class="tyle1">
			<input type="button" class="button" id="value_6" value="主页"/>
			<input type="text" id="index_6" class="text" value="1"/>次
			<span id="index_6_text" class="hidden">还剩<span class="index_6_number"></span>次</span>
			<span>[0.4秒一次]</span>
		</div>
	</div>	
	<script src="${applicationScope.staticPath}skin/js/hideshow.js" type="text/javascript"></script>
	<script src="${applicationScope.staticPath}skin/js/jquery.tablesorter.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/jquery.equalHeight.js"></script>
	<script type="text/javascript">
		var index= new Array;
		var urls = new Array("/index.action?id=1",
					"/index.action?id=2",
					"/index.action?id=3",
					"/index_1.action",
					"/index_2.action",
					"/index_3.action",
					"/index.action?id=0");
		var secconds = new Array(1000,5000,6000,1000,5000,6000,400);
		var send_index = function(url, id){
			$.get(url,function(data){
			});
			setTimeout(
				function(){		
					index[id]--;
					index_text(true, id);
					if(index[id] >= 1){
						send_index(url,id);
					}else{
						index_text(false, id);
					}
			},secconds[id]);
		}
		/*  */
		var index_text = function(append, id){
			if(append){
				$(".index_"+id+"_number").html(index[id]);
				$("#index_"+id+"_text").removeClass("hidden");
			}else{
				$("#index_"+id+"_text").addClass("hidden");
			}
		}
		$(document).ready(function() {
			$(".button").click(function(){
				var id = $(this).attr("id");
				var ids = id.split("_");
				var number = $("#index_"+ids[1]).val();
				if(number <= 0){
					number == 1;
				}
				if(number > 1000){
					number = 1000;
				}
				index[ids[1]] = number;
				index_text(true, ids[1]);
				send_index(urls[ids[1]], ids[1]);
			});
		});
    </script>
</body>
</html>