package com.lhf.springboot.ip;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName: DownloadFileUtil
 * @Author: liuhefei
 * @Description: IP工具类
 * @Date: 2019/10/18 17:40
 */
public class IpUtils {

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
     *
     * @return ip
     * */
    public static String getIpAddr(HttpServletRequest request){
        String ipAddress = null;
        try{
            ipAddress = request.getHeader("x-forwarded-for");
            if(ipAddress==null||ipAddress.length()==0||"unknown".equalsIgnoreCase(ipAddress)){
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if(ipAddress==null||ipAddress.length()==0||"unknown".equalsIgnoreCase(ipAddress)){
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if(ipAddress==null||ipAddress.length()==0||"unknown".equalsIgnoreCase(ipAddress)){
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    }catch (UnknownHostException e){

                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) {
                if(ipAddress.indexOf(",")>0){
                    ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
                }
            }
        }catch (Exception e){
            ipAddress = "";
        }
        return ipAddress;
    }

    /**
     * 获取真实IP
     * @param request 请求体
     * @return 真实IP
     */
    public static String getRealIp(HttpServletRequest request) {
        // 这个一般是Nginx反向代理设置的参数
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 处理多IP的情况（只取第一个IP）
        if (ip != null && ip.contains(",")) {
            String[] ipArray = ip.split(",");
            ip = ipArray[0];
        }
        return ip;
    }
}
