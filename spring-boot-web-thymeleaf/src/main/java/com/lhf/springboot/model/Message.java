package com.lhf.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Calendar;

/**
 * @ClassName: Message
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/28 17:14
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Message {

    private Long id;

    @NotEmpty(message = "Text is required.")
    private String text;

    @NotEmpty(message = "Summary is required.")
    private String summary;

    private Calendar created = Calendar.getInstance();
}
