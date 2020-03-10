package com.lhf.springboot.common;

import java.util.HashMap;


/**
 * @ClassName: ResponseResult
 * @Author: liuhefei
 * @Description: 响应结果
 * @Date: 2019/7/31 10:22
 */
public class ResponseResult extends HashMap<String, Object>
{
    private static final long serialVersionUID = 1L;

    /**
     * 初始化一个新创建的 Message 对象
     */
    public ResponseResult()
    {
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static ResponseResult error()
    {
        return error(1, "操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 内容
     * @return 错误消息
     */
    public static ResponseResult error(String msg)
    {
        return error(500, msg);
    }

    /**
     * 返回错误消息
     *
     * @param code 错误码
     * @param msg 内容
     * @return 错误消息
     */
    public static ResponseResult error(int code, String msg)
    {
        ResponseResult json = new ResponseResult();
        json.put("code", code);
        json.put("msg", msg);
        return json;
    }

    /**
     * 返回成功消息
     *
     * @param msg 内容
     * @return 成功消息
     */
    public static ResponseResult success(String msg)
    {
        ResponseResult json = new ResponseResult();
        json.put("msg", msg);
        json.put("code", 200);
        return json;
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static ResponseResult success()
    {
        return ResponseResult.success("操作成功");
    }

    public static ResponseResult successData(int key, Object value){
    	 ResponseResult json = new ResponseResult();
    	 json.put("key", key);
         json.put("data", value);
         return json;
    }

    /**
     * 返回成功消息
     *
     * @param key 键值
     * @param value 内容
     * @return 成功消息
     */
    @Override
    public ResponseResult put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }
}
