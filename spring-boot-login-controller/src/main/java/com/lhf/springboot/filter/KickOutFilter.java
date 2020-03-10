package com.lhf.springboot.filter;

import com.lhf.springboot.pojo.CurrentUser;
import com.lhf.springboot.pojo.UserInfo;
import com.lhf.springboot.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName: KickOutFilter
 * @Author: liuhefei
 * @Description: 控制登录过滤器
 * @Date: 2019/7/3 10:46
 */
public abstract class KickOutFilter implements Filter {
    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public RedissonClient redissonClient;

    @Autowired
    public UserInfoService userInfoService;

    public static final String PREFIX = "uni_token_";

    public static final String PREFIX_LOCK = "uni_token_lock_";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException{}

    @Override
    public void destroy(){}

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException{
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        try{
            if(checkToken(request, response) && isAccessAllowed(request, response)){
                filterChain.doFilter(req, resp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 当前用户是否允许访问
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public abstract boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response) throws Exception;

    public boolean checkToken(HttpServletRequest request, HttpServletResponse response){
        String token = request.getHeader("Authorization");
        if(StringUtils.isBlank(token)){
            sendJsonResponse(response, 401, "你无权访问");
            return false;
        }

        //校验token是否存在
        RBucket<UserInfo> rBucket = redissonClient.getBucket(token);
        UserInfo userInfo = rBucket.get();

        if(userInfo == null){
            sendJsonResponse(response, 403, "无效令牌");
            return false;
        }

        CurrentUser.put(userInfo);
        return true;
    }

    public static void sendJsonResponse(HttpServletResponse resp, int code, String message){
        sendJsonResponse(resp, String.format(jsonTemplate(), code, message));
    }

    public static String jsonTemplate(){
        return "{\"code\":%s,\"msg\":\"%s\",\"data\":null,\"errors\":null}";
    }

    public static void sendJsonResponse(HttpServletResponse response, String json){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try{
            out = response.getWriter();
            out.append(json);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(out != null){
                out.close();
            }
        }
    }
}
