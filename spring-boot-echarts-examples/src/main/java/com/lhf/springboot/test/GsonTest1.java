package com.lhf.springboot.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

/**
 * @ClassName: GsonTest1
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/5 10:20
 */
public class GsonTest1 {
    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "刘豆豆");
        map.put("age", 24);
        map.put("sex", null);
        map.put("phone", "18295514444");
        map.put("email", null);

        GsonBuilder gsonBuilder = new GsonBuilder();
        String jsonString = gsonBuilder.serializeNulls().create().toJson(map);
        //String jsonString = new Gson().toJson(map);
        System.out.println(jsonString);

    }
}
