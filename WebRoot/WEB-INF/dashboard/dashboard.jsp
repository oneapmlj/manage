<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>dashboard</title>
	<link  type="text/css"  href="skin/dashboard/css/main.css" rel="stylesheet" />
	<script type="text/javascript" src="skin/dashboard/js/jq.js"></script>
	<script type="text/javascript" src="skin/dashboard/js/drag.js"></script>
	<script type="text/javascript" src="skin/dashboard/js/jquery-1.9.1.min.js"></script>
	<%-- <script type="text/javascript" src="skin/dashboard/js/bootstrap.min.js"></script> --%>
	<script type="text/javascript" src="skin/dashboard/js/highcharts.js"></script>
	<script type="text/javascript" src="skin/dashboard/js/highcharts-more.js"></script>
	<script type="text/javascript" src="skin/dashboard/js/chartItem.js"></script>
	<script type="text/javascript">
		var alldata={};
		var categories=['1', '2', '3','4','5','6','7','1', '2', '3','4','5','6','7','1', '2', '3','4','5','6','7','1', '2', '3','4','5','6','7'] ;
		var chart1;
		var chart2;
		var chart3;
		var chart4;
		function redrawChart(chart, data, name){
			if(!chart){
				return ;
			}
			var seriesList = chart.series; 
			
			for(var i = 0;i<seriesList.length;){
		     		chart.series[0].remove();
			}
			chart.addSeries({                    
			   name: name,
		           data: data
		    	});
		}
		function redrawChart2(chart, data, data2, name, name2, color2){
			if(!chart){
				return ;
			}
			var seriesList = chart.series; 
			
			for(var i = 0;i<seriesList.length;){
		     		chart.series[0].remove();
			}
			chart.addSeries({    
			   name: name,
		           data: data
		    	}); 
			chart.addSeries({
		    	  name: name2,
		    	  data :data2,
		    	  color:color2
		    	});
		}
		function redrawChart3(chart, data, data2, data3, name, name2, name3, color2, color3){
			if(!chart){
				return ;
			}
			var seriesList = chart.series; 
			
			for(var i = 0;i<seriesList.length;){
		     		chart.series[0].remove();
			}
			chart.addSeries({    
			   name: name,
		           data: data
		    	}); 
			chart.addSeries({
		    	  name: name2,
		    	  data :data2,
		    	  color:color2
		    	});
			chart.addSeries({
		    	  name: name3,
		    	  data :data3,
		    	  color:color3
		    	});
		}
		var showalert = function(){
			$.ajax({
				dataType:'json',
				url:'info_dashboard.action',
				data:{}
			}).done(function(da){
				if(da.status == 1){ 
					categories = da.date;
					var data_all = da.data_all;
					var data_ai = da.data_ai;
					var data_mi = da.data_mi;
					var data_server = da.data_server;
					var app_all = da.app_all;
					var app_ai = da.app_ai;
					var app_mi = da.app_mi;
					var app_server = da.app_server;
					var only_ai = da.only_ai;
					var only_mi = da.only_mi;
					var only_server = da.only_server;
					
					var date_data_all = da.date_data_all;
					var date_data_ai = da.date_data_ai;
					var date_data_mi = da.date_data_mi;
					var date_data_server = da.date_data_server;
					var date_app_all = da.date_app_all;
					var date_app_ai = da.date_app_ai;
					var date_app_mi = da.date_app_mi;
					var date_app_server = da.date_app_server;
					var date_only_ai = da.date_only_ai;
					var date_only_mi = da.date_only_mi;
					var date_only_server = da.date_only_server;
					
					/* categories=['1', '2', '3','4','5','6','7','1', '2', '3','4','5','6','7','1', '2', '3','4','5','6','7','1', '2', '3','4','5','6','7'] ;
					var data = [ 100, 11, 32, 110, 235, 369, 640, 120,100, 11, 32, 110, 235, 369, 640,100, 11, 32, 110, 235, 369, 640,100, 11, 32, 110, 235, 369]  ; */
					redrawChart2(chart1, date_app_all, date_data_all, "活跃应用", "活跃用户", "#98FB98");              
					redrawChart3(chart2, date_app_ai, date_data_ai, date_only_ai, "AI活跃应用", "AI活跃用户", "AI独立用户", "#98FB98", "#97FFFF"); 
					redrawChart3(chart3, date_app_mi, date_data_mi, date_only_mi, "MI活跃应用", "MI活跃用户", "MI独立用户", "#98FB98", "#97FFFF"); 
					redrawChart3(chart4, date_app_server, date_data_server, date_only_server, "SERVER活跃应用", "SERVER活跃用户", "SERVER独立用户", "#98FB98", "#97FFFF"); 
					$("#livenumber111").html(app_all);
					$("#livenumber211").html(data_all);
					$("#livenumber121").html(app_ai);
					$("#livenumber122").html(data_ai);
					$("#livenumber123").html(only_ai);
					$("#livenumber221").html(app_mi);
					$("#livenumber222").html(data_mi);
					$("#livenumber223").html(only_mi);
					$("#livenumber321").html(app_server);
					$("#livenumber322").html(data_server);
					$("#livenumber323").html(only_server);
					/* $("#number_sign").html(da.sign);
					$("#number_download_new").html(da.downloadNew);
					$("#number_app_new").html(da.appNew);
					$("#number_download").html(da.download);
					$("#number_app").html(da.app);
					$("#number_download_sign").html(da.download_sign);
					$("#number_app_sign").html(da.app_sign);
					$("#number_app_download").html(da.app_download);
					$("#number_sign_uv").html(da.sign_uv);
					$("#number_uv").html(da.uv);
					$("#number_uv_new").html(da.new_uv);
					$("#liveappnumber").html(da.total_app); */
				 }else{
					
				}
			}); 
			
		} 
		$(function () {
			doeach();
			chart1=createChartItem('container31',{}, '活跃/活跃应用');
			chart2=createChartItem('container13',{}, 'AI活跃应用/AI活跃用户/AI独立用户');
			chart3=createChartItem('container23',{},'MI活跃应用/MI活跃用户/MI独立用户');
			chart4=createChartItem('container33',{},'SERVER活跃应用/SERVER活跃用户/SERVER独立用户');
			showalert();
		});
		window.setInterval(showalert, 30000);
	</script>
</head>

<body>
  <div class="item_content">
    <ul>
    	<li>
        <div class="item" id="panel11">
        	<div class="domain" style="background-color:#d6d6d6;">
             	<div class="title">活跃应用</div>
                  <div class="ronglmang" style="width:400px;">
                    <div class="livescontent" style="background-color:#d6d6d6;"><div class="livenumber" id="livenumber111">0</div><div class="livemessage">活跃应用数</div></div>
                  </div>
             </div>
         </div>
      </li>
      <li>
        <div class="item" id="panel21">
        	<div class="domain" style="background-color:#d6d6d6;">
             	<div class="title">当前活跃</div>
                  <div class="ronglmang" style="width:400px;">
                   <div class="livescontent" style="background-color:#d6d6d6;"><div class="livenumber" id="livenumber211">0</div><div class="livemessage">活跃用户数</div></div>
                  </div>
             </div>
          </div>
      </li>
      <li>
        <div class="item" ><div id="container31" class="domain"></div> </div>
      </li>
      <li>
        <div class="item" id="panel12">
        	<div class="domain" style="background-color:#d6d6d6;">
             	<div class="title">AI活跃应用/AI活跃用户/AI独立用户</div>
                  <div class="ronglmang" style="width:500px;">
                    <div class="livescontent3" style="background-color:#d6d6d6;">
                    	<div class="livenumber3" id="livenumber121">285</div>
                    	<div class="livemessage3">AI活跃应用</div>
                    </div>
                    <div class="livescontent3" style="background-color:#d6d6d6;">
                    	<div class="livenumber3" id="livenumber122">285</div>
                    	<div class="livemessage3">AI活跃用户</div>
                    </div>
                    <div class="livescontent3" style="background-color:#d6d6d6;">
                    	<div class="livenumber3" id="livenumber123">285</div>
                    	<div class="livemessage3">AI独立用户</div>
                    </div>
                  </div>
             </div>
         </div>
      </li>
      <li>
        <div class="item" id="panel22">
        	<div class="domain" style="background-color:#d6d6d6;">
             	<div class="title">MI活跃应用/MI活跃用户/MI独立用户</div>
                  <div class="ronglmang" style="width:500px;">
                    <div class="livescontent3" style="background-color:#d6d6d6;">
                    	<div class="livenumber3" id="livenumber221">285</div>
                    	<div class="livemessage3">MI活跃应用</div>
                    </div>
                    <div class="livescontent3" style="background-color:#d6d6d6;">
                    	<div class="livenumber3" id="livenumber222">285</div>
                    	<div class="livemessage3">MI活跃用户</div>
                    </div>
                    <div class="livescontent3" style="background-color:#d6d6d6;">
                    	<div class="livenumber3" id="livenumber223">285</div>
                    	<div class="livemessage3">MI独立用户</div>
                    </div>
                  </div>
             </div>
         </div>
      </li>
      <li>
        <div class="item" id="panel32">
        	<div class="domain" style="background-color:#d6d6d6;">
             	<div class="title">SERVER活跃应用/SERVER活跃用户/SERVER独立用户</div>
                  <div class="ronglmang" style="width:500px;">
                    <div class="livescontent3" style="background-color:#d6d6d6;">
                    	<div class="livenumber3" id="livenumber321">285</div>
                    	<div class="livemessage3">SERVER活跃应用</div>
                    </div>
                    <div class="livescontent3" style="background-color:#d6d6d6;">
                    	<div class="livenumber3" id="livenumber322">285</div>
                    	<div class="livemessage3">SERVER活跃用户</div>
                    </div>
                    <div class="livescontent3" style="background-color:#d6d6d6;">
                    	<div class="livenumber3" id="livenumber323">285</div>
                    	<div class="livemessage3">SERVER独立用户</div>
                    </div>
                  </div>
             </div>
         </div>
      </li>
      <li>
        <div class="item" ><div id="container13" class="domain"></div> </div>
      </li>
      <li>
        <div class="item" ><div id="container23" class="domain"></div> </div>
      </li>
      <li>
        <div class="item" ><div id="container33" class="domain"></div> </div>
      </li>
    </ul>
    <div style="clear:both"></div>
  </div>
</body>

</html>
