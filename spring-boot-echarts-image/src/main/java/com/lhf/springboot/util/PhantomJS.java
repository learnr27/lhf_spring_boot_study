package com.lhf.springboot.util;


import lombok.Data;

import java.util.Map;

/**
 * 功能说明: PhantomJS参数<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author liansh<br>
 * 开发时间: 2019年9月22日<br>
 */
@Data
public class PhantomJS {
	private String reqMethod;// 请求方法:支持table和echarts
	private String url;// 请求地址:table时,请求的地址
	private String file;// 保存文件的路径名称:支持png.jpg,pdf等
	private Map<String, Object> opt;// 请求 参数:echarts时,option的入参
	private boolean close = true;// 是否关闭PhantomJS  true:不关闭， false:关闭
}
