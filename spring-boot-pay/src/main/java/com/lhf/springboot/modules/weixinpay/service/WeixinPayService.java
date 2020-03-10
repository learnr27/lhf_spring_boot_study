package com.lhf.springboot.modules.weixinpay.service;

import com.lhf.springboot.common.model.Product;
import org.springframework.stereotype.Service;

/**
 * @ClassName: WeixinPayService
 * @Author: liuhefei
 * @Description: 微信支付服务
 * @Date: 2019/12/12 14:42
 */
@Service
public interface WeixinPayService {

    /**
     * 扫码支付
     * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_4
     * 微信支付下单(模式一)
     * @param product
     */
    void weixinPay1(Product product);

    /**
     * 扫码支付
     * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_4
     * 微信支付下单（模式二）
     * @param product
     * @return
     */
    String weixinPay2(Product product);

    /**
     * 微信支付退款
     * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_4
     * @param product
     * @return
     */
    String weixinRefund(Product product);

    /**
     * 关闭订单
     * @param product
     * @return
     */
    String weixinCloseorder(Product product);

    /**
     * 下载微信账单
     */
    void saveBill();

    /**
     * 微信公众号支付返回一个url地址
     * @param product
     * @return
     */
    String weixinPayMobile(Product product);

    /**
     * H5支付 唤醒 微信APP支付 进行支付
     * 申请入口：登录商户平台-->产品中心-->我的产品-->支付产品-->H5支付
     * @param product
     * @return
     */
    String weixinPayH5(Product product);

    /**
     * 查询订单
     * @param product
     */
    void orderquery(Product product);
}
