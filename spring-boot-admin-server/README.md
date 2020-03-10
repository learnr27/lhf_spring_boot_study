#Spring Boot Admin   服务端
Spring Boot Admin 是一个管理和监控你的 Spring Boot 应用程序的应用程序。这些应用程序通过 Spring Boot Admin Client（通过 HTTP）注册或者使用 Spring Cloud（例如 Eureka）发现。UI只是 Spring Boot Actuator 端点上的一个 AngularJs 应用程序。

在启动类上添加注解@EnableAdminServer，它类似于Springcloud的@EnableEurekaServer


文档：https://codecentric.github.io/spring-boot-admin/2.1.5/
