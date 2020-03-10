package com.lhf.springboot.value;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @ClassName: RandomInject
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/24 16:54
 */
@Data
@Component
public class RandomInject {

    @Value("${com.lhf.value}")
    private String strValue;

    @Value("${com.lhf.int}")
    private Integer intValue;

    @Value("${com.lhf.long}")
    private Long longValue;

    @Value("${com.lhf.uuid}")
    private String uuid;

    @Value("${com.lhf.int100}")
    private Integer int100;

    @Value("${com.lhf.int10and50}")
    private Integer int10and50;

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        try {
            sb.append("strValue=").append(strValue).append("\r\n")
                    .append("intValue=").append(intValue).append("\r\n")
                    .append("longValue=").append(longValue).append("\r\n")
                    .append("uuid=").append(uuid).append("\r\n")
                    .append("int100=").append(int100).append("\r\n")
                    .append("int10and50=").append(int10and50).append("\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();

    }


}
