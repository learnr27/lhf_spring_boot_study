package com.lhf.springboot.modules.web;

import com.lhf.springboot.common.utils.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: PayController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/12/5 11:47
 */
@Api(tags = "支付后台")
@Controller
@RequestMapping(value = "pay")
public class PayController {
    private static final Logger logger = LoggerFactory.getLogger(PayController.class);

    @ApiOperation(value = "登录")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public @ResponseBody String login(HttpServletRequest request, HttpServletResponse response, String account, String password){
        logger.info("登录....");
        String param = "false";
        if("admin".equals(account)&&"334455".equals(password)){
            param = "true";
        }
        return param;
    }

    @ApiOperation(value="后台展示")
    @RequestMapping(value="main",method=RequestMethod.GET)
    public String main(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        model.addAttribute("ip", "192.168.1.66");
        model.addAttribute("address", "深圳");
        model.addAttribute("time", DateUtil.getTime());
        return "web/main";
    }
}
