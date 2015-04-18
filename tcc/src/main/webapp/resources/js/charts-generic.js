function montarGrafico(data, theme, containerName) {	
	try {
		Highcharts.theme = JSON.parse(theme);
	} catch(e) {
		Highcharts.theme = null;
	}
	
	Highcharts.setOptions(Highcharts.theme);
	try {
		var obj = JSON.parse(data);
	} catch(e) {
		return;
	}
	
	if (obj.categories.length == 0) {
		obj.categories = ['-'];
	}
	
	montarGraficoGenerico(obj, containerName);
}

function montarGraficoGenerico(obj, containerName) {

	var graphTitle = obj.title;
	var graphSubTitle = obj.subtitle;
	var graphType = obj.type;
	var xAxisTitle = obj.xAxisTitle === undefined ? '' : obj.xAxisTitle;
	var yAxisTitle = obj.yAxisTitle === undefined ? '' : obj.yAxisTitle;
	var categoriesList = obj.categories;
	var isStacked = obj.stacked;
	var yAxisLabels = obj.yAxisLabels;
	var showValueSeries = obj.showValueSeries;
	var showToolTip = obj.showToolTip;
	
	chart = new Highcharts.Chart({
        chart: {
        	renderTo: containerName,
            type: graphType,
            plotBackgroundColor: null,
    		plotBorderWidth: null,
    		plotShadow: false,
    		backgroundColor: 'none',
            events: {
                load:  function(event) {
                	graphTitle = obj.title;
                	graphSubTitle = obj.subtitle;
                	var chart = this;
                	
                	var namesSeries = obj.namesSeries;
                		
	                jQuery.each(obj.valuesSeries, function(i, item) {
	                	chart.addSeries({
	                		name: namesSeries[i],
	                   		data: item,
	                   		dataLabels: {
	                   			enabled: true,
	                   			style: {
	                   				fontWeight:'bold',
		                   	    },
	                   			formatter: function() {
	                   				if (yAxisLabels !== undefined && showValueSeries) {
	                   					return this.y === undefined ? '' : yAxisLabels[this.y];
	                   				}
								}
	                   		},
	                	});
	                		
	            	});
                }
            }
        },
        
        plotOptions: {
            series: {
            	stacking: isStacked ? 'normal' : ''
            }
        },
        
        title: {
        	text: graphTitle
        },
        
        subtitle: {
            text: graphSubTitle,
        },
        
        credits: {
            enabled: false
        },
        
        tooltip: {
   			enabled: showToolTip
        },
        
        xAxis: {
        	showEmpty: false,
        	categories: categoriesList,
        	title: {
        		text: xAxisTitle
        	}
        },
        
        yAxis: {
        	showEmpty: true,
            title: {
                text: yAxisTitle
            },
            labels: {
                formatter: function() {
                	if (yAxisLabels !== undefined) {
                		return this.value === undefined ? '' : yAxisLabels[this.value];
                	}
                }
            },
            showFirstLabel: false,
            showLastLabel: true,
            tickPixelInterval: 30
        },

        legend: {
            enabled: true,
            width: 425,
			borderWidth: 0,
            horizontalAlign: 'center',
        },
        
        plotOptions: {
    		column: {
    			cursor: 'pointer',
    			borderColor: 'white',
    			pointPadding: 0.5,
    			pointWidth: 10,
    			minPointLength: 2,
    			borderWidth: 0,
    		},
	        line: {
				cursor: 'pointer',
				connectNulls: true
			}
        },
        
        tooltip: {
        	formatter: function() {
            	if (yAxisLabels !== undefined) {
            		var text = '<b>' + this.x + '</b><br>';
            		text += this.series.name + ': '; 
            		text += this.y === undefined ? '' : yAxisLabels[this.y];
            		return text;
            	}
            }
        },
        
        exporting: {
        	enabled: true
        }
    });
}

function reloadHighcharts() {
	jQuery.ajax({
		  url: contextPath+'/javascript/highcharts.js',
		  dataType: "script",
		  async: false
	});
	
	jQuery.ajax({
		  url: contextPath+'/javascript/exporting.js',
		  dataType: "script",
		  async: false
	});
}