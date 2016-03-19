$(function() {
    $('#container').highcharts({
        title: {
            text: 'Camera ID vs. Position',
            x: -20 //center
        },
        subtitle: {
            text: '',
            x: -20
        },
        xAxis: {
            categories: []
        },
        yAxis: {
            title: {
                text: 'Camera ID'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '°C'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: 'Person A',
            data: [1.2734748441450463, 0.8627219216831161, 1.0248686560250686, 1.1996213988784976, 1.0006695897078, 1.5, 1.5, 1.5, 1.1996213988784976, 1.1473206695897078, 0.9307632617358351, 0.8464741901036954, 1.2525964349908527, 1.1089683278382226, 1.3103436312304149
            ]
        }, {
            name: 'Person B',
            data: [0.89032691862267463, 1.2588296013703779, 1.1349100189486856, 1.3894288270438961, 1.3188915061409474, 1.5, 1.5, 1.5, 2.199217715153289, 1.9610958169444224, 1.848644344627577, 2.054678063750264, 1.9780893439634206]
        }]
    });
});