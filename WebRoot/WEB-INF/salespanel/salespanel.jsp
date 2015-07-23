<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <!-- <script src="js/jquery-1.4.4.min.js" type="text/javascript"></script> -->
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <!-- <link href="css/vscroller.css" rel="stylesheet" type="text/css" /> -->
    <link rel="stylesheet" href="${applicationScope.staticPath}skin/css/vscroller.css" type="text/css" media="screen" />
    <script src="${applicationScope.staticPath}skin/js/vscroller.js" type="text/javascript"></script>
	<script src="${applicationScope.staticPath}skin/js/highcharts.js"></script>
<script src="${applicationScope.staticPath}skin/js/highcharts-more.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
			var scrollData=[{name:"惠大庆/张海庆",
					  company:"合众人寿运维管理项目",
					  detail:"恭喜惠大庆/张海庆本月与合众人寿运维管理项目成功签约",
					  date:"本月"
					 },{name:"邵俊如",
					  company:"中移全通系统集成有限公司",
					  detail:"恭喜邵俊如本月与中移全通系统集成有限公司成功签约",
					  date:"本月"
					 },{name:"陈宁",
					  company:"北京华胜天成科技股份有限公司",
					  detail:"恭喜陈宁本月与北京华胜天成科技股份有限公司成功签约",
					  date:"本月"
					 },{name:"王霏",
					  company:"北京中电普华信息技术有限公司",
					  detail:"恭喜王霏本月与北京中电普华信息技术有限公司成功签约",
					  date:"本月"
					 },{name:"张伯瑾",
					  company:"北京思邈互联医药科技有限公司",
					  detail:"恭喜张伯瑾本月与北京思邈互联医药科技有限公司成功签约",
					  date:"本月"
					 },{name:"李冠孜",
					  company:"西安石榴花开电子科技有限公司",
					  detail:"恭喜李冠孜本月与西安石榴花开电子科技有限公司成功签约",
					  date:"本月"
					 },{name:"李冠孜",
					  company:"陕西西游电子商务有限公司",
					  detail:"恭喜李冠孜本月与陕西西游电子商务有限公司成功签约",
					  date:"本月"
					 },{name:"穆沛林",
					  company:"上海易兑外币兑换有限公司",
					  detail:"恭喜穆沛林本月与上海易兑外币兑换有限公司成功签约",
					  date:"本月"
					 }]
			var chartData=[];
            $('#vscroller').vscroller({ newsdata:scrollData});


			$('#container').highcharts({
					chart: {
						type: 'bar'
					},
					title: {
						text: '销售业绩'
					},
					xAxis: {
						categories: ['惠大庆', '邵俊如', '张海庆',  '陈宁','王霏','张伯瑾','李冠孜','穆沛林'],
						title: {
							text: null
						}
					},
					yAxis: {
						min: 0,
						title: {
							text: '签单数量',
							align: 'high'
						},
						tickInterval:1,
						labels: {
							overflow: 'justify'
						}
					},
					tooltip: {
						valueSuffix: '(单)'
					},
					plotOptions: {
						bar: {
							dataLabels: {
								enabled: true
							}
						}
					},
					legend: {
						layout: 'vertical',
						align: 'right',
						verticalAlign: 'top',
						x: 10,
						y: -10,
						floating: true,
						borderWidth: 1,
						backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
						shadow: true
					},
					credits: {
						enabled: false
					},
					series: [
					/* {
						name: '四月份',
						data: [0, 0, 0, 0, 0,0, 0,0]
					},  */
					/* {
						name: '五月份',
						data: [2, 2, 1, 1, 1,0, 0,0]
					},  */
					{
						name: '成单数',
						data: [1, 1, 1, 1, 1,1,2,1]
					}]
			 });
        });
    </script>
    <style type="text/css">
        body
        {
			background-size:100% 100%;
            font-family: Arial;
        }
       
    </style>
</head>
<body>
   
    <div class="news-wrapper" id="vscroller">
    </div>
	<div class="news-table" id="container">
    </div>
   
</body>
</html>
