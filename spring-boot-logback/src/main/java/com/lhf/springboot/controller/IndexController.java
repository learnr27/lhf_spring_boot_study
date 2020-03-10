package com.lhf.springboot.controller;

import com.lhf.springboot.config.annotation.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @ClassName: IndexController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/17 12:49
 */
@RestController
public class IndexController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Log("首页页面")
    @RequestMapping(value="/index", method= RequestMethod.GET)
    public String index(@RequestParam String content) {
        LocalDateTime localDateTime = LocalDateTime.now();

        LOGGER.trace("请求参数：content:{}", content);
        LOGGER.debug("请求参数：content:{}", content);
        LOGGER.info("请求参数：content:{}", content);
        LOGGER.warn("请求参数：content:{}", content);
        LOGGER.error("请求参数：content:{}", content);

        return localDateTime + ",content: " + content;
    }


}
