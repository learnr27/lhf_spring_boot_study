package com.lhf.springboot.dto;

import com.lhf.springboot.validate.group.Insert;
import com.lhf.springboot.validate.group.Update;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: GroupCardDTO
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/24 9:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupCardDTO {

    @NotBlank(groups = {Update.class})
    private String id;

    @NotBlank(groups = {Insert.class})
    private String cardNum;

    @NotNull(groups = {Insert.class, Update.class})
    private Integer cardType;
}
