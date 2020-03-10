package com.lhf.springboot.controller;

import com.lhf.springboot.model.Girl;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: GirlController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/9 18:43
 */
@RestController
@RequestMapping("/girl")
@Api(value = "女孩APi接口", tags = "女孩")
public class GirlController {

    @RequestMapping(value = "/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * SpringBoot默认使用Jsckson框架解析Json
     * 对象转json
     * @param girl
     * @return
     */
    @RequestMapping(value = "/getAll")
    //@ResponseBody
    public Girl getAll(@RequestBody Girl girl){
        girl.setId(1);
        girl.setName("珊珊");
        girl.setAge(24);
        girl.setCupSize("B罩杯");
        girl.setHeight(172);
        girl.setWeight(100);
        girl.setPhone("18296666688");
        girl.setAddress("中国北京");
        girl.setContent("谁，执我之手，敛我半世癫狂；谁，吻我之眸，遮我半世流离；谁，抚我之面，慰我半世哀伤；谁，携我之心，融我半世冰霜；谁，扶我之肩，驱我一世沉寂。");
        List<String> list = new ArrayList<>();
        list.add("电影");
        list.add("火锅");
        list.add("旅行");
        list.add("瑜伽");
        list.add("编程");
        girl.setHobbies(list);
        Map<String, Object> params = new HashMap<>();
        params.put("血型", "AB型");
        params.put("婚姻", "未婚");
        params.put("单身", "单身");
        params.put("房车", "无");
        girl.setParams(params);

        return girl;
    }

    @RequestMapping(value = "/list")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView("list");
        return mv;
    }

    @RequestMapping(value = "/getList")
    public List<Girl> girlList(){
        List<Girl> listGirl = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add("电影");
        list.add("火锅");
        list.add("旅行");
        list.add("瑜伽");
        list.add("编程");

        Map<String, Object> params = new HashMap<>();
        params.put("血型", "AB型");
        params.put("婚姻", "未婚");
        params.put("单身", "单身");
        params.put("房车", "无");
        listGirl.add(new Girl(1,"兰兰", 24, "C罩杯", 172, 110, "18296666633", "中国北京", "一个人", list, params ));
        listGirl.add(new Girl(2,"彤彤", 22, "B罩杯", 170, 100, "18296666644", "中国北京", "孤独岁月", list, params ));
        listGirl.add(new Girl(3,"琪琪", 20, "A罩杯", 168, 90, "18296666655", "中国北京", "天空很蓝", list, params ));

        return listGirl;
    }


}
