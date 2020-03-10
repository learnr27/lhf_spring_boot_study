##SpringBoot整合RestTemplate     
来源： https://www.xncoding.com/2017/07/06/spring/sb-restclient.html

spring框架提供的RestTemplate类可用于在应用中调用rest服务，它简化了与http服务的通信方式，统一了RESTful的标准，封装了http链接， 我们只需要传入url及返回值类型即可。            
相较于之前常用的HttpClient，RestTemplate是一种更优雅的调用RESTful服务的方式。       

RestTemplate默认依赖JDK提供http连接的能力（HttpURLConnection），如果有需要的话也可以通过setRequestFactory方法替换为例如 Apache HttpComponents、Netty或OkHttp等其它HTTP library。     

实现逻辑：    
RestTemplate包含以下几个部分：   
HttpMessageConverter 对象转换器     
ClientHttpRequestFactory 默认是JDK的HttpURLConnection      
ResponseErrorHandler 异常处理     
ClientHttpRequestInterceptor 请求拦截器      


Http连接池配置：     
httpclient.connectTimeout: 20000  #建立连接的超时时间        
httpclient.requestTimeout: 20000  #连接不够用的等待时间    
httpclient.socketTimeout: 30000   #每次请求等待返回的超时时间       
httpclient.defaultMaxPerRoute: 100  #每个主机最大连接数     
httpclient.maxTotalConnections: 300  #最大连接数          
httpclient.defaultKeepAliveTimeMillis: 20000  #默认连接保持活跃的时间          
httpclient.closeIdleConnectionWaitTimeSecs: 30 #空闲连接生的存时间        





