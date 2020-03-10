package com.lhf.springboot.common.constants;

/**
 * @ClassName: PayType
 * @Author: liuhefei
 * @Description: 支付类型
 * @Date: 2019/11/7 16:13
 */
public enum PayType {
    /**支付类型*/
    ALI("支付宝", (short)1),
    WECHAT("微信", (short)2),
    UNION("银联", (short)3);

    private Short code;
    private String name;

    PayType(String name, Short code) {
        this.code = code;
        this.name = name;
    }

    public static String getName(Short code, String name){
        for(PayType c : PayType.values()){
            if(c.getCode() == code){
                return c.name;
            }
        }
        return null;
    }

    public Short getCode() {
        return code;
    }

    public void setCode(Short code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }}
