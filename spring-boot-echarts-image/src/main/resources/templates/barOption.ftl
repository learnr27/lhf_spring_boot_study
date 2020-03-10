{
    "title": {
        "text": "${title}",
        "textStyle": {
            "color": "red",
            "fontSize": 15,
            "fontWeight": "bolder"
        }
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
                "color": "#315070",
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
            "lineStyle":{
                "color":"#315070",
                "width":4
            }
        }
    }],
    "series": [{
        "name": "${legend}",
        "data": ${values},
        "type": "bar",
        "label": {
            "normal": {
                "color": "green",
                "fontSize": 15,
                "fontWeight": "bolder",
                "show": true,
                "position": "top"
            }
        }
    }]
}
