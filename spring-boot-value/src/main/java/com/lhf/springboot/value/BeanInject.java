package com.lhf.springboot.value;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @ClassName: BeanInject
 * @Author: liuhefei
 * @Description: 注入普通字符串
 * @Date: 2019/7/24 15:22
 */
@Data
@Component
public class BeanInject {

    @Value("其他Bean的属性")
    private String another;

    @Value("注入普通字符串")
    private String str;


}
