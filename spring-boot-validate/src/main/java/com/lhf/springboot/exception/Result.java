package com.lhf.springboot.exception;

/**
 * @ClassName: Result
 * @Author: liuhefei
 * @Description: 结果信息
 * @Date: 2019/7/24 12:05
 */
public class Result {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
