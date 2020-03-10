package com.lhf.springboot.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: SwaggerConfig
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/29 19:26
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket webApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("支付后台API接口文档")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lhf.springboot.modules.web"))
                .paths(PathSelectors.any()).build();
    }
    @Bean
    public Docket alipayApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("支付宝API接口文档")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lhf.springboot.modules.alipay"))
                .paths(PathSelectors.any()).build();
    }
    @Bean
    public Docket weixinpayApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("微信API接口文档")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lhf.springboot.modules.weixinpay"))
                .paths(PathSelectors.any()).build();
    }
    @Bean
    public Docket unionpayApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("银联API接口文档")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lhf.springboot.modules.unionpay"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Api接口接口")
                .description("微信、支付宝、银联支付服务接口描述信息")
                .contact(new Contact("liuhefei", "https://www.imooc.com/u/1323320", "2510736432@qq.com"))
                .termsOfServiceUrl("https://swagger.io/swagger-ui/")
                .version("1.0")
                .build();
    }
}
