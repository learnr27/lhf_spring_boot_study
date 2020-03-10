##SpringBoot注解@value的用法总结
来源：https://blog.csdn.net/hry2015/article/details/72353994

使用@value注解注入属性，如下：
注入普通字符串   
注入操作系统属性   
注入表达式结果    
注入其他Bean属性：注入beanInject对象的属性another   
注入文件资源   
注入URL资源   



通过配置文件的注入属性的情况    
通过@Value将外部配置文件的值动态注入到Bean中。配置文件主要有两类：    
（1）application.properties。application.properties在spring boot启动时默认加载此文件        
（2）自定义属性文件。自定义属性文件通过@PropertySource加载。@PropertySource可以同时加载多个文件，也可以加载单个文件。如果相同第一个属性文件和第二属性文件存在相同key，则最后一个属性文件里的key启作用。加载文件的路径也可以配置变量，如下文的${anotherfile.configinject}，此值定义在第一个属性文件config.properties      

# ${}和#{}的区别
{}里面的内容必须符合SpEL表达式      
通过@Value(“${spelDefault.value}”)可以获取属性文件中对应的值，但是如果属性文件中没有这个属性，则会报错。可以通过赋予默认值解决这个问题，如@Value("${spelDefault.value:127.0.0.1}")    
```$xslt
#{…} 用于执行SpEl表达式，并将内容赋值给属性
${…} 主要用于加载外部属性文件中的值
#{…} 和${…} 可以混合使用，但是必须#{}外面，${}在里面
```

SpringBoot属性文件加载顺序：
默认先加载系统自带的application.properties属性文件，然后加载用户自定义的属性文件



