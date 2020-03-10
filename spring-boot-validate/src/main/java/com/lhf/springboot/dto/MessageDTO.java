package com.lhf.springboot.dto;

import com.lhf.springboot.validate.annotation.PhoneValidation;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @ClassName: MessageDTO
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/24 11:24
 */
@Data
public class MessageDTO {

    @NotEmpty(message = "{info.valid.notnull.error}")
    private String username;  //用户名

    @Pattern(regexp="^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$",message="{info.valid.email.error}")
    private String email;  //反馈邮箱

    @Length(min = 5, max = 20, message = "{info.valid.length.error}")
    private String content;  //反馈内容

    // 自定义规则注解
    @PhoneValidation
    private String phone;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull
    @Past
    private Date time;
}
