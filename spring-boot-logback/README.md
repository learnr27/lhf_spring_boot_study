## LogBack、Slf4j和Log4j之间的关系
 Slf4j是The Simple Logging Facade for Java的简称，是一个简单日志门面抽象框架，它本身只提供了日志Facade API和一个简单的日志类实现，一般常配合Log4j，LogBack，java.util.logging使用。Slf4j作为应用层的Log接入时，程序可以根据实际应用场景动态调整底层的日志实现框架(Log4j/LogBack/JdkLog…)。
 
 LogBack和Log4j都是开源日志工具库，LogBack是Log4j的改良版本，比Log4j拥有更多的特性，同时也带来很大性能提升。
 
 LogBack官方建议配合Slf4j使用，这样可以灵活地替换底层日志框架。
 
 
## LogBack的结构
   LogBack被分为3个组件，logback-core, logback-classic 和 logback-access。
   
   logback-core提供了LogBack的核心功能，是另外两个组件的基础。
   
   logback-classic则实现了Slf4j的API，所以当想配合Slf4j使用时，需要将logback-classic加入classpath。
   
   logback-access是为了集成Servlet环境而准备的，可提供HTTP-access的日志接口。
   

## Maven依赖
   假如maven依赖中添加了spring-boot-starter-logging：
   
```$xslt
  <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-logging</artifactId>
   </dependency>
```
   那么，我们的Spring Boot应用将自动使用logback作为应用日志框架，Spring Boot启动的时候，由org.springframework.boot.logging.Logging-Application-Listener根据情况初始化并使用。
   
   但是呢，实际开发中我们不需要直接添加该依赖，你会发现spring-boot-starter其中包含了 spring-boot-starter-logging，该依赖内容就是 Spring Boot 默认的日志框架 logback


##语法说明
appender name="STDOUT": 日志打印到控制台

appender name="FILE"： 日志按日打印到文件中，最多保留MaxHistory天，每个文件大小为MaxFileSize

encoder：定义输出格式

%d{HH:mm:ss.SSS}——日志输出时间
%thread——输出日志的进程名字，这在Web应用以及异步任务处理中很有用
%-5level——日志级别，并且使用5个字符靠左对齐
%logger{36}——日志输出者的名字
%msg——日志消息
%n——平台的换行符

<root level="INFO">： 定义根logger，通过appender-ref指定前方定义的appender

<logger name="com.lhf.springboot" level="INFO" />：在继承root的logger上对com.lhf.springboot包日志作特殊处理

<springProfile name="dev">： 定义profile的值，只有特定profile的情况下，此间定义的内容才启作用


