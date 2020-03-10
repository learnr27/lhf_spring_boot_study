phantom.outputEncoding = "gbk";// 为防止输出中文时出现乱码，可设置输出编码格式,写在最顶部
var params = require('system');// 获取系统参数
var server = require('webserver').create(); // 服务端
var port = params.args[3];// 端口,与启动命令有关,不一定是3
var listen = server.listen(port, function(request, response) {// 监听端口
	var args = serverGetArgs(request);// 得到网络请求参数
	args.response = response;
	methodDis(args);
});
var jslib = {
	jquery : phantom.libraryPath + '/lib/jquery-3.2.1.min.js',
	echarts : phantom.libraryPath + '/lib/echarts.min.js',
	china : phantom.libraryPath + '/lib/china.js',
};
/**
 * 请求分发
 * 
 * @author liansh
 * @data 2019年9月19日 下午11:32:59
 * @param args
 */
function methodDis(args) {
	if (args.reqMethod == "table") {
		table(args);
	} else if (args.reqMethod == "echarts") {
		echarts(args);
	}
	if (args.exit == "true") {
		writeResponse(args.response, {
			error_no : 0
		});
		phantom.exit();
	}
}
function table(args) {
	var page = require('webpage').create();// 打开页面
	// 设置分辨率
	page.viewportSize = {
		width : 1000,
		height : 1200
	};
	// 打开页面
	page.open(args.url || 'http://127.0.0.1:8080/hello', function(status) {
		if (status == "fail") {
			writeResponse(args.response, {
				error_no : -1
			});
			return;
		}
		page.injectJs(jslib.jquery);
		var tableheight = page.evaluate(function() {
			return $('body').height() + 20;
		});
		// 定义剪切范围
		page.clipRect = {
			top : 0,
			left : 0,
			width : 1000,
			height : tableheight
		};
		// var base64 = 'data:image/png;base64,' + page.renderBase64('png');
		page.render(args.file);// 将整个page保存为文件,可以是png，jpg, gif,pdf
		page.close();
		writeResponse(args.response, {
			error_no : 0
		});
	});
	page.onError = function(msg, trace) {
		writeResponse(args.response, {
			error_no : -1,
			error_info : trace
		});
	};
}
function echarts(args) {
	var page = require('webpage').create(); // 客户端
	page.open("about:blank", function(status) {// 空白页
		page.injectJs(jslib.jquery);
		page.injectJs(jslib.echarts);
		page.injectJs(jslib.china);
		var pageBody = page.evaluate(function(args) {
			// 动态加载js，获取options数据
			$('<script>').attr('type', 'text/javascript').html('var options = ' //
					+ JSON.stringify(args.opt)).appendTo(document.head);
			// 取消动画,否则生成图片过快，会出现无数据
			if (options !== undefined) {
				options.animation = false;
			}
			// body背景设置为白色
			$(document.body).css('backgroundColor', 'white');
			// echarts容器
			var container = $("<div>").attr('id', 'container').css({
				width : args.width,
				height : args.height
			}).appendTo(document.body);
			var eChart = echarts.init(container[0]);
			eChart.setOption(options);
		}, args);
		// 定义剪切范围
		page.clipRect = {
			top : 0,
			left : 0,
			width : args.width - 100,
			height : args.height + 10
		};
		// var base64 = 'data:image/png;base64,' + page.renderBase64('png');
		// writeResponse(args.response, {// 返回给http请求
		// error_no : 0,
		// base64 : base64
		// });
		page.render(args.file);// 将整个page保存为文件,可以是png，jpg, gif,pdf
		page.close();
		writeResponse(args.response, {
			error_no : 0
		});
	});
	page.onError = function(msg, trace) {
		writeResponse(args.response, {
			error_no : -1,
			error_info : trace
		});
	};
}
function writeResponse(response, msg) {
	response.write(JSON.stringify(msg || {
		error_no : 0
	}));
	response.close();
}
/**
 * 获取请求参数
 * 
 * @author liansh
 * @data 2019年9月19日 下午11:27:16
 * @param request
 * @returns
 */
function serverGetArgs(request) {
	var args = {};
	if ('GET' === request.method) {
		var index = request.url.indexOf('?');
		if (index !== -1) {
			pairs = request.url.substr(index + 1).split("&");
			for (var i = 0; i < pairs.length; i++) {
				var pos = pairs[i].indexOf('=');
				if (pos === -1)
					continue;
				var key = pairs[i].substring(0, pos);
				var value = pairs[i].substring(pos + 1);
				// 中文解码，必须写两层
				value = decodeURIComponent(decodeURIComponent(value));
				args[key] = value;
			}
		}
	} else if ('POST' === request.method) {
		args = JSON.parse(request.post);
	}
	args.width = args.width || 1000;
	args.height = args.height || 400;
	return args;
};