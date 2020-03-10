package com.lhf.springboot.common.config;

import com.alipay.demo.trade.config.Configs;
import com.lhf.springboot.modules.unionpay.util.SDKConfig;
import com.lhf.springboot.modules.weixinpay.util.ConfigUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName: InitPay
 * @Author: liuhefei
 * @Description: 启动加载支付宝、微信、以及银联相关参数配置
 * @Date: 2019/11/7 15:52
 */
@Component
public class InitPay implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //初始化支付宝、微信、银联相关参数
        Configs.init("zfbinfo.properties"); //支付宝
        ConfigUtil.init("wxinfo.properties");   //微信
        SDKConfig.getConfig().loadPropertiesFromSrc();  //银联
    }
}
