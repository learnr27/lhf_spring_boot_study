package com.lhf.springboot.modules.alipay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.lhf.springboot.common.constants.Constants;
import com.lhf.springboot.common.model.Product;
import com.lhf.springboot.modules.alipay.service.AlipayService;
import com.lhf.springboot.modules.alipay.util.AliPayConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName: AliPayController
 * @Author: liuhefei
 * @Description: 支付宝支付
 * @Date: 2019/12/2 17:23
 */
@Api(tags = "支付宝支付")
@Controller
@RequestMapping(value = "alipay")
public class AliPayController {

    private static final Logger logger = LoggerFactory.getLogger(AliPayController.class);

    @Autowired
    private AlipayService alipayService;

    @ApiOperation(value = "电脑支付")
    @RequestMapping(value = "pcPay", method = RequestMethod.POST)
    public String pcPay(Product product, ModelMap modelMap){
        logger.info("电脑支付....");
        String form = alipayService.aliPayPc(product);
        modelMap.addAttribute("form", form);

        return "alipay/pay";
    }

    @ApiOperation(value = "手机支付")
    @RequestMapping(value = "mobilePay", method = RequestMethod.POST)
    public String modilePay(Product product, ModelMap modelMap){
        logger.info("手机H5支付....");
        String form = alipayService.aliPayMobile(product);
        modelMap.addAttribute("form", form);
        return "alipay/pay";
    }

    @ApiOperation("二维码支付")
    @RequestMapping(value = "qcPay", method = RequestMethod.POST)
    public String qcPay(Product product, ModelMap modelMap){
        logger.info("二维码支付");
        String message = alipayService.alipay(product);
        if(Constants.SUCCESS.equals(message)){
            String img = "../qrcode/" + product.getOutTradeNo()+".png";
            modelMap.addAttribute("img", img);
        }else {
            //失败
            logger.info("二维码支付失败。。。");

        }
        return "alipay/qcpay";
    }

    @ApiOperation(value = "app支付")
    @RequestMapping(value = "appPay", method = RequestMethod.POST)
    public String appPay(Product product, ModelMap modelMap){
        logger.info("app支付....");
        String orderString = alipayService.appPay(product);
        modelMap.addAttribute("orderString", orderString);

        return "alipay/pay";
    }

    @ApiOperation(value = "支付宝支付回调(二维码,H5,网站)")
    @RequestMapping(value = "pay", method = RequestMethod.POST)
    public void alipay_notify(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String message = "success";
        Map<String, String> params = new HashMap<String, String>();
        //取出所有参数是为了验证签名
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String parameterName = parameterNames.nextElement();
            params.put(parameterName, request.getParameter(parameterName));
        }

        //验证签名  校验签名
        boolean signVerified = false;
        try{
            signVerified = AlipaySignature.rsaCheckV1(params, Configs.getAlipayPublicKey(), AliPayConfig.CHARSET, AliPayConfig.SIGN_TYPE);
            // 2018/01/26 以后新建应用只支持RSA2签名方式，目前已使用RSA签名方式的应用仍然可以正常调用接口，注意下自己生成密钥的签名算法
            //signVerified = AlipaySignature.rsaCheckV1(params, Configs.getAlipayPublicKey(), "UTF-8","RSA2");


            //signVerified = AlipaySignature.rsaCheckV2(params, Configs.getAlipayPublicKey(), "UTF-8");//正式环境
        }catch (AlipayApiException e){
            e.printStackTrace();
            message = "failed";
        }

        if(signVerified){
            logger.info("支付宝验证签名成功！");
            //若参数中的appid和填入的appid不相同，则为异常通知
            if(!Configs.getAppid().equals(params.get("app_id"))){
                logger.info("与付款时的appid不同，此为异常通知，应忽略！");
                message = "failed";
            }else{
                String outtradeno = params.get("out_trade_no");
                //在数据库中查找订单号对应的订单，并将其金额与数据库中的金额对比，若对不上，也为异常通知
                String status = params.get("trade_status");
                if(status.equals("WAIT_BUYER_PAY")){   //如果状态是正在等待用户付款
                    logger.info(outtradeno + "订单的状态正在等待用户付款");
                }else if (status.equals("TRADE_CLOSED")) { // 如果状态是未付款交易超时关闭，或支付完成后全额退款
                    logger.info(outtradeno + "订单的状态已经关闭");
                } else if (status.equals("TRADE_SUCCESS") || status.equals("TRADE_FINISHED")) { // 如果状态是已经支付成功
                    logger.info("(支付宝订单号:"+outtradeno+"付款成功)");
                    //这里 根据实际业务场景 做相应的操作
                } else {

                }
            }
        }else {   //如果验证签名没有通过
           message = "failed";
           logger.info("验证签名失败！");
        }

        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(message.getBytes());
        out.flush();
        out.close();
    }

    @RequestMapping("/frontRcvResponse")
    public String frontRcvResponse(HttpServletRequest request){
        try{
            //获取支付宝Get过来反馈信息
            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();

            for(Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();){
                String name = iter.next();
                String[] values = requestParams.get(name);
                String valueStr = "";
                for(int i = 0;i<values.length;i++){
                    valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                }
                //乱码解决
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }
            //商户订单号
            String orderNo = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //前台回调验证签名 v1 or v2
            boolean signVerified = alipayService.rsaCheckV1(params);
            if(signVerified){
                logger.info("订单号：" + orderNo + "验证签名结果【成功】");
                //处理业务逻辑。。。。

            }else {
                logger.info("订单号：" + orderNo + "验证签名结果【失败】");

            }
        }catch (Exception e){
            logger.error("发生了异常，异常：" + e);
            //处理异常信息。。。

        }
        //支付成功 跳转到成功页面
        return "success.html";
    }


}
