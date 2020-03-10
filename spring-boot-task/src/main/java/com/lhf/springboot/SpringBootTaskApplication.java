package com.lhf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 异步调用任务
 *
 * 异步调用测试 EnableAsync: 开启异步调用，异步调用的方法只需加上@Async注解即可，
 *          一般放到启动类中，需要注意的是@Async所修饰的函数不要定义为static类型，这样异步调用不会生效
 *
 */
@EnableAsync  //开启异步调用
@SpringBootApplication
public class SpringBootTaskApplication {

    /*
     * SpringApplication 则是用于从main方法启动Spring应用的类。默认，它会执行以下步骤：
     * 1.创建一个合适的ApplicationContext实例 （取决于classpath）。
     * 2.注册一个CommandLinePropertySource，以便将命令行参数作为Spring properties。
     * 3.刷新application context，加载所有单例beans。
     * 4.激活所有CommandLineRunner beans。
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringBootTaskApplication.class, args);
        System.out.println("http://localhost:8070");
    }

}
