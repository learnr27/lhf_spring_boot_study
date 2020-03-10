package com.lhf.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: Message
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/5 16:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {

    private String msg;

    private String content;

}
