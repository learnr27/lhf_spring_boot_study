package com.lhf.springboot.test;

import com.google.gson.Gson;

import java.util.HashMap;

/**
 * @ClassName: FruitGsonTest
 * @Author: liuhefei
 * @Description:  Gson  输出错误的数据，缺少了sex和email
 * @Date: 2019/6/5 10:13
 */
public class GsonTest {

    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "刘豆豆");
        map.put("age", 24);
        map.put("sex", null);
        map.put("phone", "18295514444");
        map.put("email", null);

        String jsonString = new Gson().toJson(map);
        System.out.println(jsonString);

    }
}
