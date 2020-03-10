package com.lhf.springboot.exception;

/**
 * @ClassName: BaseErrorInfoInterface
 * @Author: liuhefei
 * @Description: 基础接口
 * 自定义的错误描述枚举类需要实现的接口
 * @Date: 2019/7/26 16:29
 */
public interface BaseErrorInfoInterface {

    //错误码
    String getErrorCode();

    //错误描述
    String getErrorMsg();
}
