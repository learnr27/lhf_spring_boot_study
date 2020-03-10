package com.lhf.springboot.common.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: IndexController
 * @Author: liuhefei
 * @Description: 统用访问拦截匹配
 * @Date: 2019/11/7 16:05
 */
@Controller
public class IndexController {

    /**
     * 页面跳转
     * @param url
     * @return
     */
    @RequestMapping("{url}.shtml")
    public String page(@PathVariable("url")String url){
        return url;
    }

    /**
     * 页面跳转（一级目录）
     * @param module
     * @param url
     * @return
     */
    @RequestMapping("{module}/{url}.shtml")
    public String page(@PathVariable("module")String module, @PathVariable("url")String url){
        return module + "/" + url;
    }

    @RequestMapping("{module}/{sub}/{url}.shtml")
    public String page(@PathVariable("module")String module, @PathVariable("sub")String sub, @PathVariable("url")String url){
        return module + "/" + sub + "/" + url;
    }
}
