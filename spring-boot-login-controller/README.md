### SpringBoot 并发登录人数控制
来源： https://mp.weixin.qq.com/s/Y3O9ypnxk9uS1UlSFZPH_A    

方法思路：    
1. 比较时间戳   
2. 队列踢出     


运行项目，访问localhost:8888 demo中没有存储用户信息，随意输入用户名密码，用户名相同则被踢出

访问 localhost:8888/index.html 弹出用户信息, 代表当前用户有效

另一个浏览器登录相同用户名，回到第一个浏览器刷新页面，提示被踢出

application.properties中选择开启哪种过滤器模式，默认是比较时间戳踢出，开启队列踢出 queue-filter.enabled=true


