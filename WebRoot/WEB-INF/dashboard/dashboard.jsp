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
		function redrawChart(chart, data, data2){
			if(!chart){
				return ;
			}
			var seriesList = chart.series; 
			
			for(var i = 0;i<seriesList.length;){
		     		chart.series[0].remove();
			}
			chart.addSeries({                          
		           data: data
		    	});
		}
		function redrawChart2(chart, data, data2){
			if(!chart){
				return ;
			}
			var seriesList = chart.series; 
			
			for(var i = 0;i<seriesList.length;){
		     		chart.series[0].remove();
			}
			chart.addSeries({    
			   name: '活跃用户',
		           data: data
		    	}); 
			chart.addSeries({
		    	  name: '活跃应用',
		    	  data :data2
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
					var date_data = da.date_data;
					var date_uv = da.date_uv;
					var date_download_new = da.date_download_new;
					var date_app_new = da.date_app_new;
					
					var date_data2 = da.date_data2;
					
					/* categories=['1', '2', '3','4','5','6','7','1', '2', '3','4','5','6','7','1', '2', '3','4','5','6','7','1', '2', '3','4','5','6','7'] ;
					var data = [ 100, 11, 32, 110, 235, 369, 640, 120,100, 11, 32, 110, 235, 369, 640,100, 11, 32, 110, 235, 369, 640,100, 11, 32, 110, 235, 369]  ; */
					redrawChart2(chart1, date_data, date_data2);              
					redrawChart(chart2, date_uv);
					redrawChart(chart3, date_download_new);
					redrawChart(chart4, date_app_new);
					$("#livenumber").html(da.data);
					$("#number_sign").html(da.sign);
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
					$("#liveappnumber").html(da.total_app);
				 }else{
					
				}
			}); 
			
		} 
		$(function () {
			doeach();
			chart1=createChartItem('container',{}, '活跃');
			chart2=createChartItem('container1',{},'UV');
			chart3=createChartItem('container2',{},'下载/注册');
			chart4=createChartItem('container3',{},'应用/下载');
			showalert();
		});
		window.setInterval(showalert, 10000);
	</script>
</head>

<body>
  <div class="item_content">
    <ul>
    	<li>
        <div class="item" id="panel4">
        	<div class="domain" style="background-color:#d6d6d6;">
             	<div class="title">当前活跃</div>
                  <div class="ronglmang" style="width:400px;">
                    <div class="livescontent" style="background-color:#d6d6d6;"><div class="livenumber" id="livenumber">285</div><div class="livemessage">活跃数</div></div>
                  </div>
             </div>
         </div>
      </li>
      <li>
        <div class="item" id="panel">
        	<div class="domain" style="background-color:#d6d6d6;">
             	<div class="title">活跃应用</div>
                  <div class="ronglmang" style="width:400px;">
                   <div class="livescontent" style="background-color:#d6d6d6;"><div class="livenumber" id="liveappnumber">285</div><div class="livemessage">应用数</div></div>
                  </div>
             </div>
          </div>
      </li>
      <li>
        <div class="item" id="panel2">
                <div class="domain">
             	<div class="title">UV-注册</div>
                  <div class="ronglmang">
                    <div class="scontent"><div class="number" id="number_uv">1000</div><div class="message">UV</div></div>
                    <div class="scontent"><div class="number" id="number_uv_new">500</div><div class="message">NEW</div></div>
                    <div class="scontent"><div class="number" id="number_sign">41</div><div class="message">注册</div></div>
                  </div>
             </div>
          </div>
      </li>
      <li>
        <div class="item" ><div id="container" class="domain"></div> </div>
      </li>
      <li>
        <div class="item"> <div id="container1" class="domain"></div></div>
        <!-- <div class="item"> <div id="container1" class="domain"></div> </div> -->
       <!--  <div class="item" id="panel4">
        	<div class="domain" style="background-color:#d6d6d6;">
             	<div class="title">uv</div>
                  <div class="ronglmang" style="width:400px;">
                    <div class="livescontent" style="background-color:#d6d6d6;"><div class="livenumber" >敬请期待</div><div class="livemessage">uv趋势</div></div>
                  </div>
             </div>
         </div> -->
      </li>
      <li>
        <div class="item" id="panel3">
        	<div class="domain">
             	<div class="title">下载-应用</div>
                  <div class="ronglmang" style="width:400px">
                    <div class="scontent"><div class="number" id="number_download_new">9</div><div class="message">下载</div></div>
                    <div class="scontent"><div class="number" id="number_app_new">5</div><div class="message">应用</div></div>
                    <div class="scontent"><div class="number" id="number_download">20</div><div class="message">总下载</div></div>
                    <div class="scontent"><div class="number" id="number_app">20</div><div class="message">总应用</div></div>
                  </div>
             </div>
         </div>
      </li>
      <li>
        <div class="item"> <div id="container2" class="domain"></div></div>
      </li>
      <li>
        <div class="item"><div id="container3" class="domain"></div></div>
      </li>
     <li>
        <div class="item" id="panel1">
            <div class="domain">
             	<div class="title">千分比</div>
                  <div class="ronglmang" style="width:400px">
                    <div class="scontent"><div class="number" id="number_sign_uv">31</div><div class="message">注册/UV</div></div>
                    <div class="scontent"><div class="number" id="number_download_sign">296</div><div class="message">下载/注册</div></div>
                    <div class="scontent"><div class="number" id="number_app_download">750</div><div class="message">应用/下载</div></div>
                    <div class="scontent"><div class="number" id="number_app_sign">222</div><div class="message">应用/注册</div></div>
                  </div>
             </div>
          </div>
      </li>
    </ul>
    <div style="clear:both"></div>
  </div>
</body>

</html>
