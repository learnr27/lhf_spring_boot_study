package com.lhf.springboot.enums;

import com.lhf.springboot.exception.BaseErrorInfoInterface;

/**
 * @ClassName: CommonEnum
 * @Author: liuhefei
 * @Description: 公用描述类枚举类
 * @Date: 2019/7/26 16:27
 */
public enum  CommonEnum implements BaseErrorInfoInterface {

    // 数据操作错误定义
    SUCCESS("200", "成功!"),
    BODY_NOT_MATCH("400","请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH("401","请求的数字签名不匹配!"),
    NOT_FOUND("404", "未找到该资源!"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
    SERVER_BUSY("503","服务器正忙，请稍后再试!")
    ;

    //错误码
    private String errorCode;

    //错误信息
    private String errorMsg;

    CommonEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }}
