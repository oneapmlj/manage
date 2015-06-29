function createChartItem(id,data, title){	
	var chart= new Highcharts.Chart({
		
        chart: {
            type: 'area',
			renderTo:id,
			backgroundColor:'#fff'
        },

        title: {
            text: title,
			align:'right',
			style:{
  					color: '#3366cd',
  					fontSize: '18px',
					fontFamily:'微软雅黑'
				  }	
        },
        xAxis: {
            allowDecimals: false,
            labels: {
                formatter: function () {
                    return categories[this.value]; // clean, unformatted number for year
                }
            },
			gridLineColor: '#197F07',//纵向网格线颜色
			gridLineDashStyle: 'LongDashDotDot',			
			gridLineWidth: 1, //纵向网格线宽度
			tickInterval:1 
        },
        yAxis: {
            title: {
                text: ''
            },
			minPadding:0,
			startOnTick:false,
            labels: {
                formatter: function () {
                    return this.value ;
                }
            },
			gridLine:false
			
        },
        tooltip: {
            pointFormat: '{series.name} produced <b>{point.y:,.0f}</b><br/>warheads in {point.x}'
        },
        plotOptions: {
            area: {
               // pointStart: 1940,
				color:'#8dc63f',
                marker: {
                    enabled: false,
                    symbol: 'circle',
                    radius: 2,
                    states: {
                        hover: {
                            enabled: true
                        }
                    }
                }
            }
        },
		legend: {
			enabled: false
		},
        series: []
    });
	return chart;
}