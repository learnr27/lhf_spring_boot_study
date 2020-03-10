package com.lhf.springboot.spel.multiway.annotation;

import com.lhf.springboot.spel.multiway.People;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName: SpELAnnotationApplication
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/24 17:10
 */
@SpringBootApplication
@Configurable
public class SpELAnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpELAnnotationApplication.class, args);
    }


    /**
     * 初始化对象
     * @return
     */
    @Bean("people")
    public People getPeople(){
        People people = new People();
        people.setName("root");
        return people;
    }
}
