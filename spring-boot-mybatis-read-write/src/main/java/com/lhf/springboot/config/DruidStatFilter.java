package com.lhf.springboot.config;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @ClassName: DruidStatFilter
 * @Author: liuhefei
 * @Description: Druid拦截器，用于查看Druid监控
 * @Date: 2019/8/28 12:15
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*",
initParams = {
        @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")
})
public class DruidStatFilter extends WebStatFilter {
}
