package com.lhf.springboot.spel.multiway.annotation;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SpELAnnotationExample
 * @Author: liuhefei
 * @Description: 通过注解使用Spring EL
 * @Date: 2019/7/24 17:07
 */
@Data
@Component
public class SpELAnnotationExample {

    @Value("#{ 'Hello World'.concat('!') }")
    private String stringMethod; // 调用String的concat方法

    @Value("#{ T(java.util.Arrays).toString('Hello World'.bytes) }")
    private String javaBeanProperties; // 调用JavaBean的属性，如这里实际是调用getBytes()方法

    @Value("#{ 'Hello World'.bytes.length }")
    private long publicAttr; // 访问对象公共属性

    @Value("#{ people.name }")
    private String objName; // 获取People的name值，比较name值是不是hry

    @Value("#{ people.name=='root' }")
    private boolean objNamecmp;  // 比较name值是不是hry的结果

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
