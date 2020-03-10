package com.lhf.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
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
 * @Desc: swagger文档配置
 * @Author: liuhefei
 * @Date: 2019/3/11 10:29
 */
@Configuration
@EnableSwagger2
//@Profile(value = {"dev", "test"})   //指定运行环境（开发，测试）
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lhf.springboot"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Api接口接口")
                .description("api接口描述信息")
                .contact(new Contact("作者", "url", "email"))
                .termsOfServiceUrl("https://swagger.io/swagger-ui/")
                .version("1.0")
                .build();
    }
}
