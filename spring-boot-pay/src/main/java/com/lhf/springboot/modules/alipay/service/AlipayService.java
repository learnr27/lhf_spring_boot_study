package com.lhf.springboot.modules.alipay.service;

import com.lhf.springboot.common.model.Product;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName: AlipayService
 * @Author: liuhefei
 * @Description: 扫码支付以及手机H5支付
 * @Date: 2019/11/7 17:31
 */
@Service
public interface AlipayService {

    /**
     * 统一收单线下交易预创建
     * 接口文档地址：https://docs.open.alipay.com/api_1/alipay.trade.precreate
     * 接口描述：收银员通过收银台或商户后台调用支付宝接口，生成二维码后，展示给用户，由用户扫描二维码完成订单支付。
     *
     * * 如果你调用的是当面付预下单接口(alipay.trade.precreate)，调用成功后订单实际上是没有生成，因为创建一笔订单要买家、卖家、金额三要素。
     * * 预下单并没有创建订单，所以根据商户订单号操作订单，比如查询或者关闭，会报错订单不存在。
     * * 当用户扫码后订单才会创建，用户扫码之前二维码有效期2小时，扫码之后有效期根据timeout_express时间指定。
     *
     * @param product
     * @return
     */
    String alipay(Product product);

    /**
     * 统一收单交易退款接口
     * 接口文档地址：https://docs.open.alipay.com/api_1/alipay.trade.refund
     * 接口描述： 当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，支付宝将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退到买家帐号上。
     *
     * @param product
     * @return
     */
    String aliRefund(Product product);

    /**
     * 统一收单交易关闭接口
     * 接口文档地址：https://docs.open.alipay.com/api_1/alipay.trade.close
     * 接口描述：用于交易创建后，用户在一定时间内未进行支付，可调用该接口直接将未付款的交易进行关闭。
     *
     * @param product
     * @return
     */
    String aliCloseorder(Product product);

    /**
     * 下载对账单 https://docs.open.alipay.com/api_15/alipay.data.dataservice.bill.downloadurl.query
     * @param billDate  (账单时间：日账单格式为yyyy-MM-dd，月账单格式为yyyy-MM。)
     * @param billType billType(trade、signcustomer；trade指商户基于支付宝交易收单的业务账单；signcustomer是指基于商户支付宝余额收入及支出等资金变动的帐务账单；)
     * @return
     */
    String downloadBillUrl(String billDate, String billType);

    /**
     *
     * * 手机H5支付、腾讯相关软件下不支持、使用UC等浏览器打开
     * 	 * 方法一：
     * 	 * 对于页面跳转类API，SDK不会也无法像系统调用类API一样自动请求支付宝并获得结果，而是在接受request请求对象后，
     * 	 * 为开发者生成前台页面请求需要的完整form表单的html（包含自动提交脚本），商户直接将这个表单的String输出到http response中即可。
     * 	 * 方法二：
     * 	 * 如果是远程调用返回消费放一个form表单 然后调用方刷新到页面自动提交即可
     * 	 备注：人命币单位分
     * 	 attach 附件参数，使用json格式传递，用于回调区分
     *
     * @param product
     * @return
     */
    String aliPayMobile(Product product);

    /**
     * 电脑网站支付接口
     * 支付文档地址：https://docs.open.alipay.com/270
     * https://docs.open.alipay.com/api_1/alipay.trade.page.pay
     * 接口描述：通过电脑网站支付功能，用户在商家 PC 网站消费后界面会自动跳转到支付宝 PC 网站收银台完成付款。 交易资金直接打入商家支付宝账户，实时到账。
     *
     * @param product
     * @return
     */
    String aliPayPc(Product product);

    /**
     * App支付接口
     * 接口文档地址：https://docs.open.alipay.com/204
     * https://docs.open.alipay.com/api_1/alipay.trade.app.pay
     *
     * @param product
     * @return
     */
    String appPay(Product product);

    /**
     * 统一收单线下交易查询接口
     * 接口文档地址：https://docs.open.alipay.com/api_1/alipay.trade.query/
     * 接口描述：该接口提供所有支付宝支付订单的查询，商户可以通过该接口主动查询订单状态，完成下一步的业务逻辑。
     *
     * @return
     */
    String aliPayQuery(Product product);

    /**
     * 验证签名1
     * @param params
     * @return
     */
    boolean rsaCheckV1(Map<String, String> params);

    /**
     * 验证签名2
     * @param params
     * @return
     */
    boolean rsaCheckV2(Map<String, String> params);
}
