package com.lhf.springboot.security;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName: Base64Util
 * @Author: liuhefei
 * @Description: Base64加密工具类
 * @Date: 2019/11/1 10:32
 */
public class Base64Util {

    // 字符串编码
    private static final String UTF_8 = "UTF-8";

    /**
     * 加密字符串
     * @param inputData
     * @return
     */
    public static String decodeData(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.decodeBase64(inputData.getBytes(UTF_8)), UTF_8);
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    /**
     * 解密加密后的字符串
     * @param inputData
     * @return
     */
    public static String encodeData(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.encodeBase64(inputData.getBytes(UTF_8)), UTF_8);
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    public static void main(String[] args) {
        //加密
        System.out.println(Base64Util.encodeData("我是中文"));

        //解密
        String enStr = Base64Util.encodeData("我是中文");
        System.out.println(Base64Util.decodeData(enStr));
    }
}
