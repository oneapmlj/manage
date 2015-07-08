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
			var scrollData=[{name:"蔡光平",
					  company:"成都驾考团科技有限公司",
					  detail:"恭喜蔡光平本月与成都驾考团科技有限公司成功签约",
					  date:"2015/6/3"
					 },{name:"邵俊如",
					  company:"北京卡路里科技有限公司",
					  detail:"恭喜邵俊如本月与北京卡路里科技有限公司成功签约",
					  date:"2015/6/4"
					 },{name:"许振宇",
					  company:"郑州银行股份有限公司",
					  detail:"恭喜许振宇本月与郑州银行股份有限公司成功签约",
					  date:"2015/6/9"
					 },{name:"李玉峰",
					  company:"上海科视数码频道制作有限公司",
					  detail:"恭喜李玉峰本月与上海科视数码频道制作有限公司成功签约",
					  date:"2015/6/15"
					 },{name:"蔡光平",
					  company:"成都美恰科技",
					  detail:"恭喜蔡光平本月与成都美恰科技成功签约",
					  date:"2015/6/15"
					 },{name:"惠大庆",
					  company:"北京车好好科技",
					  detail:"恭喜惠大庆本月与北京车好好科技成功签约",
					  date:"2015/6/15"
					 },{name:"钟建波",
					  company:"成都我来啦",
					  detail:"恭喜钟建波本月与成都我来啦成功签约",
					  date:"2015/6/15"
					 },{name:"李智",
					  company:"天津市神州商龙科技有限公司",
					  detail:"恭喜李智本月与天津市神州商龙科技有限公司成功签约",
					  date:"2015/6/24"
					 },{name:"李玉峰",
					  company:"赛志科技（上海）有限公司",
					  detail:"恭喜李玉峰本月与赛志科技（上海）有限公司成功签约",
					  date:"2015/6/24"
					 },{name:"朱家红",
					  company:"北京铁拓无限科技有限公司",
					  detail:"恭喜朱家红本月与北京铁拓无限科技有限公司成功签约",
					  date:"2015/6/24"
					 },{name:"公司",
					  company:"北京永洪商智科技有限公司",
					  detail:"恭喜公司本月与北京永洪商智科技有限公司成功签约",
					  date:"2015/6/25"
					 },{name:"姚鹏",
					  company:"北京仁科互动网络技术有限公司",
					  detail:"恭喜姚鹏本月与北京仁科互动网络技术有限公司成功签约",
					  date:"2015/6/25"
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
						categories: ['惠大庆', '邵俊如', '李玉峰', '蔡光平', '许振宇','钟建波','李智','朱家红','公司','姚鹏'],
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
						data: [1, 1, 2, 2, 1, 1,1,1,1,1]
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
