package com.lhf.springboot.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @ClassName: HttpUtil
 * @Author: liuhefei
 * @Description: Http工具类
 * @Date: 2019/8/15 12:10
 */
public class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 发送post请求
     * @param url
     * @param params
     * @param charset
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String post(String url, Map<String, String> params, String charset)
            throws ClientProtocolException, IOException {
        logger.info("httpPostRequest url : " + url + "   paramMap : " + params);
        String responseEntity = "";

        // 创建CloseableHttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();

        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);

        // 生成请求参数
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if (params != null) {
            for (Entry<String, String> entry : params.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        // 将参数添加到post请求中
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, charset));

        // 发送请求，获取结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);

        // 获取响应实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 按指定编码转换结果实体为String类型
            responseEntity = EntityUtils.toString(entity, charset);
        }

        // 释放资源
        EntityUtils.consume(entity);
        response.close();
        //System.out.println("responseEntity = " + responseEntity);

        return responseEntity;
    }

    public static String postUrl(String url, Map<String, Object> params, String charset) {
        logger.info("params = " + params);
        String responseEntity = "";
        // 创建CloseableHttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        // 将参数添加到post请求中
        httpPost.setEntity(new StringEntity(JSON.toJSONString(params), charset));
        // 发送请求，获取结果（同步阻塞）
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpPost);
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // 按指定编码转换结果实体为String类型
                responseEntity = EntityUtils.toString(entity, charset);
            }
            // 释放资源
            EntityUtils.consume(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseEntity;
    }

    /**
     * post请求（用于请求json格式的参数）
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, String params) throws Exception {

        logger.info("httpPostRequest url : " + url + "   paramMap : " + params);

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        //logger.info("entity = " + entity);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;

        try {

            response = httpclient.execute(httpPost);
            //logger.info("response = " + response);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                logger.info("post请求响应结果：{}", jsonString);
                return jsonString;
            }
            else{
                logger.error("请求返回:"+state+"("+url+")");
            }
        }
        finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    /**
     * http发送POST请求
     *
     * @author J.M.C
     * @since 2019年1月16日
     * @param url 长连接URL
     * @param paramsJson 请求参数body
     * @return result 字符串
     */
    public static String doPostJson(String url, JSONObject paramsJson) {
        logger.info("httpPostRequest url : " + url + "   paramMap : " + paramsJson);
        if(StringUtils.isBlank(url)){
            logger.error("httpPostRequest url is null");
            return null;
        }
        String result = "";
        try {
            // 创建httpClient实例
            CloseableHttpClient httpClient = HttpClients.createDefault();
            // 创建httpPost远程连接实例
            HttpPost httpPost = new HttpPost(url);
            // 配置请求参数实例
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000)// 设置连接主机服务超时时间
                .setConnectionRequestTimeout(10000)// 设置连接请求超时时间
                .setSocketTimeout(30000)// 设置读取数据连接超时时间
                .build();
            // 为httpPost实例设置配置
            httpPost.setConfig(requestConfig);
            // 设置请求头
            httpPost.addHeader("content-type", "application/json;charset=utf-8");
            // 封装post请求参数
            httpPost.setEntity(new StringEntity(paramsJson.toJSONString(), Charset.forName("UTF-8")));
            // httpClient对象执行post请求,并返回响应参数对象
            //   HttpResponse httpResponse = httpClient.execute(httpPost);
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            result = EntityUtils.toString(httpResponse.getEntity());
            //logger.info("result = {}" , result);
        } catch (UnsupportedEncodingException e) {
            logger.error("URLUtil.httpPostRequest encounters an UnsupportedEncodingException : {}",e);
        } catch (IOException e) {
            logger.error("URLUtil.httpPostRequest encounters an IOException : {}",e);
        }
        logger.info("URLUtil.httpPostRequest -----result----: " + result);
        return result;
    }


    public static String send(String url, JSONObject jsonObject,String encoding) throws Exception{
        logger.info("httpPostRequest url : " + url + "   jsonObject : " + jsonObject);
        String body = "";

        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);

        String strParam = JSONObject.toJSONString(jsonObject);
        //System.out.println("strParam = " + strParam);

        //装填参数
        StringEntity entity = new StringEntity(strParam, "utf-8");
        entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        //设置参数到请求对象中
        httpPost.setEntity(entity);
        //System.out.println("请求地址："+ url);
        //System.out.println("请求参数："+ entity.toString());

        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        //httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        //获取结果实体
        HttpEntity entityResult = response.getEntity();
        if (entityResult != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entityResult, encoding);
        }
        EntityUtils.consume(entityResult);
        //释放链接
        response.close();
        //logger.info("body = {}", body);
        return body;
    }


    public static JSONObject doPost(String url,JSONObject json){
        logger.info("httpPostRequest url : " + url + "   jsonObject : " + json);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = httpClient.execute(post);
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = res.getEntity();
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                //logger.info("result = {}", result);
                response = (JSONObject) JSONObject.parse(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //logger.info("response = {}", response);
        return response;
    }



}
