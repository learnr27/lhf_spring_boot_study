package com.lhf.springboot.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;


/**
 * @ClassName: HttpClientUtil
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/3 10:39
 */

@SuppressWarnings(value = "all")
public class HttpClientUtil1 {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private static PoolingHttpClientConnectionManager connectionManager;
    private static String DEFAULT_STR = "";
    private static String UTF_8 = "UTF-8";
    private final static int CONNECT_TIMEOUT = 3000;// 连接超时毫秒 ps：表示建立连接的超时时间
    private final static int SOCKET_TIMEOUT = 10000;// 传输超时毫秒 ps：表示数据传输处理时间
    private final static int REQUESTCONNECT_TIMEOUT = 2000;// 从线程池获取连接超时时间毫秒
    private final static int MAX_TOTAL = 50;// 线程池的最大连接数
    private final static int CONNECT_DEFAULT_ROUTE = 5;// 每个路由默认基础的连接数

    private static void init() {
        if (connectionManager == null) {
            connectionManager = new PoolingHttpClientConnectionManager();
            connectionManager.setMaxTotal(MAX_TOTAL);// 整个连接池最大连接数
            // 可用空闲连接过期时间,重用空闲连接时会先检查是否空闲时间超过这个时间，如果超过，释放socket重新建立
            //connectionManager.setValidateAfterInactivity(50000);
            connectionManager.setDefaultMaxPerRoute(CONNECT_DEFAULT_ROUTE);// 每路由最大连接数，默认值是2
        }
    }

    /**
     * 通过连接池获取HttpClient
     * @return
     */
    private static CloseableHttpClient getHttpClient() {
        init();
        Builder builder = RequestConfig.custom();
        RequestConfig config = builder.setSocketTimeout(SOCKET_TIMEOUT)
            .setConnectTimeout(CONNECT_TIMEOUT)
            .setConnectionRequestTimeout(REQUESTCONNECT_TIMEOUT)
            .build();
        CloseableHttpClient client = HttpClients.custom()
            .setMaxConnPerRoute(CONNECT_DEFAULT_ROUTE)
            .disableConnectionState().setDefaultRequestConfig(config)
            .setConnectionManager(connectionManager).build();
        return client;
    }

    /**
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String httpGetRequest(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        return getResult(httpGet);
    }

    public static String httpGetRequest(String url, Map<String, Object> params) throws Exception {
        logger.info("参数url={}, params={}", url, params);
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        HttpGet httpGet = new HttpGet(ub.build());
        return getResult(httpGet);
    }

    public static String httpGetRequest(String url, Map<String, Object> headers
        , Map<String, Object> params) throws Exception {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        HttpGet httpGet = new HttpGet(ub.build());
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }
        return getResult(httpGet);
    }

    public static String httpPostRequest(String url) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        return getResult(httpPost);
    }

    public static String httpPostRequest(String url, Map<String, Object> params) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        return getResult(httpPost);
    }

    public static String httpPostRequest(String url, String jsonParams) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        StringEntity se = new StringEntity(jsonParams,UTF_8);
        httpPost.setEntity(se);
        httpPost.setHeader("Content-Type","application/json");
        return getResult(httpPost);
    }

    public static String httpPostXMLDataRequest(String url, String xmlData) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(new StringEntity(xmlData, UTF_8));
        return getResult(httpPost);
    }

    /**
     * post
     * @param url （a=3&b=2 形式）
     * @param headers 请求头
     * @param params 参数
     * @return
     * @throws IOException
     */
    public static String httpPostRequest(String url, Map<String, Object> headers
        , Map<String, Object> params) throws Exception {
        HttpPost httpPost = new HttpPost(url);

        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));


        return getResult(httpPost);
    }

    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
        }
        return pairs;
    }

    /**
     * post
     * @param url （JSON 形式）
     * @param headers 请求头
     * @param params 参数
     * @return
     * @throws IOException
     */
    public static String httpPostRequest(String url, Map<String, Object> headers, String jsonParams
    ) throws Exception {
        HttpPost httpPost = new HttpPost(url);

        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpPost.setHeader(param.getKey(), String.valueOf(param.getValue()));
        }

        StringEntity se = new StringEntity(jsonParams,UTF_8);
        httpPost.setEntity(se);

        return getResult(httpPost);
    }

    /**
     * 处理Http请求
     *
     * @param request
     * @return string
     * @throws IOException
     */
    private static String getResult(HttpRequestBase request) throws IOException {
        CloseableHttpClient httpClient = getHttpClient();
        CloseableHttpResponse response = null;
        InputStream in = null;
        try {
            response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            in = response.getEntity().getContent();
            if (entity != null) {
                String result = EntityUtils.toString(entity, Charset.forName(UTF_8));
                response.close();
                return result;
            }
        } catch (ConnectTimeoutException e) {
            // 连接超时异常
            logger.error("connect timeout {}", e);
        } catch (SocketTimeoutException e) {
            // 读取超时异常
            logger.error("read timeout {}", e);
        } catch (ClientProtocolException e) {
            // 该异常通常是协议错误导致:比如构造HttpGet对象时传入协议不对(将'http'写成'htp')or响应内容不符合
            logger.error("protocol exception {}", e);
        } catch (ParseException e) {
            // 解析异常
            logger.error("parse exception {}", e);
        } catch (IOException e) {
            // 该异常通常是网络原因引起的,如HTTP服务器未启动等
            logger.error("network exception {}", e);
        } catch (Exception e) {
            logger.error("other exception {}", e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //in.close();作用就是将用完的连接释放，下次请求可以复用
            //这里特别注意的是，如果不使用in.close();而仅仅使用response.close();结果就是连接会被关闭，并且不能被复用，这样就失去了采用连接池的意义。
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return DEFAULT_STR;
    }
}
