package com.lhf.springboot.http;

import com.lhf.springboot.json.JsonUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.util.*;

/**
 * @ClassName: HttpClientUtil
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/2 15:26
 */
public class HttpClientUtil {
    private final static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    public static String MEHTOD_POST = "POST";
    public static String METHOD_GET = "GET";
    private String boundary;
    private boolean isTrustAll = false;
    private Map<String, String> headers = new HashMap();
    private RequestConfig cusRequestConfig;
    private RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).setConnectionRequestTimeout(10000).build();

    private HttpClientUtil() {
    }

    public static HttpClientUtil getInstance() {
        return new HttpClientUtil();
    }

    public HttpClientUtil addHeader(String name, String value) {
        this.headers.put(name, value);
        return this;
    }

    public HttpClientUtil setBoundary(String boundary) {
        this.boundary = boundary;
        return this;
    }

    public HttpClientUtil setTrustAll(boolean trustAll) {
        this.isTrustAll = trustAll;
        return this;
    }

    public HttpClientUtil setRequestConfig(RequestConfig requestConfig) {
        this.cusRequestConfig = requestConfig;
        return this;
    }

    private RequestConfig getRequestConfig() {
        return this.cusRequestConfig != null ? this.cusRequestConfig : this.defaultRequestConfig;
    }

    public String sendHttpPost(String httpUrl) {
        HttpPost httpPost = new HttpPost(httpUrl);
        return this.sendHttp0(httpPost, httpUrl);
    }

    public String sendHttpPost(String httpUrl, String params) {
        HttpPost httpPost = new HttpPost(httpUrl);

        try {
            StringEntity stringEntity = new StringEntity(params, "UTF-8");
            stringEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(stringEntity);
        } catch (Exception e) {
            logger.error("sys.httppost.error {},{}", new String[]{httpUrl, e.getMessage()}, e);

        }

        return this.sendHttp0(httpPost, httpUrl);
    }

    public String postFormData(String httpUrl, Map params) {
        Charset utf8 = Charset.forName("utf8");
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE).setCharset(utf8);
        if (this.boundary != null) {
            multipartEntityBuilder.setBoundary(this.boundary);
        }

        if (params != null) {
            try {
                Set<String> keySet = params.keySet();
                Iterator var6 = keySet.iterator();

                while(var6.hasNext()) {
                    String key = (String)var6.next();
                    Object value = params.get(key);
                    if (value != null) {
                        if (value instanceof File) {
                            multipartEntityBuilder.addPart(key, new FileBody((File)value));
                        } else {
                            multipartEntityBuilder.addPart(key, new StringBody(value.toString(), utf8));
                        }
                    }
                }
            } catch (UnsupportedEncodingException e) {
                logger.error("sys.httppost.error {},{}", new String[]{httpUrl, e.getMessage()}, e);
            }
        }

        HttpPost httpPost = new HttpPost(httpUrl);
        httpPost.setEntity(multipartEntityBuilder.build());
        return this.sendHttp0(httpPost, httpUrl);
    }

    public String sendHttpPostJson(String httpUrl, String json) {
        HttpPost httpPost = new HttpPost(httpUrl);

        try {
            StringEntity stringEntity = new StringEntity(json, "UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
        } catch (Exception e) {
            logger.error("sys.httppost.error {},{}", new String[]{httpUrl, e.getMessage()}, e);

        }

        return this.sendHttp0(httpPost, httpUrl);
    }

    public String sendHttpPost(String httpUrl, Map<String, String> maps) {
        HttpPost httpPost = new HttpPost(httpUrl);
        List<NameValuePair> nameValuePairs = new ArrayList();
        Iterator var5 = maps.keySet().iterator();

        while(var5.hasNext()) {
            String key = (String)var5.next();
            nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
        }

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        } catch (Exception e) {
            logger.error("sys.httppost.error {},{}", new String[]{httpUrl, e.getMessage()}, e);
        }

        return this.sendHttp0(httpPost, httpUrl);
    }

    public String sendHttpPost(String httpUrl, Map<String, String> maps, List<File> fileLists) {
        HttpPost httpPost = new HttpPost(httpUrl);
        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
        Iterator var6 = maps.keySet().iterator();

        while(var6.hasNext()) {
            String key = (String)var6.next();
            meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));
        }

        var6 = fileLists.iterator();

        while(var6.hasNext()) {
            File file = (File)var6.next();
            FileBody fileBody = new FileBody(file);
            meBuilder.addPart("files", fileBody);
        }

        HttpEntity reqEntity = meBuilder.build();
        httpPost.setEntity(reqEntity);
        return this.sendHttp0(httpPost, httpUrl);
    }

    private String sendHttp0(HttpRequestBase request, String httpUrl) {
        if (!this.headers.isEmpty()) {
            Iterator var3 = this.headers.entrySet().iterator();

            while(var3.hasNext()) {
                Map.Entry<String, String> me = (Map.Entry)var3.next();
                request.addHeader(me.getKey(), me.getValue());
            }
        }

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String responseContent = "";

        try {
            HttpClientBuilder httpClientBuilder = HttpClients.custom();
            if (this.isTrustAll) {
                httpClientBuilder = httpClientBuilder.setSSLContext((new SSLContextBuilder()).loadTrustMaterial(null, new TrustAllStrategy()).build());
            }

            httpClient = httpClientBuilder.build();
            request.setConfig(this.getRequestConfig());
            response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            logger.error("sys.httppost.error {},{}", new String[]{httpUrl, e.getMessage()}, e);
        } finally {
            this.closeResource(response, httpClient);
        }

        return responseContent;
    }

    public String sendHttpGet(String httpUrl) {
        HttpGet httpGet = new HttpGet(httpUrl);
        return this.sendHttp0(httpGet, httpUrl);
    }

    public String sendHttpsGet(String httpUrl) {
        HttpGet httpGet = new HttpGet(httpUrl);
        return this.sendHttp0(httpGet, httpUrl);
    }

    private void closeResource(CloseableHttpResponse response, CloseableHttpClient httpClient) {
        if (response != null) {
            try {
                response.close();
            } catch (IOException var5) {
                var5.printStackTrace();
            }
        }

        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (IOException var4) {
                var4.printStackTrace();
            }
        }

    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ServerName = request.getServerName();//返回服务器的主机名
        int ServerPort = request.getServerPort();//返回服务器的端口号

        String uri = request.getRequestURI();//返回请求行中的资源名称
        String url = request.getRequestURL().toString();//获得客户端发送请求的完整url
        String ip = request.getRemoteAddr();//返回发出请求的IP地址
        String params = request.getQueryString();//返回请求行中的参数部分
        String host=request.getRemoteHost();//返回发出请求的客户机的主机名
        int port =request.getRemotePort();//返回发出请求的客户机的端口号

        System.out.println(ServerName);
        System.out.println(ServerPort);

        System.out.println(ip);
        System.out.println(url);
        System.out.println(uri);
        System.out.println(params);
        System.out.println(host);
        System.out.println(port);
    }

    public static void main(String[] args) {
        //https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN

        String wxUrl = "https://api.weixin.qq.com";
        String access_token = "93f3d23c94ad18b8b1b3f97faa59696ab8e135f55f2200da4b2d";
        String openid = "135f55f2200da4b2d";
        String lang = "zh_CN";

        Map map = new HashMap();
        map.put("access_token", access_token);
        map.put("openid", openid);
        map.put("lang", lang);
        String qeuryJson = JsonUtil.toJson(map);

        //时间戳
        String timestamp = System.currentTimeMillis() / 1000 + "";
        System.out.println("timestamp:"+timestamp);

        String httpUrl = wxUrl + "/sns/userinfo?";

        /*
        byte[] signature= DigestUtils.sha256(timestamp + access_token + timestamp);
        String signatureParam = String.valueOf(signature);
        System.out.println(signature);
        String json = HttpClientUtil.getInstance()
                .addHeader("timestamp", timestamp)
                .addHeader("signature", signatureParam)
                .sendHttpPostJson(httpUrl, qeuryJson);
        */
        String json = HttpClientUtil.getInstance().sendHttpPostJson(httpUrl, qeuryJson);

        Map result = JsonUtil.getMapByJson(json);
        System.out.println("json = " + json);
        System.out.println("result = " + result);
        System.out.println("qeuryJson = " + qeuryJson);
    }
}
