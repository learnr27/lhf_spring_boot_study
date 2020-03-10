package com.lhf.springboot.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能说明: PhantomJS<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author liansh<br>
 * 开发时间: 2019年9月22日<br>
 */
public class PhantomJSUtil {
	private static String PORT = "6668";
	private static final String SUCCESS_CODE = "0";

	/**
	 * 采用代码的方式启动插件服务，在这里不用
	 */
	public static void start() {
		try {
			StringBuilder sb = new StringBuilder();
			if (System.getProperty("os.name").contains("Windows")) {
				sb.append(getResourcePath(" static/phantomJS/phantomjs-2.1.1-windows/bin/phantomjs.exe"));
			} else
				sb.append(getResourcePath("static/phantomJS/phantomjs-2.1.1-linux-x86_64/bin/phantomjs"));
			sb.append(" ").append(getResourcePath("static/js/my.js"));
			sb.append(" -s -p ").append(PORT);
			System.out.println(sb.toString());
			Runtime.getRuntime().exec(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String phantomJS(String requestUrl, Map<String, Object> params) {
		System.out.println("入参params: " + params);
		String base64 = "";
		//String response = HttpUtil.postUrl("http://localhost:" + PORT, params, "utf-8");
		String response = HttpUtil.postUrl(requestUrl, params, "utf-8");
		// 解析echartsConvert响应
		JSONObject responseJson = JSON.parseObject(response);
		System.out.println("responseJson = " + responseJson);
		String code = responseJson.getString("error_no");
		// 如果echartsConvert正常返回
		if (SUCCESS_CODE.equals(code)) {
			base64 = responseJson.getString("data");
		} else {// 未正常返回
			String string = responseJson.getString("error_info");
			throw new RuntimeException(string);
		}
		System.out.println("base64 = " + base64);

		return base64;
	}

	/**
	 * 关闭插件服务
	 */
	public static void colse() {
		HashMap<String, Object> pa = new HashMap<>();
		pa.put("exit", "true");
		phantomJS("http://localhost:" + PORT , pa);
	}

	public static String getResourcePath(String path) {
		System.out.println("path = " + path);
		path = PhantomJSUtil.class.getClassLoader().getResource(path).getPath();
		if (System.getProperty("os.name").contains("Windows"))
			return path.substring(1, path.length());
		else
			return path;
	}

	public static Map<String, Object> getJson(String templateFileName, String templateDirectory, Map<String, Object> datas) {
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
		// 设置默认编码
		configuration.setDefaultEncoding("UTF-8");
		// 设置模板所在文件夹
		Map<String, Object> opt = null;
		try {
			//configuration.setDirectoryForTemplateLoading(new File(PhantomJSUtil.getResourcePath("") + templateDirectory));
			configuration.setDirectoryForTemplateLoading(new File( templateDirectory));
			// 生成模板对象
			Template template = configuration.getTemplate(templateFileName);
			// 将datas写入模板并返回
			String json = null;
			StringWriter stringWriter = new StringWriter();
			template.process(datas, stringWriter);
			stringWriter.flush();
			json = stringWriter.getBuffer().toString();
			opt = JSON.parseObject(json);
			System.out.println("opt = " + opt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return opt;
	}
}
