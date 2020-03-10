## SpringBoot整合log4j2
1. 引入Jar包
springboot默认是用logback的日志框架的，所以需要排除logback，不然会出现jar依赖冲突的报错。
```$xslt
<dependency>  
    <groupId>org.springframework.boot</groupId>  
    <artifactId>spring-boot-starter-web</artifactId>  
    <exclusions><!-- 去掉springboot默认配置 -->  
        <exclusion>  
            <groupId>org.springframework.boot</groupId>  
            <artifactId>spring-boot-starter-logging</artifactId>  
        </exclusion>  
    </exclusions>  
</dependency> 

<dependency> <!-- 引入log4j2依赖 -->  
    <groupId>org.springframework.boot</groupId>  
    <artifactId>spring-boot-starter-log4j2</artifactId>  
</dependency> 
```

2. 配置文件
   如果自定义了文件名，需要在application.yml中配置
```$xslt
logging:
     config: xxxx.xml
     level:
       com.lhf.springboot: trace
```
默认名log4j2-spring.xml，就省下了在application.yml中配置



日志级别:
机制：如果一条日志信息的级别大于等于配置文件的级别，就记录。    

trace：追踪，就是程序推进一下，可以写个trace输出      
debug：调试，一般作为最低级别，trace基本不用。     
info：输出重要的信息，使用较多      
warn：警告，有些信息不是错误信息，但也要给程序员一些提示。     
error：错误信息。用的也很多。      
fatal：致命错误。       

输出源:              
CONSOLE（输出到控制台）      
FILE（输出到文件）         

格式
SimpleLayout：以简单的形式显示
HTMLLayout：以HTML表格显示
PatternLayout：自定义形式显示
PatternLayout自定义日志布局：

%d{yyyy-MM-dd HH:mm:ss, SSS} : 日志生产时间,输出到毫秒的时间       
%-5level : 输出日志级别，-5表示左对齐并且固定输出5个字符，如果不足在右边补0           
%c : logger的名称(%logger)           
%t : 输出当前线程名称          
%p : 日志输出格式            
%m : 日志内容，即 logger.info("message")              
%n : 换行符             
%C : Java类名(%F)             
%L : 行号              
%M : 方法名             
%l : 输出语句所在的行数, 包括类名、方法名、文件名、行数               
hostName : 本地机器名              
hostAddress : 本地ip地址              

### Log4j2配置详解:
#### 根节点Configuration    
有两个属性:     
status          
monitorinterval       

有两个子节点:        
Appenders          
Loggers(表明可以定义多个Appender和Logger).

status用来指定log4j本身的打印日志的级别.

monitorinterval用于指定log4j自动重新配置的监测间隔时间，单位是s,最小是5s.

#### Appenders节点:     
常见的有三种子节点:Console、RollingFile、File     

##### Console节点用来定义输出到控制台的Appender.      
name:指定Appender的名字.       
target:SYSTEM_OUT 或 SYSTEM_ERR,一般只设置默认:SYSTEM_OUT.             
PatternLayout:输出格式，不设置默认为:%m%n.                 

##### File节点用来定义输出到指定位置的文件的Appender.           
name:指定Appender的名字.           
fileName:指定输出日志的目的文件带全路径的文件名.            
PatternLayout:输出格式，不设置默认为:%m%n.   

##### RollingFile节点用来定义超过指定条件自动删除旧的创建新的Appender.      
name:指定Appender的名字.      
fileName:指定输出日志的目的文件带全路径的文件名.           
PatternLayout:输出格式，不设置默认为:%m%n.           
filePattern : 指定当发生Rolling时，文件的转移和重命名规则.         
Policies:指定滚动日志的策略，就是什么时候进行新建日志文件输出日志.        
TimeBasedTriggeringPolicy:Policies子节点，基于时间的滚动策略，interval属性用来指定多久滚动一次，默认是1 hour。modulate=true用来调整时间：比如现在是早上3am，interval是4，那么第一次滚动是在4am，接着是8am，12am…而不是7am.             
SizeBasedTriggeringPolicy:Policies子节点，基于指定文件大小的滚动策略，size属性用来定义每个日志文件的大小.             
DefaultRolloverStrategy:用来指定同一个文件夹下最多有几个日志文件时开始删除最旧的，创建新的(通过max属性)。               

#### Loggers节点:          
常见的有两种:Root和Logger.                 
             
Root节点用来指定项目的根日志，如果没有单独指定Logger，那么就会默认使用该Root日志输出            

level:日志输出级别，共有8个级别，按照从低到高为：All < Trace < Debug < Info < Warn < Error < AppenderRef：Root的子节点，用来指定该日志输出到哪个Appender.           
Logger节点用来单独指定日志的形式，比如要为指定包下的class指定不同的日志级别等。              
level:日志输出级别，共有8个级别，按照从低到高为：All < Trace < Debug < Info < Warn < Error < Fatal < OFF.                
name:用来指定该Logger所适用的类或者类所在的包全路径,继承自Root节点.             
AppenderRef：Logger的子节点，用来指定该日志输出到哪个Appender,如果没有指定，就会默认继承自Root.如果指定了，那么会在指定的这个Appender和Root的Appender中都会输出，此时我们可以设置Logger的additivity="false"只在自定义的Appender中进行输出。             
  
  
来源：https://mp.weixin.qq.com/s/BlITsM-5Pb2xu_Pg9weR6Q             

