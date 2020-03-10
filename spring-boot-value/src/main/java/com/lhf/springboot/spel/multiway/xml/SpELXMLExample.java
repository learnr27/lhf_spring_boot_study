package com.lhf.springboot.spel.multiway.xml;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @ClassName: SpELXMLExample
 * @Author: liuhefei
 * @Description: 通过xml来注入
 * @Date: 2019/7/24 17:19
 */
@Data
public class SpELXMLExample {

    private String stringMethod; // 调用String的concat方法
    private String javaBeanProperties; // 调用JavaBean的属性，如这里实际是调用getBytes()方法
    private long publicAttr; // 访问对象公共属性
    private String objName; // 获取People的name值，比较name值是不是hry
    private boolean objNamecmp;  // 比较name值是不是hry人结果

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
