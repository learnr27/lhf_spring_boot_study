package com.lhf.springboot.web;

import com.lhf.springboot.model.User;
import com.lhf.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: UserController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/29 17:08
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(){
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String userList(Model model){
        List<User> users = userService.getUserList();
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "user/userAdd";
    }

    @RequestMapping("/add")
    public String add(User user){
        userService.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, Integer id){
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(User user) {
        userService.edit(user);
        return "redirect:/list";
    }


    @RequestMapping("/delete")
    public String delete(Integer id) {
        userService.deleteUser(id);
        return "redirect:/list";
    }
}
