package com.lhf.springboot.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MyWebFilter
 * @Author: liuhefei
 * @Description: 自定义过滤器
 * 在过滤器中设置请求的时间，符合就通过，否则返回错误信息
 * @Date: 2019/7/29 16:56
 */
@Configuration
public class MyWebFilter {

    @Bean
    public FilterRegistrationBean registrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        //过滤掉 /getUser   和 /hello  的请求
        registrationBean.addUrlPatterns("/getUser", "/hello");
        //过滤掉所有的请求
        //registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter("paramName", "paramValue");
        registrationBean.setName("MyFilter");
        registrationBean.setOrder(1);    //registration.setOrder() 方法是设置优先级，数值越大，优先级越高。
        return registrationBean;
    }

    class MyFilter implements Filter {


        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            System.out.println("参数初始化： " + filterConfig);
        }

        @Override
        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) srequest;
            //执行过滤操作
            System.out.println("请求的url: " + request.getRequestURI());
            //获取系统时间
            Calendar ca = Calendar.getInstance();
            int hour = ca.get(Calendar.HOUR_OF_DAY);
            //设置限制运行时间
            if(0 <hour && hour < 2){
                HttpServletResponse response = (HttpServletResponse) sresponse;
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                //消息
                Map<String, Object> messageMap = new HashMap<>();
                messageMap.put("status", "1");
                messageMap.put("message", "此接口可以请求时间为：2-24点");
                ObjectMapper objectMapper = new ObjectMapper();
                String writeValueAsString = objectMapper.writeValueAsString(messageMap);
                response.getWriter().write(writeValueAsString);

                return;
            }
            filterChain.doFilter(srequest, sresponse);
        }

        @Override
        public void destroy() {
            System.out.println("开始销毁.....");
        }
    }
}
