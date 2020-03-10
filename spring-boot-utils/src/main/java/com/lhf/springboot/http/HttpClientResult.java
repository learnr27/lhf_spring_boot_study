package com.lhf.springboot.http;

import java.io.Serializable;

/**
 * @ClassName: HttpClientResult
 * @Author: liuhefei
 * @Description: 封装http响应结果
 * @Date: 2019/8/30 16:44
 */
public class HttpClientResult implements Serializable {

    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private String content;

    public HttpClientResult(int code, String content) {
        this.code = code;
        this.content = content;
    }


    public HttpClientResult(int code) {
        this.code = code;
    }
}
