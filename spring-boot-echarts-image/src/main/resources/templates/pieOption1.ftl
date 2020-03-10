{
    "calculable": true,
    "legend": {
        "data": ${types},
        "orient": "vertical",
        "x": "left",
        "textStyle": {
            "color": "red",
            "fontSize": 15,
            "fontWeight": "bolder"
        }
    },
    "series": [{
        "center": ["50%", "60%"],
        "data": ${datas},
        "name": "${title}",
        "radius": "65%",
        "type": "pie",
        "avoidLabelOverlap": true,
        "label": {
            "normal": {
                "show": true,
                "position": "top",
                "textStyle": {
                    "color":"red",
                    "fontSize": "15",
                    "fontWeight": "bold"
                }
            },
            "emphasis": {
                "show": true,
                "textStyle": {
                    "fontSize": "20",
                    "fontWeight": "bold"
                }
            }
        },
        "labelLine": {
            "normal": {
                "show": true
            }
        }
    }],
    "title": {
        "subtext": "",
        "text": "${title}",
        "x": "center",
        "textStyle": {
            "color": "green",
            "fontSize": 20,
            "fontWeight": "bolder"
        }
    },
    "toolbox": {
        "feature": {
            "mark": {
                "lineStyle": {
                    "color": "#1e90ff",
                    "type": "dashed",
                    "width": 2
                },
                "show": true
            },
            "dataView": {
                "lang": ["数据视图", "关闭", "刷新"],
                "readOnly": false,
                "show": true,
                "title": "数据视图"
            },
            "magicType": {
                "show": true,
                "title": {
                    "bar": "柱形图切换",
                    "stack": "堆积",
                    "tiled": "平铺",
                    "line": "折线图切换"
                },
                "type": ["pie", "funnel"]
            },
            "restore": {
                "show": true,
                "title": "还原"
            },
            "saveAsImage": {
                "lang": ["点击保存"],
                "show": true,
                "title": "保存为图片",
                "type": "png"
            }
        },
        "show": true
    },
    "tooltip": {
        "formatter": "{a} <br/>{b} : {c} ({d}%)",
        "trigger": "item"
    }
}
