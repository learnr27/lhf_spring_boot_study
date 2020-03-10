package com.lhf.springboot.modules.weixinpay.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * @ClassName: PayCommonUtil
 * @Author: liuhefei
 * @Description: 微信支付签名相关工具类
 * @Date: 2019/12/5 15:14
 */
public class PayCommonUtil {


    /**
     * 是否签名正确，规则是：按照参数名称a-z排序，遇到空值的参数不参与签名
     * @param characterEncoding
     * @param packageParams
     * @param API_KEY
     * @return
     */
    @SuppressWarnings({"rawtypes"})
    public static boolean isTenpaySign(String characterEncoding, SortedMap<Object, Object> packageParams, String API_KEY){
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = ((Set) es).iterator();
        while (it.hasNext()){
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if(!"sign".equals(k) && null != v && !"".equals(v)){
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + API_KEY);
        //算出摘要
        String mysign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toLowerCase();
        String tenpaySign = ((String)packageParams.get("sign")).toLowerCase();

        return tenpaySign.equals(mysign);

    }

    /**
     * sign签名
     * @param characterEncoding
     * @param packageParams
     * @param API_KEY
     * @return
     */
    @SuppressWarnings({"rawtypes"})
    public static String createSign(String characterEncoding, SortedMap<Object, Object> packageParams, String API_KEY){
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()){
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if(null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)){
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + API_KEY);
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }

    /**
     * 将请求参数转化为xml格式的string
     * @param parameters
     * @return
     */
    @SuppressWarnings({"rawtypes"})
    public static String getRequestXml(SortedMap<Object, Object> parameters){
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 取出一个指定长度大小的随机正整数
     * @param length
     * @return
     */
    public static int buildRandom(int length){
        int num = 1;
        double random = Math.random();
        if(random < 0.1){
            random = random + 0.1;
        }
        for(int i = 0; i < length; i++){
            num = num * 10;
        }

        return (int)((random * num));
    }

    /**
     * 获取当前时间 yyyyMMddHHmmss
     * @return
     */
    public static String getCurrenTime(){
        DateTimeFormatter dataTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String dataStr = dataTimeFormatter.format(localDateTime);

        return dataStr;

    }


}
