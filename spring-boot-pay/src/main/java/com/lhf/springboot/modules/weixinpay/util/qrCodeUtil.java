package com.lhf.springboot.modules.weixinpay.util;

import com.alipay.demo.trade.utils.ZxingUtils;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @ClassName: qrCodeUtil
 * @Author: liuhefei
 * @Description:  二维码生成器（扫码支付模式一）
 * @Date: 2019/12/5 17:48
 */
public class qrCodeUtil {

    private final static String IMG_PATH = "E:\\";

    public static String qrCode(String prductId){
        SortedMap<Object, Object> packageParams = new TreeMap<>();
        //封装通用参数
        ConfigUtil.commonParams(packageParams);
        packageParams.put("product_id", prductId);  //商品id
        packageParams.put("time_stamp", PayCommonUtil.getCurrenTime());
        //生成签名
        String sign = PayCommonUtil.createSign("UTF-8", packageParams, ConfigUtil.API_KEY);
        //组装二维码信息
        StringBuffer qrCode = new StringBuffer();
        qrCode.append("weixin://wxpay/bizpayurl?");
        qrCode.append("appid="+ConfigUtil.APP_ID);
        qrCode.append("&mch_id="+ConfigUtil.MCH_ID);
        qrCode.append("&nonce_str="+packageParams.get("nonce_str"));
        qrCode.append("&product_id="+prductId);
        qrCode.append("&time_stamp="+packageParams.get("time_stamp"));
        qrCode.append("&sign="+sign);
        //生成二维码
        String imgPath = IMG_PATH + PayCommonUtil.getCurrenTime() + ".png";
        ZxingUtils.getQRCodeImge(qrCode.toString(), 256, imgPath);
        return imgPath;

    }

    public static void main(String[] args) {

        String productId = "201912051755";

        System.out.println("二维码图片：" + qrCodeUtil.qrCode(productId));
    }


}
