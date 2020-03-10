package com.lhf.springboot.json;

import com.alibaba.fastjson.JSON;

/**
 * @ClassName: FastJsonUtil
 * @Author: liuhefei
 * @Description: FastJson工具类
 * @Date: 2019/7/23 11:21
 */
public class FastJsonUtil {
    public static String bean2Json(Object obj){
        return JSON.toJSONString(obj);
    }

    public static <T> T json2Bean(String jsonStr, Class<T> objClass){
        return JSON.parseObject(jsonStr, objClass);
    }
}
