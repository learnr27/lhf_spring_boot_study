## SpringBoot配置文件的读取以及过滤器和拦截器的使用
来源：https://www.cnblogs.com/xuwujing/p/8485832.html


#### 过滤器：    
简单的来说，过滤器就是过滤的作用，在web开发中过滤一些我们指定的url 。     
过滤掉一些不需要的东西，例如一些错误的请求。     
也可以修改请求和相应的内容。     

过滤器的代码实现：         
过滤器(filter)有三个方法，其中初始化（init）和摧毁（destroy）方法一般不会用到，主要用到的是doFilter这个方法。    
而至于怎么过滤呢？     
如果过滤通过，则在doFilter执行filterChain.doFilter(request,response);该方法。     

那么在springBoot中如何使用过滤器呢？     
一般是使用Component和WebFilter 这两个注解，但是这里我们就直接方法调用。          
在该方法中添加Bean注解，springBoot会在启动的时候进行调用。并指定需要过滤的请求。          

 registration.setOrder() 方法是设置优先级，数值越大，优先级越高。    
 

#### 拦截器：
简单的来说，就是一道阀门，拦截不需要的东西。    
拦截器主要做什么？    
对正在运行的流程进行干预。     
   
拦截器的代码实现：     
拦截器也主要有三个方法，其中preHandle是在请求之前就进行调用，如果该请求需要被拦截，则返回false，否则true;      
                       postHandle是在请求之后进行调用，无返回值;      
                       afterCompletion是在请求结束的时候进行调用，无返回值。     

