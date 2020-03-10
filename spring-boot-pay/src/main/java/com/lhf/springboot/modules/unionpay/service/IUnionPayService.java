package com.lhf.springboot.modules.unionpay.service;

import com.lhf.springboot.common.model.Product;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName: IUnionPayService
 * @Author: liuhefei
 * @Description: 银联支付
 * @Date: 2019/12/4 17:43
 */
@Service
public interface IUnionPayService {

    /**
     * 银联支付
     * @param product
     * @return
     */
    String unionPay(Product product);

    /**
     * 前台回调验证
     * @param valideData
     * @param encoding
     * @return
     */
    String validate(Map<String, String> valideData, String encoding);

    /**
     * 对账单下载
     */
    void fileTransfer();

}
