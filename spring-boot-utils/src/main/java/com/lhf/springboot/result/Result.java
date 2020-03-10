package com.lhf.springboot.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: Result
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/12/24 10:07
 */
@Data
public class Result implements Serializable {

    private Integer code;

    private String message;

    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result() {
    }

    public Result(ResultCode resultCode, Object data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    //返回成功
    public static Result success(){
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
    }

    //返回成功
    public static Result success(Object data){
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    //返回失败
    public static Result failure(ResultCode resultCode){
        Result result = new Result();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }

    //返回失败
    public static Result failure(ResultCode resultCode, Object data){
        Result result = new Result();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        result.setData(data);
        return result;
    }

}
