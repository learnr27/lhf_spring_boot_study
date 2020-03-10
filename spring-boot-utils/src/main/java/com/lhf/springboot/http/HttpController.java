package com.lhf.springboot.http;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

/**
 * @ClassName: HttpController
 * @Author: liuhefei
 * @Description: http
 * @Date: 2019/10/23 13:21
 */
@Controller
public class HttpController {

    /**
     * 获取http请求信息
     * @param keyStr
     * @param request
     * @param response
     * @return
     */
    //http://localhost:8095/interview/abcdef/getStr
    @RequestMapping(value = "/{keyStr}/getStr", method = RequestMethod.GET)
    @ResponseBody
    public String getStr(@PathVariable(value = "keyStr")String keyStr, HttpServletRequest request, HttpServletResponse response){

        try{
            System.out.println("keyStr = " + keyStr);
            long timestamp = System.currentTimeMillis();

            //使用URLEncoder.encode()编码,因为cookie不能存储中文,否则有中文会报错
            String time = URLEncoder.encode(String.valueOf(timestamp), "utf-8");
            //创建Cookie
            Cookie cookie = new Cookie("timestamp", time);
            //设置Cookie的最大生命周期,否则浏览器关闭后Cookie即失效
            cookie.setMaxAge(Integer.MAX_VALUE);
            //将Cookie加到response中
            response.addCookie(cookie);


            //Header部分
            System.out.print(request.getHeaderNames());
            Enumeration<?> enum1 = request.getHeaderNames();
            while (enum1.hasMoreElements()) {
                String key = (String) enum1.nextElement();
                String value = request.getHeader(key);
                System.out.println(key + "\t" + value);
            }

            System.out.println("cookie = " + request.getCookies());
            System.out.println("header = " + request.getHeader("Cookie"));

            System.out.println("\n\n");

            //获取cookie信息
            Cookie[] cookies = request.getCookies();
            if(cookies != null && cookies.length > 0){
                for (Cookie cookieStr : cookies){
                    System.out.println(cookieStr.getName() + ":" + cookieStr.getValue());
                }
            }

              /*Map<String, String> map = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookieStr : cookies){
                System.out.println(cookieStr.getName() + ":" + cookieStr.getValue());
                map.put(cookieStr.getName(), cookieStr.getValue());
            }
        }
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
        logger.info("jsonObject = " + jsonObject);

        String timestamp = jsonObject.getString("timestamp");
        String  x_rio_seq = jsonObject.getString(" x-rio-seq");
        String signature = jsonObject.getString("signature");

        */


            System.out.println("\n\n");

            String responseEncoding = response.getCharacterEncoding();
            System.out.println("responseEncoding = " + responseEncoding);
            String responseContentType = response.getContentType();
            System.out.println("responseContentType = " + responseContentType);
            int status = response.getStatus();
            System.out.println("status = " + status);
            Locale locale = response.getLocale();
            System.out.println("locale = " + locale);

        }catch (Exception e){
            e.printStackTrace();
        }

        return "你好！" + keyStr;

    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public String getHttpInfo(HttpServletRequest request, HttpServletResponse response){
        //获取请求头信息
        Map<String,String[]> requestMsg = request.getParameterMap();
        Enumeration<String> requestHeader = request.getHeaderNames();

        System.out.println("------- header -------");
        while(requestHeader.hasMoreElements()){
            String headerKey=requestHeader.nextElement().toString();
            //打印所有Header值

            System.out.println(headerKey+" : "+request.getHeader(headerKey));
        }


        return "你好！";


    }

}
