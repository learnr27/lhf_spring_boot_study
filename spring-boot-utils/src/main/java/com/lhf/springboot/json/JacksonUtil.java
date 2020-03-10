package com.lhf.springboot.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @ClassName: JacksonUtil
 * @Author: liuhefei
 * @Description: Jackson 工具类
 * @Date: 2019/7/23 11:11
 */
public class JacksonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String bean2Json(Object obj){
        try{
            return mapper.writeValueAsString(obj);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T json2Bean(String jsonStr, Class<T> objClass){
        try{
            return mapper.readValue(jsonStr, objClass);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
