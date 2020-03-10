package com.lhf.springboot.controller;

import com.lhf.springboot.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: UserController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/28 15:28
 */
@Controller
public class UserController {

    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("message", "没有了你，万杯觥筹只不过是提醒寂寞");
        return "hello";
    }

    @RequestMapping("/string")
    public String string(ModelMap map) {
        map.addAttribute("userName", "一个人的生活很孤独");
        return "string";
    }

    @RequestMapping("/if")
    public String ifunless(ModelMap map) {
        map.addAttribute("flag", "no");
        return "if";
    }

    @RequestMapping("/list")
    public String list(ModelMap map) {
        map.addAttribute("users", getUserList());
        return "list";
    }

    @RequestMapping("/url")
    public String url(ModelMap map) {
        map.addAttribute("type", "link");
        map.addAttribute("pageId", "springcloud/2017/09/11/");
        map.addAttribute("img", "http://www.ityouknow.com/assets/images/neo.jpg");
        return "url";
    }

    @RequestMapping("/eq")
    public String eq(ModelMap map) {
        map.addAttribute("name", "刘豆豆");
        map.addAttribute("age", 18);
        map.addAttribute("flag", "yes");
        return "eq";
    }

    @RequestMapping("/switch")
    public String switchcase(ModelMap map) {
        map.addAttribute("sex", "woman");
        return "switch";
    }

    private List<User> getUserList(){
        List<User> list=new ArrayList<User>();
        User user1=new User("小花",18,"女", 168, 48, "13567898765", "dhdhgdgh@163.com", "广东深圳", "逛吃，旅行", "加油！加油！");
        User user2=new User("小蓝",20,"女", 170, 45, "18567898765", "wwweewz@163.com", "广东深圳", "旅行，爬山", "喜欢独自一人");
        User user3=new User("小芳",21,"女", 165, 47, "13767898765", "uytrrer@163.com", "广东深圳", "旅行， 绘画", "工作使我快乐");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return  list;
    }


}
