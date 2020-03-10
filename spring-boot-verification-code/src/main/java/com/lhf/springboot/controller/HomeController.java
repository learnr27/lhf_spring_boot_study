package com.lhf.springboot.controller;

import com.google.code.kaptcha.Constants;
import com.lhf.springboot.model.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: HomeController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/6 11:26
 */
@Controller
public class HomeController {


    /**
     * 请求到登陆界面
     * @param request
     * @return
     */
    @ApiOperation(value="请求到登陆界面",notes="请求到登陆界面")
    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model) {

        //登录逻辑
        System.out.println("请输入账号和密码进行登录");
        return "login";
    }

    /**
     * 用户登陆验证
     * @param user
     * @param redirectAttributes
     * @param rememberMe
     * @param model
     * @param request
     * @return
     */
    @PostMapping("login")
    public ModelAndView login(User user, String code, RedirectAttributes redirectAttributes, boolean rememberMe, Model model, HttpServletRequest request) {
        ModelAndView view =new ModelAndView();
        String scode = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String userName = user.getUsername();
        String userPass = user.getPassword();

        System.out.println("code = " + code );
        System.out.println("scode = " + scode);
        System.out.println("userName = " + userName + " , userPass = " + userPass);
        System.out.println("rememberMe = " + rememberMe);

        if (!code.equals(scode)){
            //跳转到 get请求的登陆方法
            view.setViewName("error");
            return view;
        }
        //跳转到 get请求的登陆方法
        view.setViewName("index");
        return view;

    }


}
