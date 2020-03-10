{
    "title": {
        "text": "${title}",
        "textStyle": {
            "color": "red",
            "fontSize": 15,
            "fontWeight": "bolder"
        }
    },
    "toolbox": {
        "feature": {},
        "show": true
    },
    "tooltip": {
        "formatter": "{a}<br/>{b} : {c}",
        "show": true
    },
    "legend": {
        "textStyle": {
            "color": "red",
            "fontSize": 15,
            "fontWeight": "bolder"
        },
        "data": ["${legend}"]
    },
    "xAxis": [{
        "type": "category",
        "axisLabel": {
            "show": true,
            "textStyle": {
                "fontSize": 15,
                "fontWeight": "bolder"
            }
        },
        "axisLine":{
            "lineStyle":{
            "color":'#315070',
            "width":4
            }
        },
        "data": ${category}
    }],
    "yAxis": [{
        "type": "value",
        "axisLabel": {
            "show": true,
            "textStyle": {
                "fontSize": 15,
                "fontWeight": "bolder"
            }
        },
        "axisLine":{
            "show":true,
            "lineStyle":{
                "color":'#315070',
                "width":4
            }
        },
        "axisTick": {
            "show": true
        }
    }],
    "series": [{
        "name": "${legend}",
        "type": "bar",
        "label": {
            "normal": {
                "color": "red",
                "fontSize": 15,
                "fontWeight": "bolder",
                "show": true,
                "position": "top"
            }
        },
        "data":${values},
        "itemStyle": "__itemStyle"
    }]
}
