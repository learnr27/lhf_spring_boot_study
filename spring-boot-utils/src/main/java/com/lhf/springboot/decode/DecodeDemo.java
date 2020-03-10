package com.lhf.springboot.decode;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 *
 * @ClassName: DecodeDemo.java
 * @Description: Java字符串编码解码
 * 1.
 * getBytes() 编码 new String(byte[], decode) 解码
 *
 * 2.
 * 处理请求参数传递编码问题
        java中编码：URLEncoder.encode(strUri, "UTF-8");
        java中解码：URLDecoder.decode(strUri, "UTF-8");
 *
 * 3.
 * 流读取文件，具有转换编码功能的有：OutputStreamWriter和InputStreamReader，构造器有如：
      InputStreamReader(InputStream in, String charsetName) 创建使用指定字符集的 InputStreamReader。
      OutputStreamWriter(OutputStream out, String charsetName) 创建使用指定字符集的 OutputStreamWriter。
 * @version: v1.0.0
 * @author: liuhefei
 * @date: 2019年11月7日 下午12:17:13
 */
public class DecodeDemo {

	/**
	 * @Description:字符串转码
	 *
	 * @param str   需要转码的字符串
	 * @return
	 * @throws：异常描述
	 * @author:liuhefei
	 */
	public static String strDecode(String str) {
		if(str == null) {
			return "需要转码的字符串不能为空";
		}
		String s_iso88591 = null;  //将utf8编码的字符串解码为ISO8859-1
		String s_utf8 = null;   //将ISO8859-1编码的字符串解码为UTF-8
		try {
		    s_iso88591 = new String(str.getBytes("UTF-8"), "ISO8859-1");
			s_utf8 = new String(s_iso88591.getBytes("ISO8859-1"), "UTF-8");
			System.out.println(s_utf8);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return s_utf8;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "只愿君心似我心，定不负相思意";

		//编码
		byte[] b_default = str.getBytes();
		byte[] b_gbk = str.getBytes("GBK");
		byte[] b_utf8 = str.getBytes("UTF-8");
		byte[] b_iso8859 = str.getBytes("ISO8859-1");
		System.out.println("操作系统默认的编码格式：" + Arrays.toString(b_default));
		System.out.println("GBK编码格式： " + Arrays.toString(b_gbk));
		System.out.println("UTF8编码格式： " + Arrays.toString(b_utf8));
		System.out.println("ISO8859编码： " + Arrays.toString(b_iso8859));
		System.out.println();

		//解码
		System.out.println("操作系统默认的编码格式：" + new String(b_default));
		System.out.println("GBK编码格式： " + new String(b_gbk, "GBK"));
		System.out.println("UTF8编码格式： " + new String(b_utf8, "UTF-8"));
		System.out.println("ISO8859编码： " + new String(b_iso8859, "ISO8859-1"));

        String strResult = strDecode(str);
        System.out.println("strResult = " + strResult);

        //Charset
        Charset charset = Charset.forName("UTF-8");
        ByteBuffer buffer = charset.encode(str);
        String string = charset.decode(buffer).toString();
        System.out.println(string);

	}

}
