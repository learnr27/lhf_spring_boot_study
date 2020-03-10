package com.lhf.springboot.beans;

import lombok.Data;
import java.util.Date;

/**
 * @ClassName: Message
 * @Author: liuhefei
 * @Description: 消息类
 * @Date: 2019/10/12 15:12
 */
@Data
public class Message {
    private Long id;

    private String msg;  //消息

    private Date sendTime;  //时间戳
}
