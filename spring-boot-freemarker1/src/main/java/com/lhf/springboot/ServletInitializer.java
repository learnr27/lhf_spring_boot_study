package com.lhf.springboot;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @ClassName: ServletInitializer
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/12 16:02
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootFreemarker1Application.class);
    }

}
