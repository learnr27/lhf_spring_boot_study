package com.lhf.springboot.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName: Message
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/22 16:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private Long id;

    @ApiModelProperty(value = "消息体")
    private String text;

    @ApiModelProperty(value = "消息总结")
    private String summary;

    private Date createDate;
}
