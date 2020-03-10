package com.lhf.springboot.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName: MD5Utils
 * @Desc: MD5加密处理工具类
 *
 * @Author: liuhefei
 * @Date: 2019/2/22 15:25
 */
public class MD5Utils {
	public static String MD5(String s) throws Exception {
		if (s.trim() == null) {
			return "null";
		}
		MessageDigest messagedigest = MessageDigest.getInstance("MD5");
		byte[] abyte0 = s.getBytes("utf-8");
		byte[] abyte1 = messagedigest.digest(abyte0);
		return bytes2Hex(abyte1).toUpperCase();
	}

	private static String bytes2Hex(byte[] b) {
		String s = "";
		for (int i = 0; i < b.length; ++i) {
			String s1 = Integer.toHexString(b[i] & 0xFF);
			if (s1.length() == 1) {
				s = s + "0";
			}
			s = s + s1;
		}

		return s;
	}

	/**
	 * <p>Description: 16位的MD5值</p>
	 * <p>Company: caimei365</p>
	 * @author dmeng
	 * @date 2015年12月17日 下午5:28:49
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public static String MD5To16Bit(String s) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(s.getBytes());
		byte b[] = md.digest();
		int i;
		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if(i < 0){
				i += 256;
			}
			if(i < 16){
				buf.append("0");
			}
			buf.append(Integer.toHexString(i));
		}
		return buf.toString().substring(8, 24);
	}
	// 全局数组
	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	// 返回形式为数字跟字符串
	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		// System.out.println("iRet="+iRet);
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}
	private static String byteToString(byte[] bByte) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}
	/**
	 *	签名计算方法：
	 *	参数对排序：参数对按参数首字母（assic值大小）升序排序，如：
	 *	Type=1&name=zhang&_timestamp=111
	 *  排序完成后为：
	 *	Type=1&_timestamp=111&name=zhang
	 *	字符串 s = 排序后参数对（如果没有参数，设置为空）+tap请求body(如果body为空，设置为空串) + AccessId对应AccessKey。
	 *	字符串s的md5，即为signature值。
	 *	调用方发起计算signature值并设置header，被调用方取出header做签名校验。
	 */
	public static String GetMD5Code(String strObj) {
		String resultString = null;
		try {
			resultString = new String(strObj);
			MessageDigest md = MessageDigest.getInstance("MD5");
			// md.digest() 该函数返回值为存放哈希值结果的byte数组
			resultString = byteToString(md.digest(strObj.getBytes()));
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return resultString;
	}
	// 转换字节数组为16进制字串


	public static void main(String[] args) throws Exception{
//		System.out.println(MD5Util.MD5("act_name=发红包测试&client_ip=127.0.0.1&max_value=132&mch_billno=100257913453645825&mch_id=10025791&min_value=132&nick_name=采美365网&nonce_str=A1ED1E437160452FAE18356985D38F43&re_openid=oVTYvt4xhmqGKSI3owBO1TdJqtYo&remark=快来抢！&send_name=采美365网&total_amount=132&total_num=1&wishing=恭喜发财,大吉大利&wxappid=wxea43a0f9ebce9e66&key=CaimeiWxpayasdklfj8sdf27sdf3DcVd"));
		Map map = new HashMap();
		map.put("body", "test");
		map.put("appid", "1111");
		map.put("device_info", "100");
		map.put("mch_id", "11111111");
		map.put("nonce_str", "ibuaiVcKdpRxkhJA");

		String a = "appid=111&body=test&device_info=100&mch_id=11111111111&nonce_str=ibuaiVcKdpRxkhJA&key=192006250b4c09247ec02edce69f6a2d";
//		System.out.println(GetMD5Code(a).toUpperCase());
		String test = map.toString().substring(1, map.toString().length()-1).replace(", ", "&");
		System.out.println(byteToString(test.getBytes()));

		System.out.println(MD5("123456"));

	}

	/**
	 * 转换byte到16进制
	 * @param b 要转换的byte
	 * @return 16进制格式
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return strDigits[d1] + strDigits[d2];
	}
	/**
	 * 转换字节数组为16进制字串
	 * @param b 字节数组
	 * @return 16进制字串
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuilder resultSb = new StringBuilder();
		for (byte aB : b) {
			resultSb.append(byteToHexString(aB));
		}
		return resultSb.toString();
	}
	/**
	 * MD5编码
	 * @param origin 原始字符串
	 * @return 经过MD5加密之后的结果
	 */
	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = origin;
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(resultString.getBytes("UTF-8"));
			resultString = byteArrayToHexString(md.digest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}
}
