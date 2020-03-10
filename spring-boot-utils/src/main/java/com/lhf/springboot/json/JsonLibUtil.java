package com.lhf.springboot.json;

import net.sf.json.JSONObject;

/**
 * @ClassName: JsonLibUtil
 * @Author: liuhefei
 * @Description: JsonLib工具类
 * @Date: 2019/7/23 11:16
 */
public class JsonLibUtil {

    public static String bean2Json(Object obj){
        JSONObject jsonObject = JSONObject.fromObject(obj);
        return jsonObject.toString();
    }

    @SuppressWarnings("unchecked")
    public static <T> T json2Bean(String jsonStr, Class<T> objClass){
        return (T)JSONObject.toBean(JSONObject.fromObject(jsonStr), objClass);
    }
}
